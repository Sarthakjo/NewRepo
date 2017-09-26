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

public class TestPayStationRejected{
  private enum STATE { IDLE, WAITING, REJECTED, PAID } 
	
	private STATE state_;
       stateRejected StateRejected ;


@Before
     public void setup(){
		StateRejected = new StateRejected ();
	 }
 @After
	public void tearDown() throws Exception {
		stateRejected = null;
	}
@Test
private void TestState(PaystationController.STATE newState) {
		if(state_ = PaystationController.STATE.Rejected){
                Assert.assertEquals (ui_.display("Rejected"));
		System.out.println("State"+ ui.display);
}
}

			
			