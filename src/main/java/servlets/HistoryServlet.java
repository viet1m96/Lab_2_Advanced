package servlets;

import data_services.CollectionManager;
import data_services.ResultJsonService;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Result;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    @Inject
    CollectionManager collectionManager;
    @Inject
    private ResultJsonService resultJsonService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String jSessionId = req.getSession().getId();
        List<Result> resWithId = collectionManager.getAllElementWithSessionId(jSessionId);
        String jsonList = resultJsonService.toJson(resWithId);
        res.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            out.write(jsonList);
        }
    }
}
