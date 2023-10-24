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

public class BoardDetailController implements Controller {

	private BoardService boardService;

	public BoardDetailController(Object boardService) {
		this.boardService = (BoardService) boardService;
	}

	@Override
	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		int boardId = JsonUtil.getProperty(gson, json, "boardId", "게시글 ID", Integer.class);
		BoardVO boardVO = boardService.find(boardId);
		retJson.addProperty("board", gson.toJson(boardVO));
	}
}
