package test;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdhocTicketDAO  {

	private Map<String, IAdhocTicket> currentTickets;
	private IAdhocTicketFactory adhocTicketFactory_;
	private int currentTicketNo;
	
	AdhocTicketDAO ticket;
	
	@Before
	public void setUp() throws Exception {
		AdhocTicketMap = spy(new HashMap<Integer, IAdhocTicket>());
		AdhocTicketFactory = test(IAdhocTicketFactory.class);
		currentTicketNo = test(IcurrentTicketNo.class);
		
		
		ticket = new AdhocTicketDAO(AdhocTicketMap, AdhocTicketFactory, currentTicketNo);
	}
	

	
	@After
	public void tearDown() throws Exception {
		ticket = null;
	}
	@Test
	public void testIAdhocTicket createTicket() {
		//setup
		when(currentTicketNo.getBarcode()).thenReturn(ticket);
		when(currentTicketNo.makeAdhocTicket(carparkId,Barcode)
			.thenReturn(AdhocTicket);
		
		//execute
		IAdhocTicket actual = ticket.createTicket(carparkId, barcode);
		
		//verify
		verify(currentTicketNo).barcode();
		verify(currentTicketNo).makeTicket(AdhocTicketMap, AdhocTicketFactory, currentTicketNo);
		assertEquals(AdhocTicket, actual);		
	}