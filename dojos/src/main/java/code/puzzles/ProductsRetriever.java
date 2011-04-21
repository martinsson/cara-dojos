package code.puzzles;

import code.puzzles.TheRestOfTheCode.Products;
import code.puzzles.TheRestOfTheCode.WebserviceException;

abstract class ProductsRetriever {
    abstract Products getProducts();

    Products getProductsSafely() throws WebserviceException {
        Products products;
        try {
            products = getProducts();
        } catch(RuntimeException e) {
            throw new WebserviceException("failed invoking the webservice", e);
        }
        ifNullThrowError(products);
        return products;
    }

    public static void ifNullThrowError(Object products) throws WebserviceException {
        if (products==null)
               throw new WebserviceException("The webservice returned null");
    }
}