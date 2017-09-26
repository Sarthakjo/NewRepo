
package bcccp.carpark;

import bcccp.tickets.adhoc.AdhocTicketDAO;
import bcccp.tickets.adhoc.IAdhocTicketDAO;
import bcccp.tickets.season.ISeasonTicketDAO;
import bcccp.tickets.season.SeasonTicketDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CarparkTest {
    ICarpark mockCarpark;
    ISeasonTicketDAO mockSeasonTicketDAO;
    IAdhocTicketDAO mockAdhocTicketDAO;
    String name;
    List<ICarparkObserver> observers;
    private static Field observerField;
    int capacity;
    private static Field carparkIdField;




    @Before
    public void setUp() throws Exception {
        mockSeasonTicketDAO= mock(SeasonTicketDAO.class);
        mockAdhocTicketDAO= mock(AdhocTicketDAO.class);
        mockCarpark= new Carpark(name, capacity, mockAdhocTicketDAO, mockSeasonTicketDAO);
        observerField = Carpark.class.getDeclaredField("observers");
        observerField.setAccessible(true);
        carparkIdField= Carpark.class.getDeclaredField("carparkId");
        carparkIdField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
    }
    

    @Test
    public void testGetName(){
        assertEquals(name, mockCarpark.getName());
    }

    @Test
    public void testIssueAdhocTicket(){
        assertEquals(mockAdhocTicketDAO.createTicket(carparkIdField.toString()), mockCarpark.issueAdhocTicket());
    }

}