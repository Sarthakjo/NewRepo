package bcccp.tickets.test;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class TestFindTicket {
    private String barcode;
    findTicket FindTicket;
}

@Before
     public void setup(){
		findTicket = new FindTicket();
	 }
 @After
	public void tearDown() throws Exception {
		FindTicket = null;
	}
 @Test
        public void testFindTicketByBarcode(){
            if(barcode!0){
            System.out.println("Find Ticket with barcode"+ FindTicket);
            Assert.assertEquals(barcode), new FindTicket);
        }
                
         @Test
        public void testFindTicketByBarcode(){
            if(barcode==0){
            System.out.println("Find Ticket with barcode"+ FindTicket);
            Assert.assertEquals(invalidBarcode), new FindTicket);
        }