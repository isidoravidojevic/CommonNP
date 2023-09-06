package napredno.programiranje.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceReceptionMeansTest {

	@Test
    public void testEnumValues() {
        assertEquals(3, InvoiceReceptionMeans.values().length);
        
        assertTrue(InvoiceReceptionMeans.valueOf("ELEKTRONSKI") instanceof InvoiceReceptionMeans);
        assertTrue(InvoiceReceptionMeans.valueOf("LICNO") instanceof InvoiceReceptionMeans);
        assertTrue(InvoiceReceptionMeans.valueOf("POSTA") instanceof InvoiceReceptionMeans);
    }

}
