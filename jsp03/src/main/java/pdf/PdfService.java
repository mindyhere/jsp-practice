package pdf;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import shop.CartDAO;
import shop.CartDTO;

public class PdfService {
	public String createPdf() {
		CartDAO dao = new CartDAO();
		String message = "";

		try {
			// Document → pdf문서객체
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("c:/work/sample.pdf"));
			document.open();

			// 폰트설정
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			Font font = new Font(baseFont, 12);

			PdfPTable table = new PdfPTable(4); // 한 페이지에 보여지는 테이블 행 개수
			Chunk chunk = new Chunk("장바구니", font);
			Paragraph ph = new Paragraph(chunk);
			ph.setAlignment(Element.ALIGN_CENTER);
			document.add(ph);
			document.add(Chunk.NEWLINE); // NEWLINE → 줄바꿈
			document.add(Chunk.NEWLINE);

			// 테이블 헤더
			PdfPCell cell1 = new PdfPCell(new Phrase("상품명", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1); // 테이블에 셀 추가

			PdfPCell cell2 = new PdfPCell(new Phrase("단가", font));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("수량", font));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase("금액", font));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell4);

			// 장바구니 리스트(회원id:kim1)
			List<CartDTO> items = dao.list_cart("kim1");
			for (int i = 0; i < items.size(); i++) {
				CartDTO dto = items.get(i);
				PdfPCell cellProductName = new PdfPCell(new Phrase(dto.getProduct_name(), font));
				table.addCell(cellProductName);

				// 숫자 불가 → ""를 붙여서 문자열로 변환
				PdfPCell cellPrice = new PdfPCell(new Phrase("" + dto.getPrice(), font));
				table.addCell(cellPrice);
				PdfPCell cellAmount = new PdfPCell(new Phrase("" + dto.getAmount(), font));
				table.addCell(cellAmount);
				PdfPCell cellMoney = new PdfPCell(new Phrase("" + dto.getMoney(), font));
				table.addCell(cellMoney);
			}
			document.add(table);
			document.close();
			message = "pdf파일이 생성되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			message = "pdf파일 생성 실패...";
		}
		return message;
	}
}
