package napredno.programiranje.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Predstavlja fakturu koja je kreirana. Faktura ima broj fakture, polje koje predstavlja status obradjenosti,
 * storniranosti, datum izdavanja, PDV, rabat, osnovicu za PDV, ukupna vrednost, rok placanja
 * i kupca za koga je izdata faktura.
 * Klasa implementira GenericEntity interfejs i omogucava rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class Invoice implements GenericEntity{

	/**
	 * Broj fakture kao long.
	 */
	private long invoiceNumber;
	
	/**
	 * Status fakture - da li je obradjena, kao boolean.
	 */
    private boolean processed;
    
    /**
	 * Status fakture - da li je stornirana, kao boolean.
	 */
    private boolean canceled;
    
    /**
	 * Datum izdavanja fakture kao LocalDate.
	 */
    private LocalDate issueDate;
    
    /**
	 * PDV kao double.
	 */
    private double VAT;
    
    /**
	 * Rabat kao double.
	 */
    private double rebate;
    
    /**
	 * Osnovica za PDV kao double.
	 */
    private double accountingBasis;
    
    /**
	 * Ukupna vrednost kao BigDecimal.
	 */
    private BigDecimal totalValue;
    
    /**
	 * Rok placanja fakture kao LocalDate.
	 */
    private LocalDate paymentDeadline;
    
    /**
	 * Kupac za koga je izdata faktura.
	 */
    private Customer customer;

    /**
     * Konstruktor bez parametara koji inicijalizuje objekat klase Invoice.
     */
    public Invoice() {
    }

    
    /**
     * Konstruktor koji inicijalizuje objekat klase Invoice sa zadatim vrednostima.
     *
     * @param invoiceNumber Broj fakture
     * @param processed Status fakture (obradjena ili ne)
     * @param canceled Status fakture (stornirana ili ne)
     * @param issueDate Datum izdavanja fakture
     * @param VAT PDV
     * @param rebate Rabat
     * @param accountingBasis Osnovica za PDV
     * @param totalValue Ukupna vrednost fakture
     * @param paymentDeadline Rok placanja
     * @param customer Kupac za koga je izdata faktura
     */
    public Invoice(long invoiceNumber, boolean processed, boolean canceled, LocalDate issueDate, double VAT, double rebate, double accountingBasis, BigDecimal totalValue, LocalDate paymentDeadline, Customer customer) {
        this.invoiceNumber = invoiceNumber;
        this.processed = processed;
        this.canceled = canceled;
        this.issueDate = issueDate;
        this.VAT = VAT;
        this.rebate = rebate;
        this.accountingBasis = accountingBasis;
        this.totalValue = totalValue;
        this.paymentDeadline = paymentDeadline;
        this.customer = customer;
    }
    
    /**
     * Vraca broj fakture.
     *
     * @return Broj fakture
     */
    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Proverava da li je faktura obradjena.
     *
     * @return True ako je faktura obradjena, u suprotnom False
     */
    public boolean isProcessed() {
        return processed;
    }

    /**
     * Proverava da li je faktura stornirana.
     *
     * @return True ako je faktura stornirana, u suprotnom False
     */
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Vraca datum izdavanja fakture.
     *
     * @return Datum izdavanja fakture
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Vraca PDV za fakturu.
     *
     * @return PDV za fakturu
     */
    public double getVAT() {
        return VAT;
    }

    /**
     * Vraca rabat za fakturu.
     *
     * @return Rabat za fakturu
     */
    public double getRebate() {
        return rebate;
    }

    
    /**
     * Vraca osnovicu za PDV za fakturu.
     *
     * @return Osnovica za PDV za fakturu
     */
    public double getAccountingBasis() {
        return accountingBasis;
    }

    /**
     * Vraca ukupnu vrednost fakture.
     *
     * @return Ukupna vrednost fakture
     */
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    /**
     * Vraca rok placanja fakture.
     *
     * @return Rok placanja fakture
     */
    public LocalDate getPaymentDeadline() {
        return paymentDeadline;
    }

    /**
     * Vraca kupca za kog je izdata faktura.
     *
     * @return Kupac za kog je izdata faktura
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Postavlja broj fakture na zadatu vrednost.
     *
     * @param invoiceNumber Broj fakture
     */
    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * Postavlja status fakture (obradjena ili ne).
     *
     * @param processed True ako je faktura obradjena, u suprotnom False
     */
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    /**
     * Postavlja status fakture (stornirana ili ne).
     *
     * @param canceled True ako je faktura stornirana, u suprotnom False
     */
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    /**
     * Postavlja datum izdavanja fakture na zadatu vrednost.
     *
     * @param issueDate Datum izdavanja fakture
     */
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Postavlja PDV za fakturu na zadatu vrednost.
     *
     * @param VAT PDV
     */
    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    /**
     * Postavlja rabat za fakturu na zadatu vrednost.
     *
     * @param rebate Rabat za fakturu
     */
    public void setRebate(double rebate) {
        this.rebate = rebate;
    }

    /**
     * Postavlja osnovicu za PDV za fakturu na zadatu vrednost.
     *
     * @param accountingBasis Osnovica za PDV za fakturu
     */
    public void setAccountingBasis(double accountingBasis) {
        this.accountingBasis = accountingBasis;
    }

    /**
     * Postavlja ukupnu vrednost fakture na zadatu vrednost.
     *
     * @param totalValue Ukupna vrednost fakture
     */
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * Postavlja rok placanja fakture na zadatu vrednost.
     *
     * @param paymentDeadline Rok placanja fakture
     */
    public void setPaymentDeadline(LocalDate paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    /**
     * Postavlja kupca za kog je izdata faktura na zadatu vrednost.
     *
     * @param customer Kupac za kog je izdata faktura
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTableName() {
        return "invoice";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInsertColumns() {
        return "processed, canceled, issueDate, VAT, rebate, accountingBasis, totalValue, paymentDeadline, customerID";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInsertValues() {
        return processed + ", " + canceled + ", '" + Date.valueOf(issueDate) + "', " + VAT + ", " + rebate + ", " + accountingBasis
                + ", " + totalValue + ", '" + Date.valueOf(paymentDeadline) + "', " + customer.getCustomerID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(long id) {
        invoiceNumber = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUpdateValues() {
        return "canceled = "+ canceled ;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getJoinText() {
        return " JOIN customer ON invoice.customerID = customer.customerID JOIN city ON customer.cityID = city.cityID";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSelectedText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getID() {
        return "invoice.invoiceNumber = " + invoiceNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
        customer = new Customer();
        return new Invoice(rs.getLong(getTableName() + ".invoiceNumber"), rs.getBoolean(getTableName() + ".processed"),
                rs.getBoolean(getTableName() + ".canceled"), rs.getDate(getTableName() + ".issueDate").toLocalDate(),
                rs.getDouble(getTableName() + ".VAT"), rs.getDouble(getTableName() + ".rebate"),
                rs.getDouble(getTableName() + ".accountingBasis"), rs.getBigDecimal(getTableName() + ".totalValue"),
                rs.getDate(getTableName() + ".paymentDeadline").toLocalDate(), (Customer) customer.getEntity(rs));
    }
}
