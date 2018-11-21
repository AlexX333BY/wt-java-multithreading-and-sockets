package by.bsuir.kaziukovich.archive.domain.account;

public interface Account {
    String getUsername();

    String getPasswordHash();

    UserRole getRole();
}
