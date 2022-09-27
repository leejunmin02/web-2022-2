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
		BoardDao dao = new BoardDao();
		ArrayList<BoardDto> dtos = dao.list();
		System.out.println(dtos.size());
		
		//5. 이 페이지의 저장소인 pageContext에 DB에서 검색해온 dtos 값을 저장하시오.
		request.setAttribute("dtos", dtos);
	}

}
