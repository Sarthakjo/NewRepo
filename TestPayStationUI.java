/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bcccp.carpark.ICarpark;
import bcccp.carpark.paystation.IPaystationController;
import bcccp.carpark.paystation.IPaystationUI;
import bcccp.tickets.adhoc.AdhocTicket;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestPayStationUI {
    IPaystationController controller;
    String message;
    String carparkId;
    int tNo;
    long entryTime; 
    long paidTime;
    float charge; 
    String barcode;
     @BeforeMethod
    public void setUp() {
   controller = new IPayStationController();
   
    }
  
    @Test
   public class registerController(){
    this.controller = controller;
   

   Assert.assertEquals(controller, IPayStationController( ICarpark carpark, IPaystationUI ui))
    }
    @AfterMethod
	 public void tearDown() throws Exception {
		controller= null;
	}
     @Test
     private void testTicketInserted() {
		String ticketStr = barcodeTextField.getText();
		controller.ticketInserted(ticketStr);	
	}
     
      public void setUp() {
   
          printTicket= null;
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
