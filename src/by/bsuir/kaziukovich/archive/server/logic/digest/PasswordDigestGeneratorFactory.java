package by.bsuir.kaziukovich.archive.server.logic.digest;

import by.bsuir.kaziukovich.archive.server.logic.digest.impl.Md5DigestGenerator;

public class PasswordDigestGeneratorFactory {
    private final static PasswordDigestGenerator passwordDigestGenerator = new Md5DigestGenerator();

    public static PasswordDigestGenerator getPasswordDigestGenerator() {
        return passwordDigestGenerator;
    }

    private PasswordDigestGeneratorFactory() { }
}
