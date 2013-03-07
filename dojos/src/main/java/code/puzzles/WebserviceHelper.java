package code.puzzles;

import code.puzzles.TheRestOfTheCode.Products;
import code.puzzles.TheRestOfTheCode.Webservice;
import code.puzzles.TheRestOfTheCode.WebserviceException;

public class WebserviceHelper {
    private Webservice webservice;
    
    public WebserviceHelper(Webservice webservice) {
        this.webservice = webservice;
    }
    
    public Products getProductsByCategoryAndQuantity(final Integer category, final Integer quantity) throws WebserviceException{
        return getProductsGeneric(null, category, quantity);
    }
    
    public Products getProductsByBrand(final String brand) throws WebserviceException{
        return getProductsGeneric(brand, null, null);
    }
    
    public Products getProductsGeneric(final String brand, final Integer category, final Integer quantity) throws WebserviceException{
        Products products;
        try {
            if (brand == null) {
                products = webservice.getProducts(category, quantity);
            } else {
                products = webservice.getProducts(brand);
            }
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
