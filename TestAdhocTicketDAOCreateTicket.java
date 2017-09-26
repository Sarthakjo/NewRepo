package bcccp.tickets.test;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

 @RunWith(Parameterized.class)
    public class TestAdhocTicketDAO {
	private Map<String, IAdhocTicket> currentTickets;
	private IAdhocTicketFactory adhocTicketFactory_;
	private int currentTicketNo;
	createTicket CreateTicket();
     @Before
     public void setup(){
		createTicket = new CreateTicket();
	 }
	 
	 @After
	public void tearDown() throws Exception {
		CreateTicket = null;
	}
	
	 // when value is valid and not null
	     @Test
       public IAdhocTicket createTicket(String carparkId) {
			IAdhocTicket ticket = adhocTicketFactory_.make(carparkId, ++currentTicketNo);
		currentTickets.put(ticket.getBarcode(), ticket);
		return ticket;
            assertEquals(exitCarpark.new ExitCarpark.PayCharge(exit) );
        }
		// when value is null and invalid
		  @Test
         public void exit(long DateTime) {
			ExitCarpark = exitCarpark.AdhocTicket(DateTime);
            assertEquals(exitCarpark.new ExitCarpark.PayCharge(exit) );
        }
        }
	   
    }


 public void pay(long paidDateTime, float charge) {
		this.paidDateTime = paidDateTime;
		this.charge = charge;
		state_ = STATE.PAID;