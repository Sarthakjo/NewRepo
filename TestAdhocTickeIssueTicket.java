package bcccp.tickets.test;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

 @RunWith(Parameterized.class)
    public class TestAdhocTicketIssueTicket {
   private String carparkId_;
	private int ticketNo_;
	private String barcode;
	issueTicket IssueTicket;
 
     @Before
     public void setup(){
		issueTicket = new IssueTicket();
	 }
	 
	 @After
	public void tearDown() throws Exception {
		IssueTicket = null;
	}
	 // when value is valid and not null
	     @Test
        public void AdhocTicket(String carparkId, int ticketNo,String barcode){
			IssueTicket = issueTicket.AdhocTicket(carparkId, ticketNo, barcode)
            assertEquals(issueTicket.new IssueTicket(AdhocTicket), );
        }
		// when value is null and invalid
		  @Test
        public void AdhocTicket(String carparkId, int ticketNo,String barcode){
			IssueTicket = issueTicket.AdhocTicket(carparkId, ticketNo, barcode)
            assertEquals(issueTicket.new IssueTicket(AdhocTicket), );
        };
        }
	   
    }

