package code.puzzles;


public class TheRestOfTheCode {
    static class Webservice {
        public Products getProducts(Integer category, Integer quantity) {
            return new Products();
        }
        public Products getProducts(String brand) {
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

    @SuppressWarnings("serial")
    public static class WebserviceException extends Exception {

        public WebserviceException(String string, RuntimeException e) {
        }

        public WebserviceException(String string) {
        }

    }

}
