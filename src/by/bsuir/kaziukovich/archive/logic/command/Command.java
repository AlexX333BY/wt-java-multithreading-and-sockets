package by.bsuir.kaziukovich.archive.logic.command;

import by.bsuir.kaziukovich.archive.logic.command.request.CommandRequest;
import by.bsuir.kaziukovich.archive.logic.command.response.CommandResponse;

public interface Command {
    CommandResponse execute(CommandRequest request) throws CommandException;
}
