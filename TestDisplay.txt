package bcccp.tickets.test;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestPayStationDisplay {
  String message;
  display Display;
}

@Before
     public void setup(){
		display = new Display();
	 }
 @After
	public void tearDown() throws Exception {
		Display = null;
	}
@Test
public void display(String message) {
		displayTextField.setText(message);	
	if(message== getmessage){
            System.out.println("String message"+ getTicket);
            Assert.assertEquals(nessage), new Display);
}