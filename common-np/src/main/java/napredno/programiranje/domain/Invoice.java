package napredno.programiranje.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Invoice implements GenericEntity{

	private long invoiceNumber;
    private boolean processed;
    private boolean canceled;
    private LocalDate issueDate;
    private double VAT;
    private double rebate;
    private double accountingBasis;
    private BigDecimal totalValue;
    private LocalDate paymentDeadline;
    private Customer customer;

    public Invoice() {
    }

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
    
    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public boolean isProcessed() {
        return processed;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public double getVAT() {
        return VAT;
    }

    public double getRebate() {
        return rebate;
    }

    public double getAccountingBasis() {
        return accountingBasis;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public LocalDate getPaymentDeadline() {
        return paymentDeadline;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public void setIssueDate(LocalDate dateOfIssue) {
        this.issueDate = dateOfIssue;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public void setRebate(double rebate) {
        this.rebate = rebate;
    }

    public void setAccountingBasis(double accountingBasis) {
        this.accountingBasis = accountingBasis;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public void setPaymentDeadline(LocalDate paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @Override
    public String getTableName() {
        return "invoice";
    }

    @Override
    public String getInsertColumns() {
        return "processed, canceled, issueDate, VAT, rebate, accountingBasis, totalValue, paymentDeadline, customerID";
    }

    @Override
    public String getInsertValues() {
        return processed + ", " + canceled + ", '" + Date.valueOf(issueDate) + "', " + VAT + ", " + rebate + ", " + accountingBasis
                + ", " + totalValue + ", '" + Date.valueOf(paymentDeadline) + "', " + customer.getCustomerID();
    }

    @Override
    public void setId(long id) {
        invoiceNumber = id;
    }

    @Override
    public String getUpdateValues() {
        return "canceled = "+ canceled ;
    }

    @Override
    public String getJoinText() {
        return " JOIN customer ON invoice.customerID = customer.customerID JOIN city ON customer.cityID = city.cityID";
    }

    @Override
    public String getSelectedText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID() {
        return "invoice.invoiceNumber = " + invoiceNumber;
    }

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
