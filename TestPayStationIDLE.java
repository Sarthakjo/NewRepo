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

public class TestPayStationIDLE{
  private enum STATE { IDLE, WAITING, REJECTED, PAID } 
	
	private STATE state_;
       stateIDLE StateIDLE ;


@Before
     public void setup(){
		StateIDLE  = new StateIDLE ();
	 }
 @After
	public void tearDown() throws Exception {
		stateIDLE = null;
	}
@Test
private void TestState(PaystationController.STATE newState) {
		if(state_ = PaystationController.STATE.IDLE){
                Assert.assertEquals (ui_.display("Idle"));
		System.out.println("State"+ ui.display);
}
}

			
			