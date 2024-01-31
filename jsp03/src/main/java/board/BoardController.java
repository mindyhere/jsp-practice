package board;

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
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10, location = "c:/upload/") 	
//                  파일사이즈                    업로드 디렉토리 지정
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		/* getRequestURI() : 짧은 주소 
		 * /jsp03/board_servlet/list.do 
		 * /jsp03/board_servlet/insert.do
		 * 
		 * getRequestURL() : 긴 주소
		 * http://localhost/jsp03/board_servlet/list.do
		 * http://localhost/jsp03/board_servlet/insert.do
		 */		
		String contextPath = request.getContextPath();
		BoardDAO dao = new BoardDAO();

		if (url.contains("list.do")) {
			// 문자열.contains(키워드) → true/false
			List<BoardDTO> list = dao.list();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp"); // 출력페이지
			rd.forward(request, response);
		} else if (url.contains("insert.do")) {
			BoardDTO dto = new BoardDTO();
			// 변수 초기화
			String filename = "-";	
			int filesize = 0;

			try {
				for (Part part : request.getParts()) {
					filename = part.getSubmittedFileName();	// 파일이름
					
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

			// dto → dao 요청
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContents(contents);
			dto.setPasswd(passwd);
			dto.setIp(ip); // IP주소
			
			if (filename == null || filename.trim().equals("")) {
				filename = "-";	// 첨부파일이 없는 경우 처리
			}
			dto.setFilename(filename);
			dto.setFilesize(filesize);
			dao.insert(dto);

			// 주소이동
			response.sendRedirect(contextPath + "/board_servlet/list.do");

		} else if (url.contains("download.do")) {
			int num = Integer.parseInt(request.getParameter("num")); // 선택한 게시물 번호
			String filename = dao.getFilename(num);
			String path = "c:/upload/" + filename;
			
			byte[] buffer = new byte[4096]; // 바이트배열
			FileInputStream fis = new FileInputStream(path);
			// 사용자 ← 서버
			String mimeType = getServletContext().getMimeType(path);
			// 					  /jps03 				파일종류
			if (mimeType == null) {
				mimeType = "application/octet-stream;charset=UTF-8"; // 기본타입
			}
			filename = new String(filename.getBytes("utf-8"), "8859_1"); // 한글이 깨지지 않도록 처리
			// 스트링.getBytes : 	스트링 → 바이트배열 	 서유럽언어

			// 웹요청: header(파일정보)+body(파일)
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			// setHeader → header	 특성 					첨부   파일이름  +변수(파일이름)
			ServletOutputStream out = response.getOutputStream(); // body
			int len; // 바이트수

			while (true) {
				len = fis.read(buffer, 0, buffer.length);
				// 파일읽기   ←  버퍼 시작   사이즈
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
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
