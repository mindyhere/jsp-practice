package ch02;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Servlet 서버에서 실행되는 자바클래스
//@어노테이션. 코드에 대한 설명문 → ("/ch02_servlet/sum.do") 가상의 url과 class mapping
//코드작성 → 실행 → 톰캣 처리 → 결과출력
@WebServlet("/ch02_servlet/sum.do")
public class SumController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 요청사항 브라우저 응답/출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get 방식 호출...");
		int number = Integer.parseInt(request.getParameter("number"));
		int sum = 0;
		for (int i = 1; i <= number; i++) {
			sum += i;
		}
		request.setAttribute("sum", sum);
		RequestDispatcher rd = request.getRequestDispatcher("/ch02/sum_result.jsp"); // 출력 페이지 주소
		rd.forward(request, response); // 데이터 전달
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post 방식 호출...");
		doGet(request, response);
	}

}
