package by.bsuir.kaziukovich.archive.server.domain.account;

import by.bsuir.kaziukovich.archive.server.domain.account.impl.ImmutableAccount;
import by.bsuir.kaziukovich.archive.server.domain.account.impl.SerializableAccount;

public class AccountFactory {
    public static Account createAccount(String username, String passwordHash, UserRole role) {
        return new ImmutableAccount(username, passwordHash, role);
    }

    public static Account createSerializableAccount(Account account) {
        return createSerializableAccount(account.getUsername(), account.getPasswordHash(), account.getRole());
    }

    public static Account createSerializableAccount(String username, String passwordHash, UserRole role) {
        return new SerializableAccount(username, passwordHash, role);
    }

    private AccountFactory() { }
}
