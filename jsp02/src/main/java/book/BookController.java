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
		BookDAO dao = new BookDAO(); // dao 생성. 필드변수 선언
		
		if (url.indexOf("list.do") != -1) {
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
			//System.out.println("폼 입력값 : " + dto);
			
			// dao에 저장요청
			dao.insert(dto);
			
			// 페이지 이동(도서 목록으로)
			response.sendRedirect(context + "/book_servlet/list.do");
		
		} else if (url.indexOf("view.do") != -1) {
			// idx 읽기
			int idx = Integer.parseInt(request.getParameter("idx"));
			//System.out.println("idx=" + idx);
			
			// dao detail 함수호출
			BookDTO dto = dao.detail(idx);
			//System.out.println("dto=" + dto);
			
			// 저장
			request.setAttribute("dto", dto);
			
			// 출력페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher("/book/detail.jsp");
			rd.forward(request, response);

		} else if (url.indexOf("update.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			int price = Integer.parseInt(request.getParameter("price"));
			int amount = Integer.parseInt(request.getParameter("amount"));

			BookDTO dto = new BookDTO(idx, title, author, price, amount);
			//System.out.println("dto: " + dto);
			dao.update(dto);
			response.sendRedirect("/jsp02/book_servlet/list.do");
		
		}else if(url.indexOf("delete.do")!=-1) {
			//레코드 번호
			int idx = Integer.parseInt(request.getParameter("idx")); 
			
			//dao 삭제함수 호출
			dao.delete(idx);
			
			//목록으로 이동
			response.sendRedirect("/jsp02/book_servlet/list.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
