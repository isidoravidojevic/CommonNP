package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Predstavlja stavku fakture. Stavke fakture ima redni broj stavke, fakturu na koju se odnosi,
 *  kolicinu proizvoda u okviru stavke, opis, cenu stavke i proizvod na koji se odnosi.
 * Klasa implementira GenericEntity interfejs i omogucava rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class InvoiceItem implements GenericEntity{

	/**
	 * Redni broj stavke kao long.
	 */
	private long number;
	
	/**
	 * Faktura na koju se odnosi.
	 */
    private Invoice invoice;
    
    /**
	 * Kolicina proizvoda u okviru stavke kao int.
	 */
    private int quantity;
    
    /**
	 * Opis stavke kao String.
	 */
    private String description;
    
    /**
	 * Cena stavke kao double.
	 */
    private double itemPrice;
    
    /**
	 * Proizvod za koji je vezana stavka.
	 */
    private Product product;

    /**
     * Konstruktor bez parametara koji inicijalizuje objekat klase InvoiceItem.
     */
    public InvoiceItem() {
    }

    
    /**
     * Konstruktor koji inicijalizuje objekat klase InvoiceItem sa zadatim vrednostima.
     *
     * @param number Redni broj stavke
     * @param invoice Faktura na koju se odnosi stavka
     * @param quantity Kolicina proizvoda u okviru stavke
     * @param description Opis stavke
     * @param itemPrice Cena stavke
     * @param product Proizvod na koji se odnosi stavka
     */
    public InvoiceItem(long number, Invoice invoice, int quantity, String description, double itemPrice, Product product) {
        this.number = number;
        this.invoice = invoice;
        this.quantity = quantity;
        this.description = description;
        this.itemPrice = itemPrice;
        this.product = product;
    }

    
    /**
     * Vraca redni broj stavke.
     *
     * @return Redni broj stavke
     */
    public long getNumber() {
        return number;
    }

    /**
     * Vraca fakturu na koju se odnosi.
     *
     * @return Faktura
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * Vraca kolicinu proizvoda u okviru stavke.
     *
     * @return Kolicina proizvoda
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Vraca opis stavke.
     *
     * @return Opis stavke
     */
    public String getDescription() {
        return description;
    }

    /**
     * Vraca cenu stavke.
     *
     * @return Cena stavke
     */
    public double getItemPrice() {
        return quantity * product.getSellingPrice();
    }

    /**
     * Vraca proizvod na koji se odnosi stavka.
     *
     * @return Proizvod 
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Postavlja redni broj stavke na zadatu vrednost.
     *
     * @param number Redni broj stavke
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Postavlja fakturu na koju se stavka odnosi na zadatu vrednost.
     *
     * @param invoice Faktura na koju se odnosi stavka
     */
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    /**
     * Postavlja kolicinu proizvoda u okviru stavke na zadatu vrednost.
     *
     * @param quantity Kolicina proizvoda u okviru stavke
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Postavlja opis stavke na zadatu vrednost.
     *
     * @param description Opis stavke
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Postavlja cenu stavke na zadatu vrednost.
     *
     * @param itemPrice Cena stavke
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Postavlja proizvod na koji se stavka odnosi na zadatu vrednost.
     *
     * @param product Proizvod na koji se stavka odnosi
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTableName() {
        return "invoiceitem";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInsertColumns() {
        return "invoiceNumber, quantity, description, itemPrice, productID";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInsertValues() {
        return invoice.getInvoiceNumber() + ", " + quantity + ", '" + description + "', " + itemPrice + ", " + product.getProductID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(long id) {
        number = id;
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
        return " JOIN invoice ON invoiceitem.invoiceNumber = invoice.invoiceNumber JOIN product ON invoiceitem.productID = product.productID JOIN customer ON invoice.customerID = customer.customerID JOIN producer ON product.producerID = producer.producerID JOIN city ON customer.cityID = city.cityID";
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
        product = new Product();
        invoice = new Invoice();
        return new InvoiceItem(rs.getLong(getTableName() + ".number"), (Invoice) invoice.getEntity(rs),
            rs.getInt(getTableName() + ".quantity"), rs.getString(getTableName() + ".description"),
            rs.getDouble(getTableName() + ".itemPrice"), (Product) product.getEntity(rs));
    }
}
