package code.puzzles;

import code.puzzles.TheRestOfTheCode.Products;
import code.puzzles.TheRestOfTheCode.Rebates;
import code.puzzles.TheRestOfTheCode.Webservice;
import code.puzzles.TheRestOfTheCode.WebserviceException;

public class WebserviceHelper {
    private Webservice webservice;
    
    public WebserviceHelper(Webservice webservice) {
        this.webservice = webservice;
    }
    
    public Rebates getRebates(final Integer category, final Integer quantity) throws WebserviceException{
        Rebates rebates;
        try {
            rebates = webservice.getRebates(category, quantity);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed invoking the webservice");
        }
        ifNullThrowError(rebates);
        return rebates;
    }
    
    public Products getProductsByBrand(final String brand) throws WebserviceException{
        Products products;
        try {
            products = webservice.getProducts(brand);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed invoking the webservice");
        }
        ifNullThrowError(products);
        return products;
    }
    
    private void ifNullThrowError(Object object) throws WebserviceException {
       if (object==null)
           throw new WebserviceException("The webservice returned null");
    }
    

}
