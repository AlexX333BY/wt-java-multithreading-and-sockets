package by.bsuir.kaziukovich.archive.server.dataaccess.account;

public class AccountDaoFactory {
    private static final AccountDao accountDao
            = new by.bsuir.kaziukovich.archive.server.dataaccess.account.impl.AccountDao();

    public static AccountDao getDao() {
        return accountDao;
    }

    private AccountDaoFactory() { }
}
