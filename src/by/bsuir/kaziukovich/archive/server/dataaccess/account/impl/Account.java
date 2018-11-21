package by.bsuir.kaziukovich.archive.server.dataaccess.account.impl;

import by.bsuir.kaziukovich.archive.domain.account.UserRole;
import java.util.Objects;

public class Account implements by.bsuir.kaziukovich.archive.domain.account.Account {
    private final String username;

    private final String passwordHash;

    private final UserRole role;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        Account toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (Account) o;
        return Objects.equals(username, toCompare.username) && Objects.equals(passwordHash, toCompare.passwordHash) &&
                role == toCompare.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordHash, role);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@username: " + username + ", passwordHash: " + passwordHash + ", role: " + role;
    }

    public Account(String username, String passwordHash, UserRole role) {
        if ((username == null) || (passwordHash == null) || (role == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        this.passwordHash = passwordHash.trim();
        this.role = role;
        this.username = username.trim();

        if (this.username.isEmpty() || this.passwordHash.isEmpty()) {
            throw new IllegalArgumentException("Arguments cannot be empty");
        }
    }
}
