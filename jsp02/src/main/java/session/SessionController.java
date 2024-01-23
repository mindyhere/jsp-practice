package session;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		MemberDAO dao = new MemberDAO();

		if (url.indexOf("login.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			String result = dao.login(dto);
			String page = "/session/login.jsp";

			if (result != null) {
				HttpSession session = request.getSession();
				//세션 객체
				session.setAttribute("userid", userid);
				session.setAttribute("message", result);
				page = "/session/main.jsp";
			}
			response.sendRedirect(request.getContextPath() + page);
			// sendRedirect 작업종료. 새로운주소로 이동 ↔ forward 작업진행 중인 주소로 이동
		} else if (url.indexOf("logout.do") != -1) {
			HttpSession session = request.getSession();
			session.invalidate();
			String page = request.getContextPath() + "/session/login.jsp?message=logout";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
