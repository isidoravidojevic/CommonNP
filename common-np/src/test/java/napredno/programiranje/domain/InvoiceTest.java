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

class InvoiceTest {

	Invoice i;

	@BeforeEach
	void setUp() throws Exception {
		i = new Invoice();
	}

	@AfterEach
	void tearDown() throws Exception {
		i = null;
	}

	@Test
	void testSetInvoiceNumber() {
		i.setInvoiceNumber(2);
		assertEquals(2, i.getInvoiceNumber());
	}
	
	@Test
    public void testSetInvoiceNumberInvalidZero() {
        assertThrows(IllegalArgumentException.class, () -> i.setInvoiceNumber(0)); 
    }

    @Test
    public void testSetInvoiceNumberInvalidNegative() {
        assertThrows(IllegalArgumentException.class, () -> i.setInvoiceNumber(-123));
    }

	@Test
	void testSetProcessed() {
		i.setProcessed(true);
		assertTrue(i.isProcessed());
	}

	@Test
	void testSetCanceled() {
		i.setCanceled(true);
		assertTrue(i.isCanceled());
	}

	@Test
	void testSetIssueDate() {
		LocalDate issueDate = LocalDate.of(2023, 9, 6);
		i.setIssueDate(issueDate);
		assertEquals(issueDate, i.getIssueDate());
	}

