package bcccp.tickets.test;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

 @RunWith(Parameterized.class)
    public class TestAdhocTicketEntryDateTime {
     private long entryDateTime;
	 STATE STATE.Current
 
     @Before
     public void setup(){
         entryDateTime = new EntryDateTime();
     STATE = STATE.Current();
	 }
	 
	 @After
	public void tearDown() throws Exception {
		entryDateTime = null;
	}
	 // when dateDate value is not null
	     @Test
        public void enter(long entryDateTime) {
			entryDateTime = dateDate;
			STATE = STATE.Current;
            System.out.println("Current Date Time:"+inputDateTime+inputSTATE.Current);
            assertEquals(entryDateTime.new EntryDateTime(inputSTATE.Current), expected);
        }
		// when dateDate value is null
		  @Test
        public void enter(long entryDateTime) {
			entryDateTime = null;
			STATE = STATE.Current;
            System.out.println("nothing to display");
            assertEquals(entryDateTime.dateDate(null), expected);
        }
	   
    }

