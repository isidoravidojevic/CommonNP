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

class ProducerTest {

	Producer p;
	
	@BeforeEach
	void setUp() throws Exception {
		p = new Producer();
	}

	@AfterEach
	void tearDown() throws Exception {
		p = null;
	}

	@Test
	void testSetProducerID() {
		p.setProducerID(1);
		assertEquals(1, p.getProducerID());
	}

	@Test
	void testSetProducerName() {
		p.setProducerName("Apple");
		assertEquals("Apple", p.getProducerName());
	}
	
	@Test
	public void testGetTableName() {
		assertEquals("producer", p.getTableName());
	}
	
	@Test
	public void testGetInsertColumns() {
		assertEquals("producerName", p.getInsertColumns());
	}
	
	@Test
	public void testGetInsertValues() {
		p.setProducerName("Apple");
		String expected = "'Apple'";

		assertEquals(expected, p.getInsertValues());
	}
	
	@Test
	public void testSetId() {
		long expectedId = 42;
		p.setId(expectedId);
		long actualId = p.getProducerID();

		assertEquals(expectedId, actualId);
	}
	
	@Test
	public void testGetUpdateValues() {
		p.setProducerName("Samsung");

		String expectedUpdateValues = "producerName = 'Samsung'";
		String actualUpdateValues = p.getUpdateValues();

		assertEquals(expectedUpdateValues, actualUpdateValues);
	}
	
	@Test
	public void testGetJoinText() {
		String expectedJoinText = "";
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
        p.setProducerID(42); 
        
        String expectedID = "producer.producerID=42";
        String actualID = p.getID(); 
        
        assertEquals(expectedID, actualID); 
    }
	
	@Test
	public void testGetEntity() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_seminarski_db", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM producer WHERE producerID = ?");
	    preparedStatement.setLong(1, 1);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    Producer producer = new Producer();
	    GenericEntity entity = null;

	    if (resultSet.next()) {
	        entity = producer.getEntity(resultSet);
	    }

	    assertNotNull(entity);

	    assertEquals(1L, ((Producer) entity).getProducerID());
	    assertEquals("Apple", ((Producer) entity).getProducerName());
	}

	@Test
	public void testGetEntityThrowsSqlSyntaxErrorException() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_napredno_programiranje", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM producer WHERE producerIDs = ?");
	    preparedStatement.setLong(1, 1);

	    assertThrows(SQLSyntaxErrorException.class, () -> preparedStatement.executeQuery());
	}

}
