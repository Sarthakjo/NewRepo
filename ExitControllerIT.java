package bcccp.carpark.exit;

import bcccp.carpark.*;
import bcccp.carpark.exit.ExitController;
import bcccp.carpark.exit.ExitUI;
import bcccp.carpark.exit.IExitUI;
import bcccp.tickets.adhoc.*;
import bcccp.tickets.season.ISeasonTicketDAO;
import bcccp.tickets.season.SeasonTicketDAO;
import bcccp.tickets.season.UsageRecordFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;

public class ExitControllerIT {
	private static Field stateField;
	private static Field adhocTicketField;
	private static Field adhocState;
	private static Field carDetectedField;
	private static Field nParkedField;
	
	private String testCarparkId;
	private int testCapacity;
	private ISeasonTicketDAO mockSeasonTicketDAO;
	private IAdhocTicket testTicket;
	private IGate mockGate;
	private CarSensor mockInsideSensor;
	private CarSensor mockOutsideSensor;
	private IExitUI mockExitUI;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		adhocTicketField = ExitController.class.getDeclaredField("adhocTicket");
		adhocTicketField.setAccessible(true);

		adhocState = AdhocTicket.class.getDeclaredField("state_");
		adhocState.setAccessible(true);
		
		carDetectedField = CarSensor.class.getDeclaredField("carDetected");
		carDetectedField.setAccessible(true);
		
		stateField = ExitController.class.getDeclaredField("state");
		stateField.setAccessible(true);
		
		nParkedField = Carpark.class.getDeclaredField("nParked");
		nParkedField.setAccessible(true);
	}
	
	@Before
	public void setUp() throws Exception {
		testCarparkId = "TestName";

		testCapacity = 1936;
		mockSeasonTicketDAO = new SeasonTicketDAO(new UsageRecordFactory());
		
		mockGate = new Gate(0,0);
		mockInsideSensor = new CarSensor("detectorIn", 0, 0);
		mockOutsideSensor = new CarSensor("detectorOut", 0, 0);
		
		mockExitUI = new ExitUI(0, 0);
	}

	@Test
	public void test1() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		IAdhocTicketDAO adhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());
		adhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());

		adhocTicketDAO.createTicket(testCarparkId);
		testTicket = adhocTicketDAO.findTicketByBarcode("A1");

		Carpark park = new Carpark(testCarparkId, testCapacity, adhocTicketDAO, mockSeasonTicketDAO);
		ExitController controller = new ExitController(park, mockGate, mockInsideSensor,
				mockOutsideSensor, mockExitUI);
				
		sensorToggle(mockInsideSensor, controller);
		Assert.assertEquals("WAITING", stateField.get(controller).toString());
		long entertime = testTicket.getEntryDateTime();
		testTicket.enter(entertime);
		long paid = testTicket.getPaidDateTime();
		float charge = testTicket.getCharge();
		testTicket.pay(paid,charge);
		testTicket.isPaid();
		controller.ticketInserted("A1");
		Assert.assertEquals("PROCESSED", stateField.get(controller).toString());
		controller.ticketTaken();
		Assert.assertEquals("TAKEN", stateField.get(controller).toString());
		sensorToggle(mockOutsideSensor, controller);
		Assert.assertEquals("EXITING", stateField.get(controller).toString());
		sensorToggle(mockInsideSensor, controller);
		Assert.assertEquals("EXITED", stateField.get(controller).toString());
		sensorToggle(mockOutsideSensor, controller);
		Assert.assertEquals("IDLE", stateField.get(controller).toString());
	}
	
	@Test
	public void test2() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		IAdhocTicketDAO adhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());
		adhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());
		Carpark park = new Carpark(testCarparkId, 0, adhocTicketDAO, mockSeasonTicketDAO);
		ExitController controller = new ExitController(park, mockGate, mockInsideSensor,
				mockOutsideSensor, mockExitUI);
		
		Assert.assertEquals("IDLE", stateField.get(controller).toString());
		sensorToggle(mockInsideSensor, controller);
		Assert.assertEquals("WAITING", stateField.get(controller).toString());
		controller.ticketInserted("A1");
		Assert.assertEquals("REJECTED", stateField.get(controller).toString());
		controller.ticketTaken();
		sensorToggle(mockInsideSensor, controller);
		Assert.assertEquals("IDLE", stateField.get(controller).toString());

		Assert.assertEquals(0, nParkedField.get(park));
		
	}
	
	private void sensorToggle(CarSensor sensor, ExitController controller) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		boolean newValue = !((boolean)carDetectedField.getBoolean(sensor));
		carDetectedField.set(sensor, newValue);
		controller.carEventDetected(sensor.getId(), newValue);
	}
}

