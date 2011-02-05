package code.puzzles;

public class WebserviceHelper {
    private Webservice webservice;
    public Products getProducts(Integer category, Integer quantity) throws WebserviceException{
        Products products;
        try {
            products = webservice.getProducts(category, quantity);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed invoking the webservice with category "+category+" and quantity "+quantity, e);
        }
        ifNullThrowError(products);
        return products;
    }
    public Rebates getRebates(Products products) throws WebserviceException{
        Rebates rebates;
        try {
            rebates = webservice.getRebates(products);
        } catch(RuntimeException e) {
            throw new WebserviceException("failed invoking the webservice with products "+products);
        }
        ifNullThrowError(rebates);
        return rebates;
    }
    private void ifNullThrowError(Object object) throws WebserviceException {
       if (object==null)
           throw new WebserviceException("The webservice returned null");
    }
    
    static class Webservice {
        public Products getProducts(Integer category, Integer quantity) {
            return new Products();
        }
        public Rebates getRebates(Products products) {
            return new Rebates();
        }
    }
    static class Products {
    }
    static class Rebates {
    }

    public static class WebserviceException extends Exception {

        public WebserviceException(String string, RuntimeException e) {
        }

        public WebserviceException(String string) {
        }

    }
}
