package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceItem implements GenericEntity{

	private long number;
    private Invoice invoice;
    private int quantity;
    private String description;
    private double itemPrice;
    private Product product;

    public InvoiceItem() {
    }

    public InvoiceItem(long number, Invoice invoice, int quantity, String description, double itemPrice, Product product) {
        this.number = number;
        this.invoice = invoice;
        this.quantity = quantity;
        this.description = description;
        this.itemPrice = itemPrice;
        this.product = product;
    }

    public long getNumber() {
        return number;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public double getItemPrice() {
        return quantity * product.getSellingPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    @Override
    public String getTableName() {
        return "invoiceitem";
    }

    @Override
    public String getInsertColumns() {
        return "invoiceNumber, quantity, description, itemPrice, productID";
    }

    @Override
    public String getInsertValues() {
        return invoice.getInvoiceNumber() + ", " + quantity + ", '" + description + "', " + itemPrice + ", " + product.getProductID();
    }

    @Override
    public void setId(long id) {
        number = id;
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getJoinText() {
        return " JOIN invoice ON invoiceitem.invoiceNumber = invoice.invoiceNumber JOIN product ON invoiceitem.productID = product.productID JOIN customer ON invoice.customerID = customer.customerID JOIN producer ON product.producerID = producer.producerID JOIN city ON customer.cityID = city.cityID";
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
        product = new Product();
        invoice = new Invoice();
        return new InvoiceItem(rs.getLong(getTableName() + ".number"), (Invoice) invoice.getEntity(rs),
            rs.getInt(getTableName() + ".quantity"), rs.getString(getTableName() + ".description"),
            rs.getDouble(getTableName() + ".itemPrice"), (Product) product.getEntity(rs));
    }
}
