package ocp;

public class BillFormatter {

    public String format(int price, Currency currency) {
        String formattedPrice = "";
        if (currency == Currency.EUROS) {
            formattedPrice = price + "Û";
        } else if (currency == Currency.POUNDS) {
            formattedPrice = "£" + price;
        }
        return formattedPrice;
    }
    

}
