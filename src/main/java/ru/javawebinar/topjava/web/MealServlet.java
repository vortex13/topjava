package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        int exceed;
        try {
            exceed = Integer.parseInt(request.getParameter("exceed"));
        } catch (NumberFormatException e) {
            PrintWriter out = response.getWriter();
            out.println("Укажите параметр exceed");
            out.close();
            return;
        }
        List<MealWithExceed> meals = MealsUtil.getWithExceeded(MealsUtil.getMeals(), exceed);
        request.setAttribute("meals", meals);
        //делаете forward на jsp для отрисовки таблицы (при redirect атрибуты теряются).
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
