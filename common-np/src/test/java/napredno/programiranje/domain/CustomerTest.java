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

class CustomerTest {

	Customer c;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new Customer();
	}

	@AfterEach
	void tearDown() throws Exception {
		c = null;
	}

	@Test
	void testSetCustomerID() {
		c.setCustomerID(1);
		assertEquals(1, c.getCustomerID());
	}
	
	@Test
    public void testSetCustomerIDInvalidZero() {
        assertThrows(IllegalArgumentException.class, () -> c.setCustomerID(0)); 
    }

    @Test
    public void testSetCustomerIDInvalidNegative() {
        assertThrows(IllegalArgumentException.class, () -> c.setCustomerID(-1));
    }

	@Test
	void testSetCustomerName() {
		c.setCustomerName("Tehnomanija");
		assertEquals("Tehnomanija", c.getCustomerName());
	}
	
	@Test
    public void testSetCustomerNameInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> c.setCustomerName(null)); 
    }

    @Test
    public void testSetCustomerNameInvalidShort() {
        assertThrows(IllegalArgumentException.class, () -> c.setCustomerName("C1")); 
    }
	
	@Test
	void testSetAddress() {
		c.setAddress("Zicka 3");
		assertEquals("Zicka 3", c.getAddress());
	}
	
	@Test
    public void testSetAddressInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }

    @Test
    public void testSetAddressInvalidShort() {
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("St")); 
    }
	
	@Test
	void testSetVATnumber() {
		c.setVATnumber("123456789");
		assertEquals("123456789", c.getVATnumber());
	}
	
	@Test
    public void testSetVATnumberInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> c.setVATnumber(null)); 
    }

    @Test
    public void testSetVATnumberInvalidShort() {
        assertThrows(IllegalArgumentException.class, () -> c.setVATnumber("12345678"));
    }

    @Test
    public void testSetVATnumberInvalidNonNumeric() {
        assertThrows(IllegalArgumentException.class, () -> c.setVATnumber("abcdefghi")); 
    }
	
	@Test
	void testSetCompanyNumber() {
		c.setCompanyNumber("12345678");
		assertEquals("12345678", c.getCompanyNumber());
	}
	
	@Test
    public void testSetCompanyNumberInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> c.setCompanyNumber(null));
    }

    @Test
    public void testSetCompanyNumberInvalidShort() {
        assertThrows(IllegalArgumentException.class, () -> c.setCompanyNumber("1234567"));
    }

    @Test
    public void testSetCompanyNumberInvalidNonNumeric() {
        assertThrows(IllegalArgumentException.class, () -> c.setCompanyNumber("abcdefgh")); 
    }
	
	@Test
	void testSetCity() {
		City city = new City();
		c.setCity(city);
		assertEquals(city, c.getCity());
	}
	
	@Test
    public void testSetCityInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> c.setCity(null));
    }
	
	@Test
	public void testGetTableName() {
		assertEquals("customer", c.getTableName());
	}
	
	@Test
	public void testGetInsertColumns() {
		assertEquals("customerName, address, VATnumber, companyNumber, cityID", c.getInsertColumns());
	}
	
	@Test
	public void testGetInsertValues() {
		c.setCustomerName("Tehnomanija");
		c.setAddress("Zicka 5");
		c.setVATnumber("123456789");
		c.setCompanyNumber("12345678");
		City city = new City(1, "Beograd", "11000");
		c.setCity(city);
		
		String expected = "'Tehnomanija','Zicka 5','123456789','12345678', 1";

		assertEquals(expected, c.getInsertValues());
	}
	
	@Test
	public void testSetId() {
		long expectedId = 3;
		c.setId(expectedId);
		long actualId = c.getCustomerID();

		assertEquals(expectedId, actualId);
	}
	
	@Test
	public void testGetUpdateValues() {
		c.setCustomerName("Tehnomanija");
		c.setAddress("Zicka 5");
		c.setVATnumber("123456789");
		c.setCompanyNumber("12345678");
		City city = new City(1, "Beograd", "11000");
		c.setCity(city);
		
		String expectedUpdateValues = "customerName='Tehnomanija',address='Zicka 5',VATnumber='123456789',companyNumber='12345678',cityID=1";
		String actualUpdateValues = c.getUpdateValues();

		assertEquals(expectedUpdateValues, actualUpdateValues);
	}
	
	@Test
	public void testGetJoinText() {
		String expectedJoinText = " JOIN city ON customer.cityID = city.cityID";
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
        c.setCustomerID(2); 
        
        String expectedID = "customer.customerID=2";
        String actualID = c.getID(); 
        
        assertEquals(expectedID, actualID); 
    }
	
	@Test
	public void testGetEntity() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_seminarski_db", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer JOIN city ON customer.cityID = city.cityID WHERE customerID = ?");
	    preparedStatement.setLong(1, 15);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    City city = new City();
	    Customer customer = new Customer();
	    GenericEntity entity = null;

	    if (resultSet.next()) {
	        entity = customer.getEntity(resultSet);
	    }

	    assertNotNull(entity);

	    assertEquals(15L, ((Customer) entity).getCustomerID());
	    assertEquals("Tehnomanija", ((Customer) entity).getCustomerName());
	    assertEquals("Zicka 1", ((Customer) entity).getAddress());
	    assertEquals("123456789", ((Customer) entity).getVATnumber());
	    assertEquals("12345678", ((Customer) entity).getCompanyNumber());

	    assertNotNull(((Customer) entity).getCity());
	    assertEquals(2L, ((Customer) entity).getCity().getCityID());
	    assertEquals("Beograd", ((Customer) entity).getCity().getCityName());
	    assertEquals("11000", ((Customer) entity).getCity().getPostalCode());
	}
	
	@Test
	public void testGetEntityThrowsSqlSyntaxErrorException() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_napredno_programiranje", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM custome JOIN city ON customer.cityID = city.cityID WHERE customerID = ?");
	    preparedStatement.setLong(1, 15);

	    assertThrows(SQLSyntaxErrorException.class, () -> preparedStatement.executeQuery());
	}

}
