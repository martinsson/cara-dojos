package ocp;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class BillFormatterTest {
    @Test
    public void handlesPounds() throws Exception {
        BillFormatter billFormatter = new BillFormatter();
        String price = billFormatter.format(100, Currency.POUNDS);
        assertThat(price, endsWith("£100"));
    }
    @Test
    public void handlesEuros() throws Exception {
        BillFormatter billFormatter = new BillFormatter();
        String price = billFormatter.format(23, Currency.EUROS);
        assertThat(price, endsWith("23Û"));
    }
    
    

}
