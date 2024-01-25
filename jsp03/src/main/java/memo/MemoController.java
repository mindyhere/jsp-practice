package memo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemoDAO dao = new MemoDAO();
		String url = request.getRequestURL().toString();
		// 전체주소(스트링버퍼) http://localhost/jsp03/memo_servlet/list.do → toString() 형변환
		// String url=request.getRequestURI(); // → 짧은주소 (/jsp03/memo_servlet/list.do)

		if (url.indexOf("list.do") != -1) {
			String search_option = request.getParameter("search_option");
			if (search_option == null || search_option.equals("")) {
				search_option = "writer";
			}

			String search = request.getParameter("search");
			if (search == null) {
				search = "";
			}
			
			List<Map<String, Object>> list = dao.list(search_option, search);
			request.setAttribute("list", list);
			request.setAttribute("search_option", search_option);
			request.setAttribute("search", search);
			String page = "/memo/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("insert.do") != -1) {
			String writer = request.getParameter("writer");
			String memo = request.getParameter("memo");
			Map<String, Object> map = new HashMap<>();
			map.put("writer", writer);
			map.put("memo", memo);
			dao.insert(map);
		} else if (url.indexOf("view.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			Map<String, Object> map = dao.view(idx);
			request.setAttribute("map", map);
			String page = "/memo/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("del.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			dao.delete(idx);
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");
		} else if (url.indexOf("delete_all.do") != -1) {
			String[] idx = request.getParameterValues("idx");
			if (idx != null) {
				for (int i = 0; i < idx.length; i++) {
					dao.delete(Integer.parseInt(idx[i]));
				}
			}
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");
		} else if (url.indexOf("update.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String writer = request.getParameter("writer");
			String memo = request.getParameter("memo");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idx", idx);
			map.put("writer", writer);
			map.put("memo", memo);
			dao.update(map);
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
