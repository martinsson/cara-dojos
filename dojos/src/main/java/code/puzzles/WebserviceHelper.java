package code.puzzles;

public class WebserviceHelper {
    private final Webservice webservice = new Webservice();
    public Products getProducts(final Integer category, final Integer quantity) throws WebserviceException{
        WebserviceInvoker<Products> invoker = new WebserviceInvoker<Products>() {
            public Products invoke() {
                return webservice.getProducts(category, quantity);
            }

            public String parameters() {
                return "category "+category+" and quantity "+quantity;
            }
        };
        return invoker.invokeSafely();
    }
    public Rebates getRebates(final Products products) throws WebserviceException{
        WebserviceInvoker<Rebates> invoker = new WebserviceInvoker<Rebates>() {

            @Override
            protected Rebates invoke() {
                return webservice.getRebates(products);
            }

            @Override
            protected String parameters() {
                return "products "+products;
            }
        };
        return invoker.invokeSafely();
    }

    static abstract class WebserviceInvoker<T> {
        protected abstract T invoke();
        protected abstract String parameters();
        public T invokeSafely() throws WebserviceException {
            T t;
            try {
                t = invoke();
            } catch(RuntimeException e) {
                throw new WebserviceException("failed invoking the webservice with " + parameters());
            }
            ifNullThrowError(t);
            return t;
            
        }
        private void ifNullThrowError(T t) throws WebserviceException {
            if (t==null)
                throw new WebserviceException("The webservice returned null");
         }
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
