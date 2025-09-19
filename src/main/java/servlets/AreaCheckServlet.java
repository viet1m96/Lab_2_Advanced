package servlets;

import data_services.CollectionManager;
import data_services.ResultJsonService;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logging.AppLog;
import models.Result;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/areaCheck")
public class AreaCheckServlet extends HttpServlet {

    @Inject
    CollectionManager collectionManager;
    @Inject
    private ResultJsonService resultJsonService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            processRequest(req, res);
        } catch(ServletException | IOException e) {
            AppLog.error(AreaCheckServlet.class, e.getMessage(), e);
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BigDecimal x = new BigDecimal(req.getParameter("x"));
        BigDecimal y = new BigDecimal(req.getParameter("y"));
        BigDecimal R = new BigDecimal((req.getParameter("R")));
        boolean hit;
        HttpSession session = req.getSession();
        long start = System.nanoTime();
        if(checkCircle(x, y, R) || checkSquare(x, y, R) || checkTriangle(x, y, R)) {
            hit = true;
        } else {
            hit = false;
        }
        long end = System.nanoTime();
        Double calTime = (end - start) / 1000000.0;
        LocalDateTime releaseTime = LocalDateTime.now();
        Result result = new Result(req.getSession().getId(), x, y, R, hit, calTime, releaseTime);
        collectionManager.addNewElement(result);
        res.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            String json = resultJsonService.toJson(result);
            out.write(json);
        }
    }

    private boolean checkSquare(BigDecimal x, BigDecimal y, BigDecimal R) {
        return x.compareTo(BigDecimal.ZERO) <= 0
                && y.compareTo(BigDecimal.ZERO) >= 0
                && x.compareTo(R.multiply(BigDecimal.valueOf(-1)).divide(BigDecimal.valueOf(2))) >= 0
                && y.compareTo(R) <= 0;
    }

    private boolean checkCircle(BigDecimal x, BigDecimal y, BigDecimal R) {
        return x.compareTo(BigDecimal.ZERO) >= 0
                && y.compareTo(BigDecimal.ZERO) >= 0
                && (x.multiply(x).add(y.multiply(y)).compareTo(R.multiply(R)) <= 0);
    }

    private boolean checkTriangle(BigDecimal x, BigDecimal y, BigDecimal R) {
        return x.compareTo(BigDecimal.ZERO) >= 0
                && y.compareTo(BigDecimal.ZERO) <= 0
                && (x.subtract(y.multiply(BigDecimal.valueOf(2))).compareTo(R) <= 0);
    }
}