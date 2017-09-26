
package bcccp.tickets.adhoc;

public class AdhocTicketFactory implements IAdhocTicketFactory {

	@Override
	public IAdhocTicket make(String carparkId, int ticketNo) {
		String barcode = "A" + Integer.toHexString(ticketNo);
		return new AdhocTicket(carparkId, ticketNo, barcode);
	}

}
=======
package bcccp.tickets.adhoc;

public class AdhocTicketFactory implements IAdhocTicketFactory {

	@Override
	public IAdhocTicket make(String carparkId, int ticketNo) {
		// TODO Auto-generated method stub
		return null;
	}


}

