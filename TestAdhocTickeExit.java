package bcccp.tickets.test;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

 @RunWith(Parameterized.class)
    public class TestExitCarpark {

     @Before
     public void setup(){
		exitCarpark = new ExitCarpark();
	 }
	 
	 @After
	public void tearDown() throws Exception {
		ExitCarpark = null;
	}
	
	 // when value is valid and not null
	     @Test
         public void exit(long DateTime) {
			ExitCarpark = exitCarpark.AdhocTicket(DateTime);
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