package by.bsuir.kaziukovich.archive.server.data.account.impl;

import by.bsuir.kaziukovich.archive.server.domain.account.Account;
import by.bsuir.kaziukovich.archive.server.domain.account.UserRole;
import java.io.Serializable;
import java.util.Objects;

public class SerializableAccount implements Account, Serializable {
    private String username;

    private String passwordHash;

    private UserRole role;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPasswordHash() {
        return null;
    }

    @Override
    public UserRole getRole() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        SerializableAccount toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (SerializableAccount) o;
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

    SerializableAccount(String username, String passwordHash, UserRole role) {
        if ((username == null) || (passwordHash == null) || (role == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        this.passwordHash = passwordHash.trim();
        this.role = role;
        this.username = username.trim();

        if (this.username.isEmpty() || (this.passwordHash.isEmpty())) {
            throw new IllegalArgumentException("Arguments shouldn't be empty");
        }
    }

    SerializableAccount(Account account) {
        this(account.getUsername(), account.getPasswordHash(), account.getRole());
    }

    public SerializableAccount() { }
}
