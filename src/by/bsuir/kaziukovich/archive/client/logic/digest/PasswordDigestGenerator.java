package by.bsuir.kaziukovich.archive.client.logic.digest;

public interface PasswordDigestGenerator {
    String generate(String password) throws PasswordDigestException;
}
