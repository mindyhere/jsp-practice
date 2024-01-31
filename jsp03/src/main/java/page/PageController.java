package page;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		EmpDAO dao = new EmpDAO();

		if (url.indexOf("list.do") != -1) {
			int count = dao.count();
			int curPage = 1;

			if (request.getParameter("cur_page") != null) {
				curPage = Integer.parseInt(request.getParameter("cur_page"));
			}

			PageUtil page = new PageUtil(count, curPage);
			int start = page.getPageBegin();
			int end = page.getPageEnd();
			List<EmpDTO> list = dao.list(start, end);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			RequestDispatcher rd = request.getRequestDispatcher("/page/list.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
