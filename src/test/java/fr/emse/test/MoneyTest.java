package fr.emse.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {

    private IMoney m12CHF;
    private Money m14CHF;

    @Before
    public void setUp() {
        m12CHF = new Money(12, "CHF");
        m14CHF = new Money(14, "CHF");
    }

    @Test 
    public void testSimpleAdd() {  
        IMoney expected = new Money(26, "CHF"); 
        IMoney result = m12CHF.add(m14CHF);  
        assertEquals(expected, result);
    }

    @Test 
    public void testEquals() { 
        assertNotEquals(null, m12CHF); 
        assertEquals(m12CHF, m12CHF); 
        assertEquals(m12CHF, new Money(12, "CHF")); 
        assertNotEquals(m12CHF, m14CHF); 
    } 
}
