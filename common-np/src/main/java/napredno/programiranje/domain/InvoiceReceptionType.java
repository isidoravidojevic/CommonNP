package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceReceptionType implements GenericEntity{

	private Customer customer;
    private Invoice invoice;
    private String invoiceReceptionMeans;

    public InvoiceReceptionType() {
    }

    public InvoiceReceptionType(Customer customer, Invoice invoice, String invoiceReceptionMeans) {
        this.customer = customer;
        this.invoice = invoice;
        this.invoiceReceptionMeans = invoiceReceptionMeans;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public String getInvoiceReceptionMeans() {
        return invoiceReceptionMeans;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setInvoiceReceptionMeans(String invoiceReceptionMeans) {
        this.invoiceReceptionMeans = invoiceReceptionMeans;
    }

    @Override
    public String getTableName() {
        return "invoicereceptiontype";
    }

    @Override
    public String getInsertColumns() {
        return "customerID, invoiceNumber, invoiceReceptionMeans";
    }

    @Override
    public String getInsertValues() {
        return customer.getCustomerID()+ ", " + invoice.getInvoiceNumber() + ", '" + invoiceReceptionMeans + "'";
    }

    @Override
    public void setId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getJoinText() {
        return " JOIN customer cu ON invoicereceptiontype.customerID = cu.customerID JOIN invoice ON invoicereceptiontype.invoiceNumber = invoice.invoiceNumber JOIN customer c ON invoice.customerID = c.customerID JOIN city ON cu.cityID = city.cityID";
//        return " return \" JOIN customer ON invoicereceptiontype.customerID = customer.customerID JOIN invoice ON invoicereceptiontype.invoiceNumber = invoice.invoiceNumber JOIN customer ON invoice.customerID = customer.customerID";
    }

    @Override
    public String getSelectedText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
        customer = new Customer();
        invoice = new Invoice();
        return new InvoiceReceptionType((Customer) customer.getEntity(rs), (Invoice) invoice.getEntity(rs), 
            rs.getString(getTableName() + ".invoiceReceptionMeans"));
    }
	
}
