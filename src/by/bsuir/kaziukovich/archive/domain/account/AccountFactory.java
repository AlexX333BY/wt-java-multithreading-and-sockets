package by.bsuir.kaziukovich.archive.domain.account;

import by.bsuir.kaziukovich.archive.domain.account.impl.ImmutableAccount;

public class AccountFactory {
    public static Account createAccount(String username, String passwordHash, UserRole role) {
        return new ImmutableAccount(username, passwordHash, role);
    }

    private AccountFactory() { }
}
