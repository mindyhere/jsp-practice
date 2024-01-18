package book;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/book_servlet/*") → 톰캣 상위버전에서만 사용가능(구버전X)
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청한 주소 확인
		String url = request.getRequestURI();
		String context = request.getContextPath();
		System.out.println("요청한 주소: " + url);
		BookDAO dao = new BookDAO();
		if (url.indexOf("list.do") != -1) {

			// dao 생성

			// 도서목록 가져오기
			List<BookDTO> items = dao.list_book();
			// System.out.println(items);

			// 저장
			request.setAttribute("items", items);

			// 페이지 이동
			RequestDispatcher rd = request.getRequestDispatcher("/book/book_list.jsp");
			rd.forward(request, response);
		} else if (url.indexOf("insert.do") != -1) {
			// 도서등록 폼에 입력한 값 저장
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			int price = Integer.parseInt(request.getParameter("price"));
			int amount = Integer.parseInt(request.getParameter("amount"));

			BookDTO dto = new BookDTO(title, author, price, amount);
			System.out.println("폼 입력값 : " + dto);
			// dao에 저장요청
			dao.insert(dto);
			// 페이지 이동(도서 목록으로)
			response.sendRedirect(context + "/book_servlet/list.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
