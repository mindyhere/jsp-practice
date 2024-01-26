package product;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductDAO dao = new ProductDAO();
		String url = request.getRequestURI();

		System.out.println("요청주소 : " + url);
		System.out.println("indexOf : " + url.indexOf("list.do")); // 위치 반환(없을 경우 : -1)
		System.out.println("contains : " + url.contains("list.do")); // True or False 로 반환

		if (url.contains("list.do")) {
			List<Map<String, Object>> items = dao.list();
			System.out.println(items);

			request.setAttribute("items", items);
			RequestDispatcher rd = request.getRequestDispatcher("/product/list.jsp");
			rd.forward(request, response);
		} else if (url.contains("insert.do")) {
			// 폼 데이터 변수에저장
			String product_name = request.getParameter("product_name");
			int price = Integer.parseInt(request.getParameter("price"));
			int amount = Integer.parseInt(request.getParameter("amount"));

			System.out.println("품명:" + product_name);
			System.out.println("가격:" + price);
			System.out.println("수량:" + amount);

			// dao.insert 호출
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("product_name", product_name);
			map.put("price", price);
			map.put("amount", amount);
			dao.insert(map);

			// 작업완료 후 list로 이동 : sendRedirect
			response.sendRedirect("/jsp03/product_servlet/list.do");
		} else if (url.contains("detail.do")) {
			// 상품코드 읽어오기
			int product_code = Integer.parseInt(request.getParameter("product_code"));

			// dao 요청
			Map<String, Object> map = dao.detail(product_code);
			System.out.println("map : " + map);

			request.setAttribute("map", map);
			// 출력페이지로 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/product/detail.jsp");
			rd.forward(request, response);
		} else if (url.contains("update.do")) {
			int product_code = Integer.parseInt(request.getParameter("product_code"));
			String product_name = request.getParameter("product_name");
			int price = Integer.parseInt(request.getParameter("price"));
			int amount = Integer.parseInt(request.getParameter("amount"));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("product_code", product_code);
			map.put("product_name", product_name);
			map.put("price", price);
			map.put("amount", amount);
			dao.update(map);

			response.sendRedirect("/jsp03/product_servlet/list.do");
		} else if (url.contains("delete.do")) {
			int product_code = Integer.parseInt(request.getParameter("product_code"));
			dao.delete(product_code);
			response.sendRedirect("/jsp03/product_servlet/list.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
