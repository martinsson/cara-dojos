package code.puzzles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import code.puzzles.TheRestOfTheCode.Products;
import code.puzzles.TheRestOfTheCode.Webservice;
import code.puzzles.TheRestOfTheCode.WebserviceException;

public class WebserviceHelperTest {

    @Mock
    private Webservice webservice;

    @Before
    public void initBeforeTest() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductsByBrand() throws WebserviceException {
        when(webservice.getProducts("Nokia")).thenReturn(new Products());
        
        WebserviceHelper webserviceHelper = new WebserviceHelper(webservice);
        Products productsByBrand = webserviceHelper.getProductsByBrand("Nokia");
        assertNotNull(productsByBrand);
    }

	
	@Test
    public void testGetProductsByBrandWithAnUnknownBrand() {
        when(webservice.getProducts("Apple")).thenReturn(null);
        
        WebserviceHelper webserviceHelper = new WebserviceHelper(webservice);
		try {
			webserviceHelper.getProductsByBrand("Apple");
			fail("Expected an exception to have been thrown.");
		} catch (WebserviceException e) {
			assertEquals("Unexpected exception message", "The webservice returned null", e.getMessage());
		}
    }
	
}

