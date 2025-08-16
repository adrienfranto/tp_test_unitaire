package fr.emse.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MoneyBagTest {

    private Money f12CHF;
    private Money f14CHF;
    private Money f7USD;
    private Money f21USD;
    private MoneyBag fMB1;
    private MoneyBag fMB2;

    @Before 
    public void setUp() { 
	    f12CHF= new Money(12, "CHF"); 
	    f14CHF= new Money(14, "CHF"); 
	    f7USD= new Money( 7, "USD"); 
	    f21USD= new Money(21, "USD"); 
	    fMB1= new MoneyBag(f12CHF, f7USD); 
	    fMB2= new MoneyBag(f14CHF, f21USD); 
    } 
    @Test 
    public void testBagEquals() { 
	    assertTrue(!fMB1.equals(null)); 
	    assertEquals(fMB1, fMB1); 
	    assertTrue(!fMB1.equals(f12CHF)); 
	    assertTrue(!f12CHF.equals(fMB1)); 
	    assertTrue(!fMB1.equals(fMB2)); 
    } 
    
    @Test 
    public void testMixedSimpleAdd() { 
	    Money bag[] = { f12CHF, f7USD }; 
	    MoneyBag expected = new MoneyBag(bag); 
	    assertEquals(expected, f12CHF.add(f7USD)); 
    }     
    
    @Test
    public void testBagSimpleAdd() {
        Money m5CHF = new Money(5, "CHF");
        MoneyBag mb = new MoneyBag(f14CHF, f21USD); 
        MoneyBag expected = new MoneyBag(new Money[]{ new Money(19, "CHF"), f21USD });
        assertEquals(expected, m5CHF.add(mb));
    }

    @Test
    public void testSimpleBagAdd() {
        Money m3USD = new Money(3, "USD");
        MoneyBag expected = new MoneyBag(new Money[]{ f12CHF, new Money(10, "USD") });
        assertEquals(expected, fMB1.add(m3USD));
    }

    @Test
    public void testBagBagAdd() {
        MoneyBag mb1 = new MoneyBag(f12CHF, f7USD);
        MoneyBag mb2 = new MoneyBag(f14CHF, f21USD);
        MoneyBag expected = new MoneyBag(new Money[]{ new Money(26, "CHF"), new Money(28, "USD") });
        assertEquals(expected, mb1.add(mb2));
    }
    
    @Test
    public void testSimplifyBagToSingleMoney() {
        Money minus12CHF = new Money(-12, "CHF");
        MoneyBag bag = new MoneyBag(f12CHF, f7USD); 
        
        IMoney result = bag.add(minus12CHF);
        assertEquals(new Money(7, "USD"), result);
        assertTrue(result instanceof Money);
    }

}
