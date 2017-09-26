package bcccp.tickets.test;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestPrintTicket {
  String message;
    String carparkId;
    int tNo;
    long entryTime; 
    long paidTime;
    float charge; 
    String barcode;
    printTicket PrintTicket;
}

@Before
     public void setup(){
		rintTicket = new PrintTicket();
	 }
 @After
	public void tearDown() throws Exception {
		PrintTicket = null;
	}
  @Test
     private void testPrintTicket() {
	String (carparkId)= getcarparkId;
        int tNo= getTNo ;
        long entryTime= getentryTime;
        long paidTime= getPaidTime;
        float charge= getCharge;
        String barcode= getBarcode;
                        
         assertTrue(sut instanceof IPayStationUI);
	 assertEquals(sut.getcarparkId(), carparkId);
         assertEquals(sut.gettNo(), tNo);
         assertEquals(sut.getEntryTime(), entryTime);
         assertEquals(sut.getPaidTime(), paidTimeDate;
         assertEquals(sut.getCharge(), charge);
}
