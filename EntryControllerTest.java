
package bcccp.carpark.entry;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bcccp.carpark.CarSensor;
import bcccp.carpark.Carpark;
import bcccp.carpark.Gate;
import bcccp.carpark.ICarSensor;
import bcccp.carpark.IGate;
import bcccp.tickets.adhoc.AdhocTicketDAO;
import bcccp.tickets.adhoc.AdhocTicketFactory;
import bcccp.tickets.adhoc.IAdhocTicketDAO;
import bcccp.tickets.season.ISeasonTicket;
import bcccp.tickets.season.ISeasonTicketDAO;
import bcccp.tickets.season.SeasonTicket;
import bcccp.tickets.season.SeasonTicketDAO;
import bcccp.tickets.season.UsageRecordFactory;

public class EntryControllerTest {
	private static Field stateField;
	private static Field prevStateField;
	private static Field messageField;
	private static Field entryGateField;
	private static Field outsideEntrySensorField;
	private static Field insideEntrySensorField;
	private static Field uiField;
	private static Field carparkField;
	private static Field adhockTicketField;
	private static Field entryTimeField;
	private static Field seasonTicketIdField;
	
	private String testCarparkId;
	private int testCapacity;
	private IAdhocTicketDAO mockAdhocTicketDAO;
	private ISeasonTicketDAO mockSeasonTicketDAO;
	private ISeasonTicket mockSeasonTicket;
	private ISeasonTicket mockInvalidSeasonTicket;
	
	private Carpark mockCarpark;
	private Carpark mockFullCarpark;
	private IGate mockGate;
	private ICarSensor mockInsideSensorCar;
	private ICarSensor mockOutsideSensorCar;
	private ICarSensor mockInsideSensorNoCar;
	private ICarSensor mockOutsideSensorNoCar;
	private IEntryUI mockEntryUI;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stateField = EntryController.class.getDeclaredField("state_");
		stateField.setAccessible(true);
		
		prevStateField = EntryController.class.getDeclaredField("prevState_");
		prevStateField.setAccessible(true);
				
		messageField = EntryController.class.getDeclaredField("message");
		messageField.setAccessible(true);
				
		entryGateField = EntryController.class.getDeclaredField("entryGate_");
		entryGateField.setAccessible(true);
		
		outsideEntrySensorField = EntryController.class.getDeclaredField("outsideEntrySensor_");
		outsideEntrySensorField.setAccessible(true);
				
		insideEntrySensorField = EntryController.class.getDeclaredField("insideEntrySensor_");
		insideEntrySensorField.setAccessible(true);
				
		uiField = EntryController.class.getDeclaredField("ui");
		uiField.setAccessible(true);
				
		carparkField = EntryController.class.getDeclaredField("carpark");
		carparkField.setAccessible(true);
				
		adhockTicketField = EntryController.class.getDeclaredField("adhocTicket");
		adhockTicketField.setAccessible(true);
						
		entryTimeField = EntryController.class.getDeclaredField("entryTime");
		entryTimeField.setAccessible(true);
				
		seasonTicketIdField = EntryController.class.getDeclaredField("seasonTicketId");
		seasonTicketIdField.setAccessible(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testCarparkId = "TestName1q2w3e4r5t6y7u8i9o0p";
		testCapacity = 1936;
		mockAdhocTicketDAO = new AdhocTicketDAO(new AdhocTicketFactory());
		mockSeasonTicketDAO = new SeasonTicketDAO(new UsageRecordFactory());
		
		mockSeasonTicket = new SeasonTicket("ssn4", testCarparkId, 0, 100);
		mockInvalidSeasonTicket = new SeasonTicket("ssn5", testCarparkId, 0, 100);
		mockSeasonTicketDAO.registerTicket(mockSeasonTicket);
		
		mockCarpark = new Carpark(testCarparkId, testCapacity, mockAdhocTicketDAO, mockSeasonTicketDAO);
		mockFullCarpark = new Carpark(testCarparkId, 1, mockAdhocTicketDAO, mockSeasonTicketDAO);
		mockGate = new Gate(0,0);
		mockInsideSensorCar = new CarSensor("detectorIn", 0, 0);
		mockOutsideSensorCar = new CarSensor("detectorOut", 0, 0);
		
		mockInsideSensorNoCar = new CarSensor("detectorIn", 0, 0);
		mockOutsideSensorNoCar = new CarSensor("detectorOut", 0, 0);
		
		Field carDetectedField = CarSensor.class.getDeclaredField("carDetected");
		carDetectedField.setAccessible(true);
		carDetectedField.setBoolean(mockInsideSensorCar, true);
		carDetectedField.setBoolean(mockOutsideSensorCar, true);
		
		mockEntryUI = new EntryUI(0, 0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEntryController() throws IllegalAccessException{
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorNoCar, mockInsideSensorNoCar, mockEntryUI);
		
		Assert.assertEquals(mockCarpark, carparkField.get(controller));
		Assert.assertEquals(mockGate, entryGateField.get(controller));
		Assert.assertEquals(mockOutsideSensorNoCar, outsideEntrySensorField.get(controller));
		Assert.assertEquals(mockInsideSensorNoCar, insideEntrySensorField.get(controller));
		Assert.assertEquals(mockEntryUI, uiField.get(controller));
		Assert.assertEquals("IDLE", stateField.get(controller).toString());
	}

	@Test
	public void testCarEventDetectedIdle() throws IllegalAccessException{
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorNoCar, mockInsideSensorNoCar, mockEntryUI);
		controller.carEventDetected(mockOutsideSensorNoCar.getId(), false);
		Assert.assertEquals("IDLE", stateField.get(controller).toString());
		controller.carEventDetected(mockInsideSensorCar.getId(), false);
		Assert.assertEquals("IDLE", stateField.get(controller).toString());		
		
	}
	
