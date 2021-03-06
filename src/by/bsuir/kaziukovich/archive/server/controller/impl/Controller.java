package by.bsuir.kaziukovich.archive.server.controller.impl;

import by.bsuir.kaziukovich.archive.domain.logger.Logger;
import by.bsuir.kaziukovich.archive.domain.request.Request;
import by.bsuir.kaziukovich.archive.domain.request.RequestCode;
import by.bsuir.kaziukovich.archive.domain.response.Response;
import by.bsuir.kaziukovich.archive.domain.response.ResponseCode;
import by.bsuir.kaziukovich.archive.server.controller.CommandMapFactory;
import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.account.AccountDaoFactory;
import by.bsuir.kaziukovich.archive.server.domain.account.UserRole;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

public class Controller implements by.bsuir.kaziukovich.archive.server.controller.Controller {
    @Override
    public Response process(Request request) {
        UserRole role;
        Command command;

        try {
            role = AccountDaoFactory.getDao().get(request.getRequester()).getRole();
        } catch (DaoException | IllegalArgumentException e) {
            role = null;
        }

        try {
            command = CommandMapFactory.getCommands(role).get(RequestCode.valueOf(request.getRequestCode()));
        } catch (IllegalArgumentException | NullPointerException e) {
            command = null;
        }
        if (command == null) {
            return new SerializableResponse(ResponseCode.NO_SUCH_COMMAND.toString(), null);
        }

        try {
            return new SerializableResponse(ResponseCode.SUCCESS.toString(), command.execute(request.getRequestContent()));
        } catch (CommandException e) {
            Logger.log(e);
            return new SerializableResponse(ResponseCode.INTERNAL_FAILURE.toString(), null);
        } catch (IllegalArgumentException e) {
            Logger.log(e);
            return new SerializableResponse(ResponseCode.ILLEGAL_ARGUMENTS.toString(), null);
        }
    }
}
