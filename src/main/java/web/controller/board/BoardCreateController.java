package web.controller.board;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.controller.Controller;
import web.model.service.BoardService;
import web.model.vo.BoardVO;
import web.util.JsonUtil;
import web.util.MyException;

public class BoardCreateController implements Controller {

	private BoardService boardService;

	public BoardCreateController(Object boardService) {
		this.boardService = (BoardService) boardService;
	}

	@Override
	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		String title = JsonUtil.getProperty(gson, json, "title", "게시글 제목", String.class);
		String content = JsonUtil.getProperty(gson, json, "content", "게시글 내용", String.class);
		BoardVO boardVO = new BoardVO(title, content);
		boardService.create(boardVO);
	}
}
