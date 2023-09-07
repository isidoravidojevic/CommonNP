package napredno.programiranje.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceReceptionTypeTest {

	InvoiceReceptionType i;
	
	@BeforeEach
	void setUp() throws Exception {
		i = new InvoiceReceptionType();
	}

	@AfterEach
	void tearDown() throws Exception {
		i = null;
	}

	@Test
	void testSetCustomer() {
		Customer customer = new Customer();
		i.setCustomer(customer);
		assertEquals(customer, i.getCustomer());
	}
	
	@Test
	void testSetInvoice() {
		Invoice invoice = new Invoice();
		i.setInvoice(invoice);
		assertEquals(invoice, i.getInvoice());
	}

	@Test
	void testSetInvoiceReceptionMeans() {
		i.setInvoiceReceptionMeans("LICNO");
		assertEquals("LICNO", i.getInvoiceReceptionMeans());
	}
	
	@Test
	public void testGetTableName() {
		assertEquals("invoicereceptiontype", i.getTableName());
	}
	
	@Test
	public void testGetInsertColumns() {
		assertEquals("customerID, invoiceNumber, invoiceReceptionMeans", i.getInsertColumns());
	}
	
	@Test
	public void testGetInsertValues() {
		Customer customer = new Customer();
		customer.setId(1);
		Invoice invoice = new Invoice();
		invoice.setId(1);
		i.setCustomer(customer);
		i.setInvoice(invoice);
		i.setInvoiceReceptionMeans("LICNO");
		
		String expected = "1, 1, 'LICNO'";

		assertEquals(expected, i.getInsertValues());
	}
	
	@Test
	public void testGetUpdateValues() {

		String expectedUpdateValues = "";
		String actualUpdateValues = i.getUpdateValues();

		assertEquals(expectedUpdateValues, actualUpdateValues);
	}
	
	@Test
	public void testGetJoinText() {
		String expectedJoinText = " JOIN customer cu ON invoicereceptiontype.customerID = cu.customerID JOIN invoice ON invoicereceptiontype.invoiceNumber = invoice.invoiceNumber JOIN customer c ON invoice.customerID = c.customerID JOIN city ON cu.cityID = city.cityID";
		String actualJoinText = i.getJoinText();

		assertEquals(expectedJoinText, actualJoinText);
	}
	
	@Test
	public void testGetSelectedText() {
		String expectedSelectedText = "";
		String actualSelectedText = i.getSelectedText();

		assertEquals(expectedSelectedText, actualSelectedText);
	}
	
	@Test
    public void testGetID() { 
        String expectedID = "";
        String actualID = i.getID(); 
        
        assertEquals(expectedID, actualID); 
    }
	
	@Test
	public void testGetEntity() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_seminarski_db", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoicereceptiontype JOIN customer cu ON invoicereceptiontype.customerID = cu.customerID JOIN invoice ON invoicereceptiontype.invoiceNumber = invoice.invoiceNumber JOIN customer ON invoice.customerID = customer.customerID JOIN city ON cu.cityID = city.cityID WHERE invoicereceptiontype.customerID = ? AND invoicereceptiontype.invoiceNumber = ?");
	    preparedStatement.setLong(1, 15);
	    preparedStatement.setLong(2, 24);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    Customer customer = new Customer();
	    Invoice invoice = new Invoice();
	    InvoiceReceptionType invoiceReceptionType = new InvoiceReceptionType();
	    GenericEntity entity = null;

	    if (resultSet.next()) {
	        entity = invoiceReceptionType.getEntity(resultSet);
	    }

	    assertNotNull(entity);

	    assertEquals("ELEKTRONSKI", ((InvoiceReceptionType) entity).getInvoiceReceptionMeans());

	    assertNotNull(((InvoiceReceptionType) entity).getCustomer());
	    assertEquals(15, ((InvoiceReceptionType) entity).getCustomer().getCustomerID());
	    assertEquals("Tehnomanija", ((InvoiceReceptionType) entity).getCustomer().getCustomerName());
	    assertEquals("Zicka 1", ((InvoiceReceptionType) entity).getCustomer().getAddress());
	    assertEquals("123456789", ((InvoiceReceptionType) entity).getCustomer().getVATnumber());
	    assertEquals("12345678", ((InvoiceReceptionType) entity).getCustomer().getCompanyNumber());
	    assertEquals(2, ((InvoiceReceptionType) entity).getCustomer().getCity().getCityID());

	    assertNotNull(((InvoiceReceptionType) entity).getInvoice());
	    assertEquals(24, ((InvoiceReceptionType) entity).getInvoice().getInvoiceNumber());
	    assertTrue(((InvoiceReceptionType) entity).getInvoice().isProcessed());
	    assertFalse(((InvoiceReceptionType) entity).getInvoice().isCanceled());
	    assertEquals(LocalDate.of(2000, 12, 15), ((InvoiceReceptionType) entity).getInvoice().getIssueDate());
	    assertEquals(20.0, ((InvoiceReceptionType) entity).getInvoice().getVAT(), 0.001);
	    assertEquals(4.0, ((InvoiceReceptionType) entity).getInvoice().getRebate(), 0.001);
	    assertEquals(163200.0, ((InvoiceReceptionType) entity).getInvoice().getAccountingBasis(), 0.001);
	    assertEquals(new BigDecimal("195840"), ((InvoiceReceptionType) entity).getInvoice().getTotalValue());
	    assertEquals(LocalDate.of(2000, 12, 17), ((InvoiceReceptionType) entity).getInvoice().getPaymentDeadline());
	}

	@Test
	public void testGetEntityThrowsSqlSyntaxErrorException() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_napredno_programiranje", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoicereception JOIN customer cu ON invoicereceptiontype.customerID = cu.customerID JOIN invoice ON invoicereceptiontype.invoiceNumber = invoice.invoiceNumber JOIN customer ON invoice.customerID = customer.customerID JOIN city ON cu.cityID = city.cityID WHERE invoicereceptiontype.customerID = ? AND invoicereceptiontype.invoiceNumber = ?");
	    preparedStatement.setLong(1, 15);
	    preparedStatement.setLong(2, 24);

	    assertThrows(SQLSyntaxErrorException.class, () -> preparedStatement.executeQuery());
	}
}
