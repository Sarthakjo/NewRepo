

package test;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AdhocTicket {
	
	private String carparkId_;
	private int ticketNo_;
	private long entryDateTime;
	private long paidDateTime;
	private long exitDateTime;
	private float charge;
	private String barcode;
	private STATE state_;
	
	private enum STATE { ISSUED, CURRENT, PAID, EXITED }
	
	AdhocTicket ticket;
	
	@Before
	public void setUp() throws Exception {
		carparkId = test(IcarparkId.class);
		ticketNo= test(IticketId.class);
		entryDateTime = test(IentryDateTime.class);
		paidDateTime = test(IpaidDateTime.class);
		exitDateTime = test(IexitDateTime.class);
		charge = test(Icharge.class);
		barcode = test(Ibarcode.class);
		ticket = new AdhocTicket(carparkId, ticketNo, entryDateTime, paidDateTime,exitDateTime, charge, barcode);
	}
	
	@After
	public void tearDown() throws Exception {
		ticket = null;
	}
	@Test
	public void testAdhocTicket() {
		assertTrue(ticket instanceof IAdhocTicket);
		assertEquals(ticket.getCarparkId(), carparkId);
		assertTrue(ticket.getTicketNo(), ticketNo);
		assertEquals(ticket.getBarcode(), barcode);
		assertEquals(ticket.STATE.CURRENT, ISSUED);	
	}
	
	@Test
	public void testAdhocTicket(String carparkId, int ticketNo, String barcode) {
		// test when data is valid and not null
			String carparkId = AdhocTicket.getCarparkId;
			ticketNo = AdhocTicket.getTicketNo;
			String barcode = AdhocTicket.getBarcode;
		assertEquals(ticket.STATE.CURRENT, ISSUED);
		
		
	@Test
	public void testEnter() {
		assertTrue(ticket instanceof IAdhocTicket);
		assertEquals(ticket.getEntryDateTime(), entryDateTime);
		assertEquals(ticket.STATE.CURRENT, );
	}
	
	@Test
	public void testEnter(long entryDateTime) {
		// test when data is valid and not null
			long entryDateTime = AdhocTicket.getEntryDateTime;
		assertEquals(ticket.STATE.CURRENT, );
	
	
		@Test
	public void testPay() {
		assertTrue(ticket instanceof IAdhocTicket);
		assertEquals(ticket.getPaidDateTime(), paidDateTime);
		assertEquals(ticket.getCharge(), charge);
		assertEquals(ticket.STATE.CURRENT, PAID );
	}
	
	@Test
	public void testExit() {
		assertTrue(ticket instanceof IAdhocTicket);
		assertEquals(ticket.getExitDateTimeDateTime(), exitDateTime);
		assertEquals(ticket.STATE.CURRENT, EXITED);
	}
	@Test
	public void testExit(long exitDateTime) {
		// test when data is valid and not null
			long exitDateTime = AdhocTicket.getExitDateTime;
		assertEquals(ticket.STATE.CURRENT,EXITED );