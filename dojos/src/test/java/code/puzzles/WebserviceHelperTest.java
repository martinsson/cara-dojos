package code.puzzles;

import static org.junit.Assert.*;
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

    private int category = 123;
    private int quantity = 100;
    @Mock
    private Webservice webservice;

    @Before
    public void initBeforeTest() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test public void 
    it_returns_a_product_by_brand() throws WebserviceException {
        when(webservice.getProducts("Nokia")).thenReturn(new Products());
        
        WebserviceHelper webserviceHelper = new WebserviceHelper(webservice);
        Products productsByBrand = webserviceHelper.getProductsByBrand("Nokia");
        assertNotNull(productsByBrand);
    }

	
	@Test public void 
    it_protects_from_nulls_by_throwing_exception() {
        when(webservice.getProducts("Apple")).thenReturn(null);
        
        WebserviceHelper webserviceHelper = new WebserviceHelper(webservice);
		try {
			webserviceHelper.getProductsByBrand("Apple");
			fail("Expected an exception to have been thrown.");
		} catch (WebserviceException e) {
			assertEquals("Unexpected exception message", "The webservice returned null", e.getMessage());
		}
    }
	
	@Test public void 
    it_transforms_RuntimeExceptions_to_WebserviceExceptions() throws Exception  {
	    when(webservice.getProducts("Apple")).thenThrow(new RuntimeException());
	    
	    WebserviceHelper webserviceHelper = new WebserviceHelper(webservice);
	    try {
	        webserviceHelper.getProductsByBrand("Apple");
	        fail("Expected an exception to have been thrown.");
	    } catch (WebserviceException e) {
	        assertEquals("Unexpected exception message", "failed invoking the webservice", e.getMessage());
	    }
         
    }
	
	@Test public void 
	getProductsByCategoryAndQuantity_returns_a_product() throws WebserviceException {
        when(webservice.getProducts(category, quantity)).thenReturn(new Products());
	    
	    WebserviceHelper webserviceHelper = new WebserviceHelper(webservice);
	    Products productsByBrand = webserviceHelper.getProductsByCategoryAndQuantity(category, quantity);
	    assertNotNull(productsByBrand);
	}
	
	
	@Test public void 
	getProductsByCategoryAndQuantity_protects_from_nulls_by_throwing_exception() {
	    when(webservice.getProducts(category, quantity)).thenReturn(null);
	    
	    WebserviceHelper webserviceHelper = new WebserviceHelper(webservice);
	    try {
	        webserviceHelper.getProductsByCategoryAndQuantity(category, quantity);
	        fail("Expected an exception to have been thrown.");
	    } catch (WebserviceException e) {
	        assertEquals("Unexpected exception message", "The webservice returned null", e.getMessage());
	    }
	}
	
	@Test public void 
	getProductsByCategoryAndQuantity_transforms_RuntimeExceptions_to_WebserviceExceptions() throws Exception  {
	    when(webservice.getProducts(category, quantity)).thenThrow(new RuntimeException());
	    
	    WebserviceHelper webserviceHelper = new WebserviceHelper(webservice);
	    try {
	        webserviceHelper.getProductsByCategoryAndQuantity(category, quantity);
	        fail("Expected an exception to have been thrown.");
	    } catch (WebserviceException e) {
	        assertEquals("Unexpected exception message", "failed invoking the webservice", e.getMessage());
	    }
	    
	}
	
}

