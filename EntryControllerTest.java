
package bcccp.carpark.entry;

import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import bcccp.carpark.CarSensor;
import bcccp.carpark.Carpark;
import bcccp.carpark.Gate;
import bcccp.carpark.IGate;
import bcccp.tickets.adhoc.AdhocTicket;
import bcccp.tickets.adhoc.AdhocTicketDAO;
import bcccp.tickets.adhoc.AdhocTicketFactory;
import bcccp.tickets.adhoc.IAdhocTicketDAO;
import bcccp.tickets.season.ISeasonTicketDAO;
import bcccp.tickets.season.SeasonTicketDAO;
import bcccp.tickets.season.UsageRecordFactory;


public class EntryControllerTest {
	private static Field stateField;
	private static Field adhocTicketField;
	private static Field carDetectedField;
	private static Field nParkedField;
	
	private String testCarparkId;
	private int testCapacity;
	private ISeasonTicketDAO mockSeasonTicketDAO;
	
	private IGate mockGate;
	private CarSensor mockInsideSensor;
	private CarSensor mockOutsideSensor;
	private IEntryUI mockEntryUI;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		adhocTicketField = EntryController.class.getDeclaredField("adhocTicket");
		adhocTicketField.setAccessible(true);
		
		carDetectedField = CarSensor.class.getDeclaredField("carDetected");
		carDetectedField.setAccessible(true);
		
		stateField = EntryController.class.getDeclaredField("state_");
		stateField.setAccessible(true);
		
		nParkedField = Carpark.class.getDeclaredField("nParked");
		nParkedField.setAccessible(true);
	}
	
	@Before
	public void setUp() throws Exception {
		testCarparkId = "TestEntryController";
		testCapacity = 2000;
		mockSeasonTicketDAO = new SeasonTicketDAO(new UsageRecordFactory());
		
		mockGate = new Gate(0,0);
		mockInsideSensor = new CarSensor("detectorIn", 0, 0);
		mockOutsideSensor = new CarSensor("detectorOut", 0, 0);
		
		mockEntryUI = new EntryUI(0, 0);
	}

	// Trying to enter by adhocTicket
	@Test
	public void test1() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		IAdhocTicketDAO adhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());
		adhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());
		Carpark park = new Carpark(testCarparkId, testCapacity, adhocTicketDAO, mockSeasonTicketDAO);
		EntryController controller = new EntryController(park, mockGate, mockOutsideSensor,
				mockInsideSensor, mockEntryUI);
		park.register(controller);
		
		sensorToggle(mockOutsideSensor, controller);
		Assert.assertEquals("WAITING", stateField.get(controller).toString());
		controller.buttonPushed();
		AdhocTicket ticket = (AdhocTicket)adhocTicketField.get(controller);
		Assert.assertNotNull(adhocTicketDAO.findTicketByBarcode(ticket.getBarcode()));
		Assert.assertEquals("ISSUED", stateField.get(controller).toString());
		controller.ticketTaken();
		Assert.assertEquals("TAKEN", stateField.get(controller).toString());
		sensorToggle(mockInsideSensor, controller);
		Assert.assertEquals("ENTERING", stateField.get(controller).toString());
		sensorToggle(mockOutsideSensor, controller);
		Assert.assertEquals("ENTERED", stateField.get(controller).toString());
		sensorToggle(mockInsideSensor, controller);
		Assert.assertEquals("IDLE", stateField.get(controller).toString());

		Assert.assertTrue(System.currentTimeMillis() - ticket.getEntryDateTime() < 100);
		Assert.assertEquals(1, nParkedField.get(park));
	}
	
	@Test
	public void test2() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		IAdhocTicketDAO adhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());
		adhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());
		Carpark park = new Carpark(testCarparkId, 0, adhocTicketDAO, mockSeasonTicketDAO);
		EntryController controller = new EntryController(park, mockGate, mockOutsideSensor,
				mockInsideSensor, mockEntryUI);
		park.register(controller);
		
		sensorToggle(mockOutsideSensor, controller);
		Assert.assertEquals("WAITING", stateField.get(controller).toString());
		controller.buttonPushed();
		AdhocTicket ticket = (AdhocTicket)adhocTicketField.get(controller);
		Assert.assertNull(ticket);
		Assert.assertEquals("FULL", stateField.get(controller).toString());
		sensorToggle(mockOutsideSensor, controller);
		Assert.assertEquals("IDLE", stateField.get(controller).toString());

		Assert.assertEquals(0, nParkedField.get(park));
		
	}
	
	private void sensorToggle(CarSensor sensor, EntryController controller) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		boolean newValue = !((boolean)carDetectedField.getBoolean(sensor));
		carDetectedField.set(sensor, newValue);
		controller.carEventDetected(sensor.getId(), newValue);
	}
}