	@Test
    public void testSetIssueDateInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> i.setIssueDate(null));
    }
	
	@Test
	void testSetVAT() {
		i.setVAT(13.4);
		assertEquals(13.4, i.getVAT());
	}

	@Test
    public void testSetVATInvalidNegative() {
        double invalidVAT = -5.0; 
        assertThrows(IllegalArgumentException.class, () -> i.setVAT(invalidVAT));
    }

    @Test
    public void testSetVATInvalidGreaterThan100() {
        double invalidVAT = 120.0; 
        assertThrows(IllegalArgumentException.class, () -> i.setVAT(invalidVAT));
    }
	
	@Test
	void testSetRebate() {
		i.setRebate(4.5);
		assertEquals(4.5, i.getRebate());
	}
	
	@Test
    public void testSetRebateInvalidNegative() {
        double invalidRebate = -5.0; 
        assertThrows(IllegalArgumentException.class, () -> i.setRebate(invalidRebate));
    }

    @Test
    public void testSetRebateInvalidGreaterThan100() {
        double invalidRebate = 120.0; 
        assertThrows(IllegalArgumentException.class, () -> i.setRebate(invalidRebate));
    }

	@Test
	void testSetAccountingBasis() {
		i.setAccountingBasis(4.5);
		assertEquals(4.5, i.getAccountingBasis());
	}

	@Test
    public void testSetAccountingBasisInvalidNegative() {
        double invalidAccountingBasis = -100.0; 
        assertThrows(IllegalArgumentException.class, () -> i.setAccountingBasis(invalidAccountingBasis));
    }
	
	@Test
	public void testSetTotalValue() {
		BigDecimal totalValue = new BigDecimal("100.50");
		i.setTotalValue(totalValue);

		assertEquals(totalValue, i.getTotalValue());
	}
	
	@Test
    public void testSetTotalValueInvalidNegative() {
        BigDecimal invalidTotalValue = BigDecimal.valueOf(-100.0);
        assertThrows(IllegalArgumentException.class, () -> i.setTotalValue(invalidTotalValue));
    }

    @Test
    public void testSetTotalValueInvalidZero() {
        BigDecimal invalidTotalValue = BigDecimal.ZERO;
        assertThrows(IllegalArgumentException.class, () -> i.setTotalValue(invalidTotalValue));
    }

	@Test
	void testSetPaymentDeadline() {
		LocalDate paymentDeadline = LocalDate.of(2023, 3, 6);
		i.setPaymentDeadline(paymentDeadline);
		assertEquals(paymentDeadline, i.getPaymentDeadline());
	}

	@Test
    public void testSetPaymentDeadlineInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> i.setPaymentDeadline(null));
    }
	
	@Test
	void testSetCustomer() {
		Customer customer = new Customer();
		i.setCustomer(customer);
		assertEquals(customer, i.getCustomer());
	}
	
	@Test
    public void testSetCustomerInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> i.setCustomer(null)); 
    }

	@Test
	public void testGetTableName() {
		assertEquals("invoice", i.getTableName());
	}

	@Test
	public void testGetInsertColumns() {
		assertEquals(
				"processed, canceled, issueDate, VAT, rebate, accountingBasis, totalValue, paymentDeadline, customerID",
				i.getInsertColumns());
	}

	@Test
	public void testGetInsertValues() {
		i.setProcessed(true);
		i.setCanceled(false);
		i.setIssueDate(LocalDate.of(2023, 9, 6));
		i.setVAT(20.0);
		i.setRebate(5.0);
		i.setAccountingBasis(100.0);
		i.setTotalValue(new BigDecimal("500.00"));
		i.setPaymentDeadline(LocalDate.of(2023, 9, 20));
		Customer customer = new Customer();
		i.setCustomer(customer);

		String insertValues = i.getInsertValues();
		String expectedInsertValues = "true, false, '2023-09-06', 20.0, 5.0, 100.0, 500.00, '2023-09-20', 0";

		assertEquals(expectedInsertValues, insertValues);
	}

	@Test
	public void testSetId() {
		long expectedId = 4;
		i.setId(expectedId);
		long actualId = i.getInvoiceNumber();

		assertEquals(expectedId, actualId);
	}
	
	@Test
    public void testGetUpdateValues() {
        i.setCanceled(true);
        String updateValues = i.getUpdateValues();
        String expectedUpdateValues = "canceled = true";

        assertEquals(expectedUpdateValues, updateValues);
    }
	
	@Test
	public void testGetJoinText() {
		String expectedJoinText = " JOIN customer ON invoice.customerID = customer.customerID JOIN city ON customer.cityID = city.cityID";
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
        i.setInvoiceNumber(2); 
        
        String expectedID = "invoice.invoiceNumber=2";
        String actualID = i.getID(); 
        
        assertEquals(expectedID, actualID); 
    }
	
	@Test
	public void testGetEntity() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_seminarski_db", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoice JOIN customer ON invoice.customerID = customer.customerID JOIN city ON customer.cityID = city.cityID WHERE invoiceNumber = ?");
	    preparedStatement.setLong(1, 23);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    Customer customer = new Customer();
	    Invoice invoice = new Invoice();
	    GenericEntity entity = null;

	    if (resultSet.next()) {
	        entity = invoice.getEntity(resultSet);
	    }

	    assertNotNull(entity);

	    assertEquals(23L, ((Invoice) entity).getInvoiceNumber());
	    assertTrue(((Invoice) entity).isProcessed());
	    assertFalse(((Invoice) entity).isCanceled());
	    assertEquals(LocalDate.of(2005, 5, 5), ((Invoice) entity).getIssueDate());
	    assertEquals(20.0, ((Invoice) entity).getVAT(), 0.001);
	    assertEquals(2.0, ((Invoice) entity).getRebate(), 0.001);
	    assertEquals(16170000.0, ((Invoice) entity).getAccountingBasis(), 0.001);
	    assertEquals(new BigDecimal("19404000"), ((Invoice) entity).getTotalValue());
	    assertEquals(LocalDate.of(2012, 12, 25), ((Invoice) entity).getPaymentDeadline());

	    assertNotNull(((Invoice) entity).getCustomer());
	    assertEquals(16L, ((Invoice) entity).getCustomer().getCustomerID());
	    assertEquals("iStyle", ((Invoice) entity).getCustomer().getCustomerName());
	    assertEquals("Vinogradarska 7", ((Invoice) entity).getCustomer().getAddress());
	    assertEquals("998877665", ((Invoice) entity).getCustomer().getVATnumber());
	    assertEquals("91827364", ((Invoice) entity).getCustomer().getCompanyNumber());
	}

	@Test
	public void testGetEntityThrowsSqlSyntaxErrorException() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_napredno_programiranje", "root", "");
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoices JOIN customer ON invoice.customerID = customer.customerID JOIN city ON customer.cityID = city.cityID WHERE invoiceNumber = ?");
	    preparedStatement.setLong(1, 23);

	    assertThrows(SQLSyntaxErrorException.class, () -> preparedStatement.executeQuery());
	}
}
