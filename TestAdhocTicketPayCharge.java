package bcccp.tickets.test;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

 @RunWith(Parameterized.class)
    public class TestPayCharge {
 private DateTime DateTime
private	float charge charge
		state_  STATE.PAID_
     @Before
     public void setup(){
		paycharge = new Paycharge();
	 }
	 
	 @After
	public void tearDown() throws Exception {
		PayCharge = null;
	}
	 // when value is valid and not null
	     @Test
         public void pay(long paidDateTime, float charge) {
			PayCharge = payCharge.AdhocTicket( paidDateTime,charge);
            assertEquals(paycharge.new PayCharge(pay), );
        }
		// when value is null and invalid
		  @Test
         public void pay(long paidDateTime, float charge) {
			PayCharge = payCharge.AdhocTicket(charge);
            assertEquals(paycharge.new PayCharge(pay), );
        }
        }
	   
    }


 public void pay(long paidDateTime, float charge) {
		this.paidDateTime = paidDateTime;
		this.charge = charge;
		state_ = STATE.PAID;