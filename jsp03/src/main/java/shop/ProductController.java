package shop;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		String path = request.getContextPath();
		ProductDAO dao = new ProductDAO();

		if (url.indexOf("list.do") != -1) {
			List<ProductDTO> items = dao.listProduct();
			request.setAttribute("list", items);
			RequestDispatcher rd = request.getRequestDispatcher("/shop/product_list.jsp");
			rd.forward(request, response);

		} else if (url.indexOf("detail.do") != -1) {
			int product_code = Integer.parseInt(request.getParameter("product_code"));
			ProductDTO dto = dao.detailProduct(product_code);
			request.setAttribute("dto", dto);
			RequestDispatcher rd = request.getRequestDispatcher("/shop/product_detail.jsp");
			rd.forward(request, response);

		} else if (url.indexOf("insert_product.do") != -1) {
			ServletContext application = request.getSession().getServletContext(); // 서버 정보
			String img_path = application.getRealPath("/images/");
			//					 서버정보		배포경로
			// C:\work\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jsp03\images\	
			// 개발디렉토리에 복사 → part.write("C:/work/jsp03/src/main/webapp/images/"+filename);	
			String filename = " ";

			try {
				for (Part part : request.getParts()) {
					filename = part.getSubmittedFileName();
					System.out.println("filename:" + filename);
					if (filename != null && !filename.trim().equals("")) {
						part.write(img_path + filename);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String product_name = request.getParameter("product_name");
			int price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");

			ProductDTO dto = new ProductDTO();
			dto.setProduct_name(product_name);
			dto.setPrice(price);
			dto.setDescription(description);
			if (filename == null || filename.trim().equals("")) {
				filename = "-";
			}
			dto.setFilename(filename);
			dao.insertProduct(dto);

			String page = path + "/product_servlet/list.do";
			response.sendRedirect(page);

		} else if (url.indexOf("edit.do") != -1) {
			int product_code = Integer.parseInt(request.getParameter("product_code"));
			ProductDTO dto = dao.detailProduct(product_code);
			request.setAttribute("dto", dto);
			RequestDispatcher rd = request.getRequestDispatcher("/shop/product_edit.jsp");
			rd.forward(request, response);

		} else if (url.indexOf("update.do") != -1) {
			ServletContext application = request.getSession().getServletContext();
			String img_path = application.getRealPath("/images/");
			String filename = " ";

			try {
				for (Part part : request.getParts()) {
					filename = part.getSubmittedFileName();

					if (filename != null && !filename.trim().equals("")) {
						part.write(img_path + filename);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String product_name = request.getParameter("product_name");
			int price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");
			int product_code = Integer.parseInt(request.getParameter("product_code"));

			ProductDTO dto = new ProductDTO();
			dto.setProduct_name(product_name);
			dto.setPrice(price);
			dto.setDescription(description);
			dto.setProduct_code(product_code);
			if (filename == null || filename.trim().equals("")) {
				ProductDTO dto2 = dao.detailProduct(product_code);
				filename = dto2.getFilename();
				dto.setFilename(filename);
			} else {
				dto.setFilename(filename);
			}
			dao.updateProduct(dto);

			String page = path + "/product_servlet/list.do";
			response.sendRedirect(page);

		} else if (url.indexOf("delete.do") != -1) {
			int product_code = Integer.parseInt(request.getParameter("product_code"));
			dao.deleteProduct(product_code);
			String page = path + "/product_servlet/list.do";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
