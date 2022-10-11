package cs.dit.board;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class BInsertService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String filename = null;
		String dir = null;
		
		//7. insertForm 에서 입력한 subject, content, writer 를 받아와 알맞는 변수에 입력하세요.
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		// Content-type 파악하기
		String contentType = request.getContentType();
		System.out.println("contentType: " + contentType);
		
		if(contentType != null && contentType.startsWith("multipart/")) {
			dir = request.getServletContext().getRealPath("uploadFiles");
			System.out.println("업로드 파일경로: "+ dir);
		}
		//uploadfiles 폴더가 존재하는지 확인하고 없으면 폴더를 생성한다.
		File f = new File(dir);
		if(f.exists())
			f.mkdir();
		
		Collection<Part> parts = request.getParts();
		for(Part p : parts) {
			if(p.getHeader("Content-Disposition").contains("filename=")) {
				if(p.getSize()>0) { //Part 객체의 전송되는 파일이 존재
					filename = p.getSubmittedFileName();
					String filepath = dir + File.separator + filename;
					p.write(filepath);
					p.delete();
					System.out.println("filepath : " + filepath);
				}
			}
				
		}
			
		BoardDto dto = new BoardDto(0, subject, content, writer, null, filename); 
		BoardDao dao = new BoardDao();
		dao.insert(dto);
	}

}
