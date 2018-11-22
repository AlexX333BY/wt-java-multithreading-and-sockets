package by.bsuir.kaziukovich.archive.server.dataaccess.account;

import by.bsuir.kaziukovich.archive.server.domain.account.Account;
import by.bsuir.kaziukovich.archive.server.domain.account.UserRole;
import by.bsuir.kaziukovich.archive.server.dataaccess.Dao;
import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;

public interface AccountDao extends Dao<Account> {
    Account get(String username) throws DaoException;

    void delete(String username) throws DaoException;

    void update(String username, String password, UserRole role) throws DaoException;

    void add(String username, String password, UserRole role) throws DaoException;
}
