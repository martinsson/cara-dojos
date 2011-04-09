package code.puzzles;

import code.puzzles.TheRestOfTheCode.Products;
import code.puzzles.TheRestOfTheCode.Rebates;
import code.puzzles.TheRestOfTheCode.Webservice;
import code.puzzles.TheRestOfTheCode.WebserviceException;

public class WebserviceHelper {
    private Webservice webservice;
    
    public Products getProductsByCategoryAndQuantity(final Integer category, final Integer quantity) throws WebserviceException{
        Products products;
        try {
            products = webservice.getProducts(category, quantity);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed invoking the webservice with arguments " + category + " and " + quantity, e);
        }
        ifNullThrowError(products);
        return products;
    }
    
    public Rebates getRebates(final Products products) throws WebserviceException{
        Rebates rebates;
        try {
            rebates = webservice.getRebates(products);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed to get rebates for " + products,  e);
        }
        ifNullThrowError(rebates);
        return rebates;
    }
    
    private void ifNullThrowError(Object object) throws WebserviceException {
       if (object==null)
           throw new WebserviceException("The webservice returned null");
    }
    

}
