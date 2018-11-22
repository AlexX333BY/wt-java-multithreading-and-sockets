package by.bsuir.kaziukovich.archive.server.controller;

public class ControllerFactory {
    private static final Controller controller = new by.bsuir.kaziukovich.archive.server.controller.impl.Controller();

    public static Controller getController() {
        return controller;
    }

    private ControllerFactory() { }
}
