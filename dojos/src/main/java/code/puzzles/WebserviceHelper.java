package code.puzzles;

import code.puzzles.TheRestOfTheCode.Products;
import code.puzzles.TheRestOfTheCode.Rebates;
import code.puzzles.TheRestOfTheCode.Webservice;
import code.puzzles.TheRestOfTheCode.WebserviceException;

public class WebserviceHelper {
    private Webservice webservice;
    
    public Products getProductsByCategoryAndQuantity(final Integer category, final Integer quantity) throws WebserviceException{
        return getProductsForCategoryAndQuantity(category, quantity)
            .invokeSafely();
    }

    public Rebates getRebates(final Products products) throws WebserviceException{
        return getRebatesFor(products)
            .invokeSafely();
    }

    public WsInvoker<Products> getProductsForCategoryAndQuantity(final Integer category, final Integer quantity) {
        return new WsInvoker<Products>() {
            public Products invoke() {
                return webservice.getProducts(category, quantity);
            }
            
            public String errorMessage() {
                return "failed invoking the webservice with arguments " + category + " and " + quantity;
            }
        };
    }

    public WsInvoker<Rebates> getRebatesFor(final Products products) {
        return new WsInvoker<Rebates>() {
            public Rebates invoke() {
                return webservice.getRebates(products);
            }

            public String errorMessage() {
                return "failed to get rebates for " + products;
            }
        };
    }
    
    

}
