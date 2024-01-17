package ch02;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SalaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		int basic = Integer.parseInt(request.getParameter("basic"));
		int bonus = Integer.parseInt(request.getParameter("bonus"));
		int total = basic * 12 + bonus;
		int tax = (int) (total * 0.05);
		int money = total - tax;

		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("basic", basic);
		map.put("bonus", bonus);
		map.put("total", total);
		map.put("tax", tax);
		map.put("money", money);

		request.setAttribute("salary", map);

		String page = "/ch02/salary_result.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
