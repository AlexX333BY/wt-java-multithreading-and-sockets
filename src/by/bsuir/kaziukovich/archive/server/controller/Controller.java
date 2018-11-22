package by.bsuir.kaziukovich.archive.server.controller;

import by.bsuir.kaziukovich.archive.domain.request.Request;
import by.bsuir.kaziukovich.archive.domain.response.Response;

public interface Controller {
    Response process(Request request);
}
