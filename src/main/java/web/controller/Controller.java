package web.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.util.MyException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    public void process(ServletContext application, HttpServletRequest request, HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson) throws ServletException, IOException, MyException;
}

