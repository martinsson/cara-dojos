package ocp;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class BillFormatterTest {
    @Test
    public void handlesPounds() throws Exception {
        BillFormatter billFormatter = new BillFormatter();
        String price = billFormatter.format(10030, Currency.POUNDS);
        assertThat(price, equalTo("£100.30"));
    }
    @Test
    public void handlesEuros() throws Exception {
        BillFormatter billFormatter = new BillFormatter();
        String price = billFormatter.format(2357, Currency.EUROS);
        assertThat(price, equalTo("23Û57"));
    }
    
    @Test
    public void itDoesnPrintCentsWhenThePiceIsEven() throws Exception {
        BillFormatter billFormatter = new BillFormatter();
        String price = billFormatter.format(10000, Currency.POUNDS);
        assertThat(price, equalTo("£100"));   
    }
    

}