        @Test
	public void testCarEventDetectedWaiting() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorCar, mockEntryUI);
		controller.carEventDetected(mockOutsideSensorCar.getId(), true);
		Assert.assertEquals("WAITING", stateField.get(controller).toString());
		Object waitingState = stateField.get(controller);
		
	}	
	
        @Test
	public void testCarEventDetectedValidated() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorNoCar, mockEntryUI);
		controller.ticketInserted(mockSeasonTicket.getId());
		Assert.assertEquals("VALIDATED", stateField.get(controller).toString());
		Object validatedState = stateField.get(controller);
	}
	
        @Test
	public void testCarEventDetectedIssued() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorNoCar, mockEntryUI);
		controller.buttonPushed();
		Assert.assertEquals("ISSUED", stateField.get(controller).toString());
		Object issuedState = stateField.get(controller);
		
	}
	
        @Test
	public void testCarEventDetectedFull() throws IllegalAccessException {
		EntryController controller = new EntryController(mockFullCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorNoCar, mockEntryUI);
		controller.buttonPushed();
		Assert.assertEquals("FULL", stateField.get(controller).toString());
		Object fullState = stateField.get(controller);
		
	}
	
	@Test
	public void testCarEventDetectedTaken() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorNoCar, mockEntryUI);
		controller.buttonPushed();
		controller.ticketTaken();
		Object takenState = stateField.get(controller);
	}
	
	@Test
	public void testCarEventDetectedEntering() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorNoCar, mockEntryUI);
		controller.buttonPushed();
		controller.ticketTaken();
		Object takenState = stateField.get(controller);
		controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorNoCar, mockInsideSensorCar, mockEntryUI);
		stateField.set(controller, takenState);
		controller.carEventDetected(mockInsideSensorCar.getId(), true);
		Object enteringState = stateField.get(controller);
		
	}
	
	@Test
	public void testCarEventDetectedEntered() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorNoCar, mockEntryUI);
		controller.buttonPushed();
		controller.ticketTaken();
		Object takenState = stateField.get(controller);
		controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorNoCar, mockInsideSensorCar, mockEntryUI);
		stateField.set(controller, takenState);
		controller.carEventDetected(mockInsideSensorCar.getId(), true);
		Object enteringState = stateField.get(controller);
		controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorNoCar, mockInsideSensorCar, mockEntryUI);
		stateField.set(controller, enteringState);
		controller.carEventDetected(mockOutsideSensorNoCar.getId(), false);
		Object enteredState = stateField.get(controller);
		
	}
	
	@Test
	public void testTicketInserting() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorNoCar, mockEntryUI);
		controller.ticketInserted(mockInvalidSeasonTicket.getId());
		Assert.assertEquals("WAITING", stateField.get(controller).toString());
		
		controller.ticketInserted(mockSeasonTicket.getId());
		Assert.assertEquals("VALIDATED", stateField.get(controller).toString());
		
	}
	
	@Test
	public void testButtonPushed() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorCar, mockInsideSensorNoCar, mockEntryUI);
		controller.buttonPushed();
		Assert.assertEquals("ISSUED", stateField.get(controller).toString());
	}
	
	@Test
	public void testTicketTaken() throws IllegalAccessException {
		EntryController controller = new EntryController(mockCarpark, mockGate, mockOutsideSensorNoCar, mockInsideSensorNoCar, mockEntryUI);
		controller.ticketTaken();
		Assert.assertEquals("IDLE", stateField.get(controller).toString());
	}
}