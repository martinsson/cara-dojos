package code.puzzles;

import code.puzzles.TheRestOfTheCode.WebserviceException;

public abstract class WsInvoker<T> {
    public abstract T invoke();
    public abstract String errorMessage();
    
    private void ifNullThrowError(Object object) throws WebserviceException {
        if (object==null)
            throw new WebserviceException("The webservice returned null");
    }
    
    public T invokeSafely() throws WebserviceException {
        T rebates;
        try {
            rebates = invoke();
        } catch(RuntimeException e) {
            throw new WebserviceException(errorMessage(),  e);
        }
        ifNullThrowError(rebates);
        return rebates;
    }
}