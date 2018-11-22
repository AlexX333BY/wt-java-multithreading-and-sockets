package by.bsuir.kaziukovich.archive.server.controller;

import by.bsuir.kaziukovich.archive.domain.account.UserRole;
import by.bsuir.kaziukovich.archive.server.controller.request.RequestCode;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.impl.account.AddAccountCommand;
import by.bsuir.kaziukovich.archive.server.logic.command.impl.account.DoesAccountExistCommand;
import by.bsuir.kaziukovich.archive.server.logic.command.impl.account.UpdateAccountCommand;
import by.bsuir.kaziukovich.archive.server.logic.command.impl.record.*;
import java.util.HashMap;
import java.util.Map;

public class CommandMapFactory {
    private static final Map<UserRole, Map<RequestCode, Command>> commandMap;

    static {
        commandMap = new HashMap<>();
        commandMap.put(null, getUnauthorizedCommands());
        commandMap.put(UserRole.ADMIN, getAdminCommands());
        commandMap.put(UserRole.USER, getUserCommands());
    }

    private static Map<RequestCode, Command> getUnauthorizedCommands() {
        HashMap<RequestCode, Command> result = new HashMap<>();

        result.put(RequestCode.ADD_ACCOUNT, new AddAccountCommand());
        result.put(RequestCode.DOES_ACCOUNT_EXIST, new DoesAccountExistCommand());
        result.put(RequestCode.LOGIN, new AddAccountCommand());
        return result;
    }

    private static Map<RequestCode, Command> getUserCommands() {
        HashMap<RequestCode, Command> result = new HashMap<>();

        result.put(RequestCode.GET_ALL_RECORDS, new GetAllRecordsCommand());
        result.put(RequestCode.GET_RECORDS_BY_GROUP, new GetRecordsByGroupCommand());
        return result;
    }

    private static Map<RequestCode, Command> getAdminCommands() {
        HashMap<RequestCode, Command> result = new HashMap<>();

        result.put(RequestCode.GET_ALL_RECORDS, new GetAllRecordsCommand());
        result.put(RequestCode.GET_RECORDS_BY_GROUP, new GetRecordsByGroupCommand());
        result.put(RequestCode.ADD_ACCOUNT, new AddAccountCommand());
        result.put(RequestCode.ADD_RECORD, new AddRecordCommand());
        result.put(RequestCode.DELETE_RECORD, new DeleteRecordCommand());
        result.put(RequestCode.UPDATE_RECORD, new UpdateRecordCommand());
        result.put(RequestCode.UPDATE_ACCOUNT, new UpdateAccountCommand());
        return result;
    }

    public static Map<RequestCode, Command> getCommands(UserRole userRole) {
        return new HashMap<>(commandMap.get(userRole));
    }

    private CommandMapFactory() { }
}
