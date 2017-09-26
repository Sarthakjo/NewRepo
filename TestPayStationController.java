
package bcccp.tickets.test;

import bcccp.carpark.paystation.PaystationController;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
	
public class TestPayStationController{
        
        private STATE state_;
	
	private IPaystationUI ui_;
	
	private ICarpark carpark_;

	private IAdhocTicket  adhocTicket_ = null;
	private float charge_;
        payStationController PayStationController;

@Before
     public void setup(){
		payStationController  = new PayStationController ();
	 }
 @After
	public void tearDown() throws Exception {
		payStationController = null;
	}
        
        
@Test
        private void TestState(PaystationController.STATE newState) {
		if(state_ = PaystationController.STATE.Waiting){
                Assert.assertEquals (ui_.display("Waiting"));
		System.out.println("State"+ ui.display);
}
 @Test
private void TestState(PaystationController.STATE newState) {
		if(state_ = PaystationController.STATE.Rejected){
                Assert.assertEquals (ui_.display("Rejected"));
		System.out.println("State"+ ui.display);
}
              
@Test
private void TestState(PaystationController.STATE newState) {
		if(state_ = PaystationController.STATE.IDLE){
                Assert.assertEquals (ui_.display("Idle"));
		System.out.println("State"+ ui.display);
}
                