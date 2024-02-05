package shop;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		String path = request.getContextPath();

		if (url.contains("login.do")) {
			AdminDAO dao = new AdminDAO();
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");

			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			String name = dao.login(dto);

			if (name == null) {
				String page = path + "/shop/admin_login.jsp?message=error";
				response.sendRedirect(page);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("admin_userid", dto.getUserid());
				session.setAttribute("admin_name", name);
				session.setAttribute("userid", userid);
				session.setAttribute("name", name);
				session.setAttribute("result", name + "님 환영합니다.");

				String page = "/shop/admin_result.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if (url.contains("logout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();	// 세션 클리어
			String page = path + "/shop/admin_login.jsp?message=logout";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
