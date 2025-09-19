package servlets;

import jakarta.servlet.http.HttpSession;
import logging.AppLog;
import logging.ErrorFactory;
import exceptions.InputFault;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validators.ParametersChecker;
import org.jboss.logging.*;

import java.io.IOException;


@WebServlet("/app")
public class ControllerServlet extends HttpServlet {
    public ControllerServlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            process(req, res);
        } catch(ServletException | IOException e) {
            AppLog.error(ControllerServlet.class, e.getMessage(), e);
        }
    }



    private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("process starts");
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String R = req.getParameter("R");
        try {
            ParametersChecker.checkParams(x, y, R);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/areaCheck");
            dispatcher.forward(req, res);
        } catch(InputFault e) {
            ErrorFactory.feedbackError(req, res, HttpServletResponse.SC_BAD_REQUEST, ErrorFactory.BAD_REQUEST, e.getMessage());
        }

    }
}