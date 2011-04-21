package code.puzzles;

import code.puzzles.TheRestOfTheCode.Products;
import code.puzzles.TheRestOfTheCode.Rebates;
import code.puzzles.TheRestOfTheCode.Webservice;
import code.puzzles.TheRestOfTheCode.WebserviceException;

public class WebserviceHelper {
    private Webservice webservice;
    public Products getProductsByCategoryAndQuantity(final Integer category, final Integer quantity) throws WebserviceException{
        ProductsRetriever retriever = categoryBasedProductRetriever(category, quantity);
        return retriever.getProductsSafely();
    }

    public Products getProductsByBrand(final String brand) throws WebserviceException{
        ProductsRetriever retriever = brandBasedCategoryRetriever(brand);
        return retriever.getProductsSafely();
    }
    public Rebates getRebates(final Products products) throws WebserviceException{
        Rebates rebates;
        try {
            rebates = webservice.getRebates(products);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed to get rebates for " + products,  e);
        }
        ProductsRetriever.ifNullThrowError(rebates);
        return rebates;
    }


    
    private ProductsRetriever brandBasedCategoryRetriever(final String brand) {
        return new ProductsRetriever() {
            public Products getProducts() {
                return webservice.getProducts(brand);
            }
        };
    }
    private ProductsRetriever categoryBasedProductRetriever(final Integer category, final Integer quantity) {
        return new ProductsRetriever() {
            public Products getProducts() {
                return webservice.getProducts(category, quantity);
            }
        };
    }
}
