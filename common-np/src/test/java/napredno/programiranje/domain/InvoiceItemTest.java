package napredno.programiranje.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceItemTest {

	InvoiceItem i;
	
	@BeforeEach
	void setUp() throws Exception {
		i = new InvoiceItem();
	}

	@AfterEach
	void tearDown() throws Exception {
		i = null;
	}

	@Test
	void testSetNumber() {
		i.setNumber(2);
		assertEquals(2, i.getNumber());
	}

	@Test
	void testSetInvoice() {
		Invoice invoice = new Invoice();
		i.setInvoice(invoice);
		assertEquals(invoice, i.getInvoice());
	}
	
	@Test
	void testSetQuantity() {
		i.setQuantity(30);
		assertEquals(30, i.getQuantity());
	}
	
	@Test
	void testSetDescription() {
		i.setDescription("Opis");
		assertEquals("Opis", i.getDescription());
	}
	
	@Test
    public void testGetItemPrice() {
        i.setQuantity(5);
        
        Product product = new Product();
        product.setSellingPrice(10.0);
        i.setProduct(product);

        double itemPrice = i.getItemPrice();
        double expectedItemPrice = 50.0;

        assertEquals(expectedItemPrice, itemPrice, 0.001);
    }
	
	@Test
	void testSetProduct() {
		Product product = new Product();
		i.setProduct(product);
		assertEquals(product, i.getProduct());
	}
	
	@Test
	public void testGetTableName() {
		assertEquals("invoiceitem", i.getTableName());
	}
	
	@Test
	public void testGetInsertColumns() {
		assertEquals(
				"invoiceNumber, quantity, description, itemPrice, productID",
				i.getInsertColumns());
	}
	
	@Test
    public void testGetInsertValues() {
		Invoice invoice = new Invoice();
		i.setInvoice(invoice);
        i.setQuantity(5);
        i.setDescription("Opis");
        i.setItemPrice(10.0);
        
        Product product = new Product();
        i.setProduct(product);

        String insertValues = i.getInsertValues();
        String expectedInsertValues = "0, 5, 'Opis', 10.0, 0";
        
        assertEquals(expectedInsertValues, insertValues);
    }

	@Test
	public void testSetId() {
		long expectedId = 4;
		i.setId(expectedId);
		long actualId = i.getNumber();

		assertEquals(expectedId, actualId);
	}

	@Test
    public void testGetUpdateValues() {
        String expectedUpdateValues = "";

        assertEquals(expectedUpdateValues, i.getUpdateValues());
    }

	@Test
	public void testGetJoinText() {
		String expectedJoinText = " JOIN invoice ON invoiceitem.invoiceNumber = invoice.invoiceNumber JOIN product ON invoiceitem.productID = product.productID JOIN customer ON invoice.customerID = customer.customerID JOIN producer ON product.producerID = producer.producerID JOIN city ON customer.cityID = city.cityID";
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
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoiceitem JOIN invoice ON invoiceitem.invoiceNumber = invoice.invoiceNumber JOIN product ON invoiceitem.productID = product.productID JOIN customer ON invoice.customerID = customer.customerID JOIN producer ON product.producerID = producer.producerID JOIN city ON customer.cityID = city.cityID WHERE number = ?");
	    preparedStatement.setLong(1, 14); 

	    ResultSet resultSet = preparedStatement.executeQuery();

	    Product product = new Product();
	    Invoice invoice = new Invoice();
	    InvoiceItem invoiceItem = new InvoiceItem();
	    GenericEntity entity = null;

	    if (resultSet.next()) {
	        entity = invoiceItem.getEntity(resultSet);
	    }

	    assertNotNull(entity);

	    assertEquals(14L, ((InvoiceItem) entity).getNumber());
	    assertEquals(100, ((InvoiceItem) entity).getQuantity());
	    assertEquals("neki opis", ((InvoiceItem) entity).getDescription());
	    assertEquals(10000000.0, ((InvoiceItem) entity).getItemPrice(), 0.001);

	    assertNotNull(((InvoiceItem) entity).getInvoice());
	    assertEquals(23L, ((InvoiceItem) entity).getInvoice().getInvoiceNumber());

	    assertNotNull(((InvoiceItem) entity).getProduct());
	    assertEquals(11L, ((InvoiceItem) entity).getProduct().getProductID());
	    assertEquals("iPhone", ((InvoiceItem) entity).getProduct().getProductName());
	    assertEquals(100, ((InvoiceItem) entity).getProduct().getQuantity());
	    assertEquals("PSC", ((InvoiceItem) entity).getProduct().getMeasurementUnit());
	    assertEquals(70000.0, ((InvoiceItem) entity).getProduct().getPurchasePrice(), 0.001);
	    assertEquals(100000.0, ((InvoiceItem) entity).getProduct().getSellingPrice(), 0.001);
	}

	@Test
	public void testGetEntityThrowsSqlSyntaxErrorException() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_napredno_programiranje", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoiceit JOIN invoice ON invoiceitem.invoiceNumber = invoice.invoiceNumber JOIN product ON invoiceitem.productID = product.productID JOIN customer ON invoice.customerID = customer.customerID JOIN producer ON product.producerID = producer.producerID JOIN city ON customer.cityID = city.cityID WHERE number = ?");
	    preparedStatement.setLong(1, 14);

	    assertThrows(SQLSyntaxErrorException.class, () -> preparedStatement.executeQuery());
	}
}
