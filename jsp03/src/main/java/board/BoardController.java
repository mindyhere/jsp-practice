package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import page.PageUtil;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10, location = "c:/upload/")
//                  파일사이즈                    업로드 디렉토리 지정
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		/*
		 * getRequestURI() : 짧은 주소 /jsp03/board_servlet/list.do
		 * /jsp03/board_servlet/insert.do
		 * 
		 * getRequestURL() : 긴 주소 http://localhost/jsp03/board_servlet/list.do
		 * http://localhost/jsp03/board_servlet/insert.do
		 */
		String contextPath = request.getContextPath();
		BoardDAO dao = new BoardDAO();

		// 문자열.contains(키워드) → true/false
		if (url.contains("list.do")) {
			int count = dao.count();
			int cur_page = 1;
			if (request.getParameter("cur_page") != null) {
				cur_page = Integer.parseInt(request.getParameter("cur_page"));
			}

			PageUtil page = new PageUtil(count, cur_page);
			int start = page.getPageBegin();
			int end = page.getPageEnd();

			List<BoardDTO> list = dao.list(start, end);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp"); // 출력페이지
			rd.forward(request, response);

		} else if (url.contains("insert.do")) {
			BoardDTO dto = new BoardDTO();
			// 변수 초기화
			String filename = "-";
			int filesize = 0;

			try {
				for (Part part : request.getParts()) {
					filename = part.getSubmittedFileName(); // 파일이름

					// 첨부파일이 있을 때,
					if (filename != null && !filename.equals("null") && !filename.trim().equals("")) {
						filesize = (int) part.getSize();
						part.write(filename);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 폼 데이터
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String passwd = request.getParameter("passwd");
			String ip = request.getRemoteAddr(); // IP주소

			// dto → dao 요청
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContents(contents);
			dto.setPasswd(passwd);
			dto.setIp(ip); // IP주소
			if (filename == null || filename.trim().equals("")) {
				filename = "-"; // 첨부파일이 없는 경우 처리
			}
			dto.setFilename(filename);
			dto.setFilesize(filesize);
			dao.insert(dto);

			// 주소이동
			response.sendRedirect(contextPath + "/board_servlet/list.do");

		} else if (url.contains("view.do")) {
			// 글번호
			int num = Integer.parseInt(request.getParameter("num"));
			HttpSession session = request.getSession();

			// 조회수 증가처리
			dao.plus_hit(num, session); // 조회수 시간제한

			// dao 요청(레코드)
			BoardDTO dto = dao.view(num); // 레코드 1개 → 상세내용 가져오기
			request.setAttribute("dto", dto); // 저장

			// 출력 페이지로 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/board/view.jsp");
			rd.forward(request, response);

		} else if (url.contains("download.do")) {
			int num = Integer.parseInt(request.getParameter("num")); // 선택한 게시물 번호
			String filename = dao.getFilename(num);
			String path = "c:/upload/" + filename;

			byte[] buffer = new byte[4096]; // 바이트배열
			FileInputStream fis = new FileInputStream(path);
			String mimeType = getServletContext().getMimeType(path);
			if (mimeType == null) {
				mimeType = "application/octet-stream;charset=UTF-8"; // 기본타입
			}
			filename = new String(filename.getBytes("utf-8"), "8859_1"); // 한글이 깨지지 않도록 처리
			// 스트링.getBytes : 스트링 → 				바이트배열    서유럽언어

			// 웹요청: header(파일정보)+body(파일)
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			// setHeader → header 				특성 		첨부 			   파일이름 +변수(파일이름)
			ServletOutputStream out = response.getOutputStream(); // body
			int len; // 바이트수

			while (true) {
				len = fis.read(buffer, 0, buffer.length);
				// 파일읽기 ← 		버퍼 	  시작 	 	사이즈
				if (len == -1) { // 파일을 다 읽으면 : -1
					break;
				}
				out.write(buffer, 0, len); // 파일에 저장
			}
			// 메모리 정리
			out.flush(); // 버퍼 클리어
			out.close();
			fis.close();
			dao.plus_down(num); // 다운로드 횟수 증가

		} else if (url.contains("insert_comment.do")) {
			BoardCommentDTO dto = new BoardCommentDTO();
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			String writer = request.getParameter("writer");
			String contents = request.getParameter("contents");
			dto.setBoard_num(board_num);
			dto.setWriter(writer);
			dto.setContents(contents);
			dao.insert_comment(dto);

		} else if (url.contains("list_comment.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			List<BoardCommentDTO> list = dao.list_comment(num);
			request.setAttribute("list", list);
			String page = "/board/list_comment.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("check_pwd.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			String passwd = request.getParameter("passwd");
			String result = dao.check_pwd(num, passwd);
			String page = "";

			if (result != null) {
				page = "/board/edit.jsp";
				request.setAttribute("dto", dao.view(num));
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			} else {
				page = contextPath + "/board_servlet/view.do?num=" + num + "&message=error";
				response.sendRedirect(page);
			}
		} else if (url.contains("update.do")) {
			BoardDTO dto = new BoardDTO();
			String filename = "-";
			int filesize = 0;

			try {
				for (Part part : request.getParts()) {
					filename = part.getSubmittedFileName(); // 파일이름

					// 첨부파일이 있을 때,
					if (filename != null && !filename.equals("null") && !filename.equals("")) {
						filesize = (int) part.getSize();
						part.write(filename);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 폼 데이터
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String passwd = request.getParameter("passwd");
			String ip = request.getRemoteAddr(); // IP주소
			int num = Integer.parseInt(request.getParameter("num"));
			String delete_file = request.getParameter("delete_file"); // 파일삭제 체크박스
			System.out.println("파일삭제 체크확인 : " + delete_file);
			// 체크박스 체크 on → 첨부파일 삭제
			if (delete_file != null && delete_file.equals("on")) {
				String fileName = dao.getFilename(num);
				File f = new File("c:/upload/" + fileName);
				f.delete();

				dto.setNum(num);
				dto.setWriter(writer);
				dto.setSubject(subject);
				dto.setContents(contents);
				dto.setPasswd(passwd);
				dto.setIp(ip);
				// 첨부파일 없을 때, 파일명/파일크기 처리
				dto.setFilename("-");
				dto.setFilesize(0);
				dto.setDown(0);
				dao.update(dto);
			}

			// dto → dao 요청
			dto.setNum(num);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContents(contents);
			dto.setPasswd(passwd);
			dto.setIp(ip);

			if (filename == null || filename.trim().equals("")) {
				BoardDTO dto2 = dao.view(num);
				String name = dto2.getFilename();
				int size = dto2.getFilesize();
				int down = dto2.getDown();
				dto.setFilename(name);
				dto.setFilesize(size);
				dto.setDown(down);
			} else {
				dto.setFilename(filename);
				dto.setFilesize(filesize);
			}

			String result = dao.check_pwd(num, passwd);
			if (result != null) {
				dao.update(dto);
				String page = contextPath + "/board_servlet/list.do";
				response.sendRedirect(page);
			} else {
				request.setAttribute("dto", dto);
				String page = "/board/edit.jsp?pwd_error=y";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}

		} else if (url.contains("delete.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			String passwd = request.getParameter("passwd");
			String result = dao.check_pwd(num, passwd);

			if (result != null) {
				dao.delete(num);
				String page = contextPath + "/board_servlet/list.do";
				response.sendRedirect(page);
			} else {
				request.setAttribute("dto", dao.view(num));
				String page = "/board/edit.jsp?pwd_error=y";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}

		} else if (url.contains("input_reply.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDTO dto = dao.view(num);
			dto.setContents("====contents====\n" + dto.getContents());
			request.setAttribute("dto", dto);
			String page = "/board/reply.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("insert_reply.do")) {
			int num = 0;
			if (request.getParameter("num") != null) {
				num = Integer.parseInt(request.getParameter("num"));
				//게시물번호
			}

			BoardDTO dto = dao.view(num);
			// 답변글이 추가되었을 때 추가되는 목록 순서 업데이트 처리
			int group_num = dto.getGroup_num();	// 답변 그룹번호
			int re_order = dto.getRe_order() + 1; // 답변순서 +1
			int re_dept = dto.getRe_depth() + 1;  // 답변단계 +1

			// 폼에 입력한 데이터
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String passwd = request.getParameter("passwd");

			// dto로 묶어서 전달 → dao에 요청
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContents(contents);
			dto.setPasswd(passwd);
			dto.setGroup_num(group_num);
			dto.setRe_order(re_order);
			dto.setRe_depth(re_dept);
			dto.setFilename("-");
			dto.setFilesize(0);
			dto.setDown(0);
			dao.update_order(group_num, re_order); // 답글순서 변경
			dao.insert_reply(dto);	// 답변글 DB저장

			String page = "/board_servlet/list.do";
			response.sendRedirect(request.getContextPath() + page);

		} else if (url.contains("search.do")) {
			String search_option = request.getParameter("search_option");
			String keyword = request.getParameter("keyword");
			int count = dao.count(search_option, keyword);
			int cur_page = 1;

			if (request.getParameter("cur_page") != null) {
				cur_page = Integer.parseInt(request.getParameter("cur_page"));
			}

			PageUtil page = new PageUtil(count, cur_page);
			int start = page.getPageBegin();
			int end = page.getPageEnd();

			List<BoardDTO> list = dao.list_search(search_option, keyword, start, end);
			request.setAttribute("list", list);
			request.setAttribute("search_option", search_option);
			request.setAttribute("keyword", keyword);
			request.setAttribute("page", page);

			RequestDispatcher rd = request.getRequestDispatcher("/board/search.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
