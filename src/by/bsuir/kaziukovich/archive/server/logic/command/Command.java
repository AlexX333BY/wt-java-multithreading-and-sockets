package by.bsuir.kaziukovich.archive.server.logic.command;

public interface Command {
    String[] execute(String[] request) throws CommandException;
}
