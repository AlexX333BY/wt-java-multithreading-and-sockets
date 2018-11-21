package by.bsuir.kaziukovich.archive.domain;

public interface Account {
    String getUsername();

    String getPasswordHash();

    UserRole getRole();
}
