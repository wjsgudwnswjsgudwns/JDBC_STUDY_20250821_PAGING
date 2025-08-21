package board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/boardlist")
public class BoardController extends HttpServlet {
	BoardDao boardDao = new BoardDao();
	static final int pageGroupSize = 10; 
	// 게시판 하단에 표시될 현재 글의 갯수로 만들어진 전체 페이지 수
	// [1] [2] [3] [4] [5] [6] [7] [8] [9] [10]
	
    public BoardController() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	int page = 1; 
    	// 게시판의 페이지 번호 없이 게시판 링크로 접근한 경우 무조건 1페이지의 내용이 보여야 함
    	// 처음에 보여질 페이지 번호를 1로 초기화
    	int totalBoardCount = boardDao.countBoard(); // 총 글의 갯수
    	
    	if(request.getParameter("page") == null) { // 링크 타고 게시판 들어온 경우
    		page = 1;
    	} else { // 유저가 보고 싶은 페이지 번호를 클릭한 경우
    		page = Integer.parseInt(request.getParameter("page")); // 유저가 클릭한 페이지 번호
    	}
    	
    	List<BoardDto> boardDtos = boardDao.boardList(page);
    	int totalPage = (int) Math.ceil((double)totalBoardCount/BoardDao.PAGE_SIZE); //총 글의 갯수로 표현 될 페이지의 수
    	int startPage = (((page-1)/BoardDao.PAGE_SIZE)*BoardDao.PAGE_SIZE) + 1;
    	int endPage = startPage + BoardDao.PAGE_SIZE -1;
    	
    	if(endPage > totalPage ) { // 계산한 endPage(20)가 totalPage(16)보다 크면 totalPage로 대체  
    		endPage = totalPage;
    	}
    	
    	request.setAttribute("boardDtos", boardDtos); // 유저가 선택한 페이지에 해당하는 글
    	request.setAttribute("currentPage", page); // 유저가 현재 선택한 페이지
    	request.setAttribute("totalPage", totalPage); // 총 글의 갯수로 표현 될 페이지의 수
    	request.setAttribute("startPage", startPage); // 시작 페이지
    	request.setAttribute("endPage", endPage); // 마지막 페이지
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
