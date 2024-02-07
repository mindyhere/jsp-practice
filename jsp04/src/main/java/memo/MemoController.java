package memo;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		MemoDAO dao = new MemoDAO();

		if (url.indexOf("list.do") != -1) {
			String searchkey = request.getParameter("searchkey");
			String search = request.getParameter("search");
			
			if(searchkey==null) searchkey="writer";
			if(search==null) search="";
			
			List<MemoDTO> list = dao.listMemo(searchkey, search);
			request.setAttribute("list", list);
			String page = "/memo/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("insert.do") != -1) {
			String writer = request.getParameter("writer");
			String memo = request.getParameter("memo");
			MemoDTO dto = new MemoDTO(writer, memo);
			dao.insertMemo(dto);
		} else if (url.indexOf("view.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			MemoDTO dto = dao.viewMemo(idx);
			request.setAttribute("dto", dto);
			String page = "/memo/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("update.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String writer = request.getParameter("writer");
			String memo = request.getParameter("memo");
			MemoDTO dto = new MemoDTO();

			dto.setIdx(idx);
			dto.setWriter(writer);
			dto.setMemo(memo);
			dao.updateMemo(dto);
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");
		} else if (url.indexOf("del.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			dao.deleteMemo(idx);
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");

		} else if (url.indexOf("delete_all.do") != -1) {
			String[] idx = request.getParameterValues("idx");
			if (idx != null) {
				for (int i = 0; i < idx.length; i++) {
					dao.deleteMemo(Integer.parseInt(idx[i]));
				}
			}
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
