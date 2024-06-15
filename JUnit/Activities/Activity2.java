package Activities;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Activity2 {
    @Test
    void notEnoughFunds(){
        BankAccount account = new BankAccount(9);
        assertThrows("Balance Must be greater than the amount of withdrawal",NotEnoughFundsException.class, () -> account.withdraw(10));


    }

    @Test
    void enoughFunds(){
        BankAccount account = new BankAccount(100);
        assertDoesNotThrow(()->account.withdraw(100));
    }
}
