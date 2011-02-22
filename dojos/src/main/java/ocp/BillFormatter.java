package ocp;

public class BillFormatter {

    public String format(int priceInCents, Currency currency) {
        String priceAsString = String.valueOf(priceInCents);
        String cents = priceAsString.substring(priceAsString.length()-2);
        int rest = priceInCents / 100;
        String formattedPrice = "";
        if (currency == Currency.EUROS) {
            formattedPrice = rest + "Û" + cents;
        } else if (currency == Currency.POUNDS) {
            formattedPrice = "£" + rest + "." + cents;
        }
        return formattedPrice;
    }
    

}
