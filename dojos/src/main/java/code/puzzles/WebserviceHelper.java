package code.puzzles;

import code.puzzles.TheRestOfTheCode.Products;
import code.puzzles.TheRestOfTheCode.Rebates;
import code.puzzles.TheRestOfTheCode.Webservice;
import code.puzzles.TheRestOfTheCode.WebserviceException;

public class WebserviceHelper {
    private Webservice webservice;
    
    private interface WebserviceInkover<T> {
        T invoke();
        String getMessage();
    }
    
    public Products getProductsByCategoryAndQuantity(final Integer category, final Integer quantity) throws WebserviceException{
        WebserviceInkover<Products> webserviceInkover = new WebserviceInkover<Products>() {

            public Products invoke() {
                return webservice.getProducts(category, quantity);
            }
            
            public String getMessage() {
                return "failed invoking the webservice with arguments " + category + " and " + quantity;
            }
        };
        
        return webserviceInkover.invoke();
    }

    
    public Rebates getRebates(final Products products) throws WebserviceException{
        WebserviceInkover<Rebates> webserviceInkover = new WebserviceInkover<Rebates>() {

            public Rebates invoke() {
                return webservice.getRebates(products);
            }

            public String getMessage() {
                return "failed to get rebates for " + products;
            }
        };
        
        return invokeSafely(webserviceInkover);
    }


    private <T> T invokeSafely(WebserviceInkover<T> webserviceInkover) throws WebserviceException {
        T t;
        try {
            t = webserviceInkover.invoke();
        } catch(RuntimeException e) {
            throw new WebserviceException(webserviceInkover.getMessage(),  e);
        }
        ifNullThrowError(t);
        return t;
    }
    
    private void ifNullThrowError(Object object) throws WebserviceException {
       if (object==null)
           throw new WebserviceException("The webservice returned null");
    }
    

}
