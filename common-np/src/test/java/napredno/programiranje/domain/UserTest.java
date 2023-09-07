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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest {

	User u;
	
	@BeforeEach
	void setUp() throws Exception {
		u = new User();
	}

	@AfterEach
	void tearDown() throws Exception {
		u = null;
	}

	@Test
	void testSetFirstName() {
		u.setFirstName("Isidora");
		assertEquals("Isidora", u.getFirstName());
	}

	@Test
	void testSetLastName() {
		u.setLastName("Vidojevic");
		assertEquals("Vidojevic", u.getLastName());
	}
	
	@Test
	void testSetUsername() {
		u.setUsername("isidora");
		assertEquals("isidora", u.getUsername());
	}
	
	@Test
	void testSetPassword() {
		u.setPassword("isidora");
		assertEquals("isidora", u.getPassword());
	}
	
	@Test
	public void testGetTableName() {
		assertEquals("user", u.getTableName());
	}
	
	@Test
	public void testGetInsertColumns() {
		assertEquals("firstName, lastName, username, password", u.getInsertColumns());
	}
	
	@Test
	public void testGetInsertValues() {
		u.setFirstName("Isidora");
		u.setLastName("Vidojevic");
		u.setUsername("isidora");
		u.setPassword("isidora");
		String expected = "'Isidora', 'Vidojevic', 'isidora', 'isidora'";

		assertEquals(expected, u.getInsertValues());
	}
	
	@Test
	public void testGetUpdateValues() {
		String expectedUpdateValues = "";
		String actualUpdateValues = u.getUpdateValues();

		assertEquals(expectedUpdateValues, actualUpdateValues);
	}
	
	@Test
	public void testGetJoinText() {
		String expectedJoinText = "";
		String actualJoinText = u.getJoinText();

		assertEquals(expectedJoinText, actualJoinText);
	}
	
	@Test
	public void testGetSelectedText() {
		String expectedSelectedText = "";
		String actualSelectedText = u.getSelectedText();

		assertEquals(expectedSelectedText, actualSelectedText);
	}
	
	@Test
    public void testGetID() {
        
        String expectedID = "";
        String actualID = u.getID(); 
        
        assertEquals(expectedID, actualID); 
    }
	
	@Test
	public void testGetEntity() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_seminarski_db", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
	    preparedStatement.setString(1, "isidora");
	    ResultSet resultSet = preparedStatement.executeQuery();

	    User user = new User();
	    GenericEntity entity = null;

	    if (resultSet.next()) {
	        entity = user.getEntity(resultSet);
	    }

	    assertNotNull(entity);

	    assertEquals("Isidora", ((User) entity).getFirstName());
	    assertEquals("Vidojevic", ((User) entity).getLastName());
	    assertEquals("isidora", ((User) entity).getUsername());
	    assertEquals("isidora", ((User) entity).getPassword());
	}


	@Test
	public void testGetEntityThrowsSqlSyntaxErrorException() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_napredno_programiranje", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE usernames = ?");
	    preparedStatement.setString(1, "isidora");

	    assertThrows(SQLSyntaxErrorException.class, () -> preparedStatement.executeQuery());
	}
	
	@ParameterizedTest
	@CsvSource ({
		"isidora, dora, isidora, dora, true",
		"isidora, masa, isidora, dora, false",
		"marija, dora, isidora, dora, false",
		"marija, masa, isidora, dora, false"
	})
	void testEquals(String un1, String p1, String un2, String p2, boolean equals) {
		u.setUsername(un1);
		u.setPassword(p1);
		
		User u2 = new User();
		u2.setUsername(un2);
		u2.setPassword(p2);
		
		assertEquals(equals, u.equals(u2));
	}

}
