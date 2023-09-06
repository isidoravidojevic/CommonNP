package napredno.programiranje.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

	Product p;
	
	@BeforeEach
	void setUp() throws Exception {
		p = new Product();
	}

	@AfterEach
	void tearDown() throws Exception {
		p = null;
	}

	@Test
	void testSetProductID() {
		p.setProductID(1);
		assertEquals(1, p.getProductID());
	}

	@Test
	void testSetProductName() {
		p.setProductName("Slusalice");
		assertEquals("Slusalice", p.getProductName());
	}
	
	@Test
	void testSetQuantity() {
		p.setQuantity(50);
		assertEquals(50, p.getQuantity());
	}
	
	@Test
	void testSetMeasurementUnit() {
		p.setMeasurementUnit("PSC");
		assertEquals("PSC", p.getMeasurementUnit());
	}
	
	@Test
	void testSetPurchasePrice() {
		p.setPurchasePrice(40);
		assertEquals(40, p.getPurchasePrice());
	}
	
	@Test
	void testSetSellingPrice() {
		p.setSellingPrice(40);
		assertEquals(40, p.getSellingPrice());
	}
	
	@Test
	void testSetProducer() {
		Producer producer = new Producer(1L, "Apple"); 
		p.setProducer(producer);
		assertEquals(producer, p.getProducer());
	}
	
	@Test
	public void testGetTableName() {
		assertEquals("product", p.getTableName());
	}
	
	@Test
	public void testGetInsertColumns() {
		assertEquals("productName, quantity, measurementUnit, purchasePrice, sellingPrice, producerID", p.getInsertColumns());
	}
	
	@Test
	public void testGetInsertValues() {
		p.setProductName("Slusalice");
		p.setQuantity(5);
		p.setMeasurementUnit("PSC");
		p.setPurchasePrice(10.3);
		p.setSellingPrice(40.3);
		Producer producer = new Producer(2, "Apple");
		p.setProducer(producer);
		
		String expected = "'Slusalice',5,'PSC',10.3,40.3,2";

		assertEquals(expected, p.getInsertValues());
	}
	
	@Test
	public void testSetId() {
		long expectedId = 42;
		p.setId(expectedId);
		long actualId = p.getProductID();

		assertEquals(expectedId, actualId);
	}
	
	@Test
	public void testGetUpdateValues() {
		p.setProductName("Slusalice");
		p.setQuantity(5);
		p.setMeasurementUnit("PSC");
		p.setPurchasePrice(10.3);
		p.setSellingPrice(40.3);
		Producer producer = new Producer(2, "Apple");
		p.setProducer(producer);

		String expectedUpdateValues = "productName= 'Slusalice',quantity= 5,measurementUnit= 'PSC',purchasePrice= 10.3,sellingPrice= 40.3,producerID= 2";
		String actualUpdateValues = p.getUpdateValues();

		assertEquals(expectedUpdateValues, actualUpdateValues);
	}
	
	@Test
	public void testGetJoinText() {
		String expectedJoinText = " JOIN producer ON product.producerID = producer.producerID";
		String actualJoinText = p.getJoinText();

		assertEquals(expectedJoinText, actualJoinText);
	}
	
	@Test
	public void testGetSelectedText() {
		String expectedSelectedText = "";
		String actualSelectedText = p.getSelectedText();

		assertEquals(expectedSelectedText, actualSelectedText);
	}
	
	@Test
    public void testGetID() {
        p.setProductID(2); 
        
        String expectedID = "product.productID=2";
        String actualID = p.getID(); 
        
        assertEquals(expectedID, actualID); 
    }
	
	@Test
	public void testGetEntity() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_seminarski_db", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product JOIN producer ON product.producerID = producer.producerID WHERE productID = ?");
	    preparedStatement.setLong(1, 11);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    Product product = new Product();
	    Producer producer = new Producer(); 

	    GenericEntity entity = null;

	    if (resultSet.next()) {
	        entity = product.getEntity(resultSet);
	    }

	    assertNotNull(entity);

	    assertEquals(11L, ((Product) entity).getProductID());
	    assertEquals("iPhone", ((Product) entity).getProductName());
	    assertEquals(100, ((Product) entity).getQuantity());
	    assertEquals("PSC", ((Product) entity).getMeasurementUnit());
	    assertEquals(70000.0, ((Product) entity).getPurchasePrice(), 0.001); 
	    assertEquals(100000.0, ((Product) entity).getSellingPrice(), 0.001);

	    assertNotNull(((Product) entity).getProducer());
	    assertEquals(1L, ((Product) entity).getProducer().getProducerID());
	    assertEquals("Apple", ((Product) entity).getProducer().getProducerName());
	}
	
	
}
