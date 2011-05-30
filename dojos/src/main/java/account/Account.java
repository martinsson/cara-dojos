package account;

public class Account {
    
    private Money balance = new Money(0);
    private final Client client;

    public Account(Client client) {
        this.client = client;
    }

    public void deposit(Money money) {
        balance.add(money) ;
    }

    public Money balance() {
        return balance;
    }

    public void withdraw(Money money) {
        balance.substract(money);
    }

    public void transferTo(Account account2, Money money) {
        withdraw(money);
        account2.deposit(money);
    }

}
