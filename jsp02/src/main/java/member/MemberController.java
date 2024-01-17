package member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		// uri 짧은주소(ex. "/jsp02/member_servlet/list.do")
		String context = request.getContextPath();
		// ContextPath → 프로젝트이름(식별자)
		MemberDAO dao = new MemberDAO();

		// indexOf → 찾는 문자열이 참조문자열의 몇번째에 위치해 있는지 index 번호로 반환. (없을 경우:-1)
		if (url.indexOf("list.do") != -1) {
			Map<String, Object> map = new HashMap<>();
			List<MemberDTO> list = dao.list();
			map.put("list", list);
			map.put("count", list.size());
			request.setAttribute("map", map);
			String page = "/member/list.jsp"; // 데이터를 출력할 페이지 지정
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("join.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			MemberDTO dto = new MemberDTO(userid, passwd, name, address, tel);
			dao.insert(dto);
			response.sendRedirect(context + "/member_servlet/list.do");
		} else if (url.indexOf("view.do") != -1) {
			String userid = request.getParameter("userid");
			MemberDTO dto = dao.detail(userid);
			request.setAttribute("dto", dto);
			String page = "/member/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("update.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			MemberDTO dto = new MemberDTO(userid, passwd, name, address, tel);
			dao.update(dto);
			response.sendRedirect(context + "/member_servlet/list.do");
		} else if (url.indexOf("delete.do") != -1) {
			String userid = request.getParameter("userid");
			dao.delete(userid);
			response.sendRedirect(context + "/member_servlet/list.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
