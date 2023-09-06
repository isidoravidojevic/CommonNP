package napredno.programiranje.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MeasurementUnitTest {

	@Test
    public void testEnumValues() {
        assertEquals(4, MeasurementUnit.values().length);
        
        assertTrue(MeasurementUnit.valueOf("KG") instanceof MeasurementUnit);
        assertTrue(MeasurementUnit.valueOf("L") instanceof MeasurementUnit);
        assertTrue(MeasurementUnit.valueOf("G") instanceof MeasurementUnit);
        assertTrue(MeasurementUnit.valueOf("PSC") instanceof MeasurementUnit);
    }

}
