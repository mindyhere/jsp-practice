package chart;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import shop.CartDAO;
import shop.CartDTO;

public class ChartService {
	// JFreeChart : Swing, JSP, Servlet 등 에서 많이 사용되는 오픈소스 라이브러리
	public JFreeChart createChart() {
		CartDAO cartDao = new CartDAO();
		List<CartDTO> list = cartDao.cart_money();
		DefaultPieDataset dataset = new DefaultPieDataset();

		for (CartDTO dto : list) {
			dataset.setValue(dto.getProduct_name(), dto.getMoney());
		}
		JFreeChart chart = null;
		String title = "장바구니 통계";

		try {
			// 차트 생성
			chart = ChartFactory.createPieChart(title, dataset, true, true, false);
			
			// 글꼴 설정 :  getTitle → 차트제목, getLegend → 범례	
			chart.getTitle().setFont(new Font("돋움", Font.BOLD, 15));
			chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10));
			Font font = new Font("돋움", Font.PLAIN, 12);
			Color color = new Color(0, 0, 0); //black
			
			StandardChartTheme chartTheme = (StandardChartTheme) StandardChartTheme.createJFreeTheme();
			chartTheme.setExtraLargeFont(font);
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
			chartTheme.setSmallFont(font);
			chartTheme.setAxisLabelPaint(color);
			chartTheme.setLegendItemPaint(color);
			chartTheme.setItemLabelPaint(color);
			chartTheme.apply(chart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}

	public void makeImage(List<CartDTO> list, HttpServletResponse response) throws ServletException, IOException {
		// makeImage() : 웹페이지에 차트이미지 생성 ▶ png(writeChartAsPNG) ▶ createChart()함수호출 : 도표생성		
		ChartUtilities.writeChartAsPNG(response.getOutputStream(), createChart(), 900, 550);
	}

	public String makePdf() {
		String message = "";
		try {
			JFreeChart chart = createChart();
			Document document = new Document(); // pdf문서

			try {
				PdfWriter.getInstance(document, new FileOutputStream("c:/work/test.pdf"));
				document.open();
				// 차트이미지
				Image png = Image.getInstance(ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500)));
				document.add(png); // pdf이미지 추가
				document.close(); // pdf 저장완료
				message = "pdf파일이 생성되었습니다.";
			} catch (Exception e) {
				e.printStackTrace();
				message = "pdf파일 저장 실패";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	// 장바구니(cart) 데이터를 읽어온느 함수
	public JSONObject getChartData() {
		CartDAO cartDao = new CartDAO();
		List<CartDTO> items = cartDao.cart_money(); // 장바구니 리스트(선택상품+금액+합계) → items
		
		JSONObject data = new JSONObject();
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		JSONArray title = new JSONArray();

		//		  key     value
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");
		title.add(col1);
		title.add(col2);
		data.put("cols", title);
		
		JSONArray body = new JSONArray(); // 전체 장바구니 데이터
		for (CartDTO dto : items) {
			JSONObject name = new JSONObject();
			name.put("v", dto.getProduct_name());
			
			JSONObject money = new JSONObject();
			money.put("v", dto.getMoney());
			// "v" → value
			// (ex) {"v": "바나나"},  {"v": 12000} 형태로 저장
			
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			// "c" → "col":[{key:value}, {key:value}, ... ]
			body.add(cell);
		}
		data.put("rows", body);
		/* {"rows" : [{"c":[{"v":"바나나"}, {"v":120000}]},
		  			  {"c":[{"v": "딸기"},  {"v":27000}]},
		            "cols":[{"label":"상품명", "type":"string"}, 
		                    {"label":"금액", "type":"number"}], ... } */
		return data;
	}
}
