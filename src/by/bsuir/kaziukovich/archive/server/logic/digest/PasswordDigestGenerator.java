package by.bsuir.kaziukovich.archive.server.logic.digest;

public interface PasswordDigestGenerator {
    String generate(String password) throws PasswordDigestException;
}
