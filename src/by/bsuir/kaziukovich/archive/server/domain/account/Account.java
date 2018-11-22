package by.bsuir.kaziukovich.archive.server.domain.account;

public interface Account {
    String getUsername();

    String getPasswordHash();

    UserRole getRole();
}
