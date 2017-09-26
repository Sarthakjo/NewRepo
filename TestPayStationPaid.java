package bcccp.tickets.test;

import bcccp.carpark.paystation.PaystationController;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestPayStationPaid{
  private enum STATE { IDLE, WAITING, REJECTED, PAID } 
	
	private STATE state_;
       statePaid StatePaid ;


@Before
     public void setup(){
		StatePaid = new StatePaid ();
	 }
 @After
	public void tearDown() throws Exception {
		stateRejected = null;
	}
@Test
private void TestState(PaystationController.STATE newState) {
		if(state_ = PaystationController.STATE.Paid){
                Assert.assertEquals (ui_.display("Paid"));
		System.out.println("State"+ ui.display);
}
}

			
			