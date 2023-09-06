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

class CityTest {

	City c;

	@BeforeEach
	void setUp() throws Exception {
		c = new City();
	}

	@AfterEach
	void tearDown() throws Exception {
		c = null;
	}

	@Test
	void testSetCityID() {
		c.setCityID(1);
		assertEquals(1, c.getCityID());
	}

	@Test
	void testSetCityName() {
		c.setCityName("Aleksandrovac");
		assertEquals("Aleksandrovac", c.getCityName());
	}

	@Test
	void testSetPostalCode() {
		c.setPostalCode("37230");
		assertEquals("37230", c.getPostalCode());
	}

	@Test
	public void testGetTableName() {
		assertEquals("city", c.getTableName());
	}

	@Test
	public void testGetInsertColumns() {
		assertEquals("cityName, postalCode", c.getInsertColumns());
	}

	@Test
	public void testGetInsertValues() {
		c.setCityName("Aleksandrovac");
		c.setPostalCode("37230");
		String expected = "'Aleksandrovac', '37230'";

		assertEquals(expected, c.getInsertValues());
	}

	@Test
	public void testSetId() {
		long expectedId = 42;
		c.setId(expectedId);
		long actualId = c.getCityID();

		assertEquals(expectedId, actualId);
	}

	@Test
	public void testGetUpdateValues() {
		c.setCityName("Aleksandrovac");
		c.setPostalCode("37230");

		String expectedUpdateValues = "cityName = 'Aleksandrovac', postalCode = '37230'";
		String actualUpdateValues = c.getUpdateValues();

		assertEquals(expectedUpdateValues, actualUpdateValues);
	}

	@Test
	public void testGetJoinText() {
		String expectedJoinText = "";
		String actualJoinText = c.getJoinText();

		assertEquals(expectedJoinText, actualJoinText);
	}
	
	@Test
	public void testGetSelectedText() {
		String expectedSelectedText = "";
		String actualSelectedText = c.getSelectedText();

		assertEquals(expectedSelectedText, actualSelectedText);
	}
	
	@Test
    public void testGetID() {
        c.setCityID(42); 
        
        String expectedID = "city.cityID=42";
        String actualID = c.getID(); 
        
        assertEquals(expectedID, actualID); 
    }
	
	@Test
	public void testGetEntity() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_seminarski_db", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM city WHERE cityID = ?");
	    preparedStatement.setLong(1, 1);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    City city = new City();
	    GenericEntity entity = null;

	    if (resultSet.next()) {
	        entity = city.getEntity(resultSet);
	    }

	    assertNotNull(entity);

	    assertEquals(1L, ((City) entity).getCityID());
	    assertEquals("Aleksandrovac", ((City) entity).getCityName());
	    assertEquals("37230", ((City) entity).getPostalCode());
	}

}
