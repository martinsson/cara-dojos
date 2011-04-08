package code.puzzles;

public class WebserviceHelper {
    private Webservice webservice;
    public Products getProducts(Integer category, Integer quantity) throws WebserviceException{
        Products products;
        try {
            products = webservice.getProducts(category, quantity);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed invoking the webservice", e);
        }
        ifNullThrowError(products);
        return products;
    }
    public Products getRebates(String brand) throws WebserviceException{
        Products products;
        try {
            products = webservice.getProducts(brand);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed invoking the webservice", e);
        }
        ifNullThrowError(products);
        return products;
    }
    private void ifNullThrowError(Object object) throws WebserviceException {
       if (object==null)
           throw new WebserviceException("The webservice returned null");
    }
    
    static class Webservice {
        public Products getProducts(Integer category, Integer quantity) {
            return new Products();
        }
        public Products getProducts(String brand) {
            return new Products();
        }
    }
    static class Products {
    }
    static class Rebates {
    }

    @SuppressWarnings("serial")
    public static class WebserviceException extends Exception {

        public WebserviceException(String string, RuntimeException e) {
        }

        public WebserviceException(String string) {
        }

    }
}
