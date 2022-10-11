package cs.dit.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class BListService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	
		// TODO Auto-generated method stub
		int p = 1; //현재 페이지 번호 초기화
		int numOfRecords = 10; //한 페이지에서 출력되는 게시글의 수
		int numOfPages = 5;
		String page = request.getParameter("p"); //전달되는 현재 페이지 번호
		if(page != null && !page.equals(""))
			p = Integer.parseInt(page);
		
		BoardDao dao = new BoardDao();
		ArrayList<BoardDto> dtos = dao.list(p, numOfRecords);
		
		int count = dao.recordCount();
		
		int startNum = p - (p-1)%numOfPages; //한 화면의 첫 페이지 번호
		
		int lastNum = (int)(Math.ceil((float)count/(float)numOfRecords)); //전체의 마지막 페이지 번호
		
		
	
		
		//5. 이 페이지의 저장소인 pageContext에 DB에서 검색해온 dtos 값을 저장하시오.
		request.setAttribute("dtos", dtos);
		request.setAttribute("p", p);
		request.setAttribute("startNum", startNum);
		request.setAttribute("lastNum", lastNum);
		request.setAttribute("numOfPages", numOfPages);
	}

}
