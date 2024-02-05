package shop;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		String path = request.getContextPath();
		MemberDAO dao = new MemberDAO();

		if (url.indexOf("login.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			String name = dao.login(dto);

			if (name == null) {
				String page = "/shop/login.jsp?message=error";
				response.sendRedirect(path + page);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
				session.setAttribute("name", name);
				request.setAttribute("result", name + " 님 환영합니다.");
				String page = "/shop/login_result.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if (url.indexOf("logout.do") != -1) {
			HttpSession session = request.getSession();
			session.invalidate();
			String page = path + "/shop/login.jsp?message=logout";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
