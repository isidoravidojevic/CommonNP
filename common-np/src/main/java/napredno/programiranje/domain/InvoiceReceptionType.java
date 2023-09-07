package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Predstavlja tip prijema fakture. Tip prijema fakture ima kupca na kog se
 * odnosi, fakturu na koju se odnosi i sredstvo prijema fakture. Klasa
 * implementira GenericEntity interfejs i omogucava rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class InvoiceReceptionType implements GenericEntity {

	/**
	 * Kupac na kog se odnosi tip prijema fakture.
	 */
	private Customer customer;

	/**
	 * Faktura na koju se odnosi tip prijema fakture.
	 */
	private Invoice invoice;

	/**
	 * Sredstvo(nacin) prijema fakture.
	 */
	private String invoiceReceptionMeans;

	/**
	 * Konstruktor bez parametara koji inicijalizuje objekat klase
	 * InvoiceReceptionType.
	 */
	public InvoiceReceptionType() {
	}

	/**
	 * Konstruktor koji inicijalizuje objekat klase InvoiceReceptionType sa zadatim
	 * vrednostima.
	 *
	 * @param customer              Kupac
	 * @param invoice               Faktura
	 * @param invoiceReceptionMeans Sredstvo prijema fakture
	 */
	public InvoiceReceptionType(Customer customer, Invoice invoice, String invoiceReceptionMeans) {
		setCustomer(customer);
		setInvoice(invoice);
		setInvoiceReceptionMeans(invoiceReceptionMeans);
	}

	/**
	 * Vraca kupca na kog se odnosi tip prijema fakture.
	 *
	 * @return Kupac na kog se odnosi tip prijema fakture
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Vraca fakturu na koju se odnosi tip prijema fakture.
	 *
	 * @return Faktura na koju se odnosi tip prijema fakture
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * Vraca sredstvoprijema fakture.
	 *
	 * @return Sredstvo prijema fakture
	 */
	public String getInvoiceReceptionMeans() {
		return invoiceReceptionMeans;
	}

	/**
	 * Postavlja kupca na kog se odnosi tip prijema fakture na zadatu vrednost.
	 *
	 * @param customer Kupac na kog se odnosi tip prijema fakture
	 * @throws IllegalArgumentException Ako je customer null
	 */
	public void setCustomer(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("Kupac ne sme imati null vrednost.");
		}
		this.customer = customer;
	}

	/**
	 * Postavlja fakturu na koju se odnosi tip prijema na zadatu vrednost.
	 *
	 * @param invoice Faktura na koju se odnosti tip prijema
	 * @throws IllegalArgumentException Ako je invoice null
	 */
	public void setInvoice(Invoice invoice) {
		if (invoice == null) {
			throw new IllegalArgumentException("Faktura ne sme imati null vrednost.");
		}
		this.invoice = invoice;
	}

	/**
	 * Postavlja sredstvo prijema fakture na zadatu vrednost.
	 *
	 * @param invoiceReceptionMeans Sredstvo prijema fakture
	 * @throws IllegalArgumentException Ako je invoiceReceptionMeans null ili prazan String
	 */
	public void setInvoiceReceptionMeans(String invoiceReceptionMeans) {
		if (invoiceReceptionMeans != null && !invoiceReceptionMeans.isEmpty()) {
	        this.invoiceReceptionMeans = invoiceReceptionMeans;
	    } else {
	        throw new IllegalArgumentException("Sredstvo prijema fakture ne sme biti null ili prazan String.");
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTableName() {
		return "invoicereceptiontype";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInsertColumns() {
		return "customerID, invoiceNumber, invoiceReceptionMeans";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInsertValues() {
		return customer.getCustomerID() + ", " + invoice.getInvoiceNumber() + ", '" + invoiceReceptionMeans + "'";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(long id) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUpdateValues() {
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJoinText() {
		return " JOIN customer cu ON invoicereceptiontype.customerID = cu.customerID JOIN invoice ON invoicereceptiontype.invoiceNumber = invoice.invoiceNumber JOIN customer c ON invoice.customerID = c.customerID JOIN city ON cu.cityID = city.cityID";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSelectedText() {
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getID() {
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericEntity getEntity(ResultSet rs) throws SQLException {
		customer = new Customer();
		invoice = new Invoice();
		return new InvoiceReceptionType((Customer) customer.getEntity(rs), (Invoice) invoice.getEntity(rs),
				rs.getString(getTableName() + ".invoiceReceptionMeans"));
	}

}
