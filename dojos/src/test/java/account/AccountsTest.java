package account;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import account.Account;



public class AccountsTest {
    
    private Client client1 = new Client(new ClientIdentity("customer1"));
    private Client client2 = new Client(new ClientIdentity("customer2"));

    
    @Test
    public void weCanDepositMoney() throws Exception {
        Account account = new Account(client1);
        account.deposit(new Money(3500));
        assertThat(account.balance(), equalTo(new Money(3500)));
        account.deposit(new Money(100));
        assertThat(account.balance(), equalTo(new Money(3600)));
    }
    
    @Test
    public void weCanWithDrawMoney() throws Exception {
        Account account = new Account(client1);
        account.deposit(new Money(12300));
        account.withdraw(new Money(2300));
        assertThat(account.balance(), equalTo(new Money(10000)));
    }
    
    @Test
    public void weCanTransferMoneyFromOneAccountToAnother() throws Exception {
        Account account1 = new Account(client1);
        account1.deposit(new Money(80));
        Account account2 = new Account(client2);
        account2.deposit(new Money(20));
        
        account1.transferTo(account2, new Money(80));
        
        assertThat(account1.balance(), equalTo(new Money(0)));
        assertThat(account2.balance(), equalTo(new Money(100)));
        
    }

    @Test
    public void weCannotTransferMoneyBetweenAccountsInDifferentBanks() throws Exception {
        Account account1 = new Account(client1);
        account1.deposit(new Money(80));
        Account account2 = new Account(client2);
        account2.deposit(new Money(20));
        
        account1.transferTo(account2, new Money(80));
    }

}
