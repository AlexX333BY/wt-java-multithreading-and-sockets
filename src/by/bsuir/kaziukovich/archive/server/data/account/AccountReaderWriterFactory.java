package by.bsuir.kaziukovich.archive.server.data.account;

import by.bsuir.kaziukovich.archive.server.data.account.impl.AccountXmlFileReaderWriter;

public class AccountReaderWriterFactory {
    private static final AccountReaderWriter readerWriter = new AccountXmlFileReaderWriter();

    public static AccountReaderWriter getReaderWriter() {
        return readerWriter;
    }

    private AccountReaderWriterFactory() { }
}
