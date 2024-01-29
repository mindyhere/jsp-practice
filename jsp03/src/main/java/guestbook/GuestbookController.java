package guestbook;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		GuestbookDAO dao = new GuestbookDAO();

		if (uri.indexOf("list.do") != -1) {
			String searchkey = "name";
			String search = "";

			if (request.getParameter("searchkey") != null) {
				searchkey = request.getParameter("searchkey");
			}
			if (request.getParameter("search") != null) {
				search = request.getParameter("search");
			}

			List<GuestbookDTO> items = dao.list(searchkey, search);
			request.setAttribute("list", items);
			request.setAttribute("count", items.size());
			request.setAttribute("searchkey", searchkey);
			request.setAttribute("search", search);
			String url = "/guestbook/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (uri.indexOf("insert.do") != -1) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String contents = request.getParameter("contents");
			GuestbookDTO dto = new GuestbookDTO();
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContents(contents);
			dao.insert(dto);
			String url = "/gb_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
		} else if (uri.indexOf("passwd_check.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String passwd = request.getParameter("passwd");
			boolean result = dao.check_pwd(idx, passwd);
			String url = "";

			if (result) {
				url = "/guestbook/edit.jsp";
				GuestbookDTO dto = dao.detail(idx);
				request.setAttribute("dto", dto);
			} else {
				url = "/gb_servlet/list.do";
			}
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (uri.indexOf("update.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String contents = request.getParameter("contents");
			GuestbookDTO dto = new GuestbookDTO();
			dto.setIdx(idx);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContents(contents);
			dao.update(dto);
			String url = "/gb_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
		} else if (uri.indexOf("delete.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			dao.delete(idx);
			String url = "/gb_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
