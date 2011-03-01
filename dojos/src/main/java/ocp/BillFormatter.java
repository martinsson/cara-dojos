package ocp;

public class BillFormatter {

    public String format(int price, Currency currency) throws UnknownCurrencyException {
        String formattedPrice = "Your total is : ";
        if (currency == Currency.EUROS) {
            formattedPrice += price + "Û";
        } else if (currency == Currency.POUNDS) {
            formattedPrice += "£" + price;
        } else {
            throw new UnknownCurrencyException();
        }
        return formattedPrice;
    }
    

}
