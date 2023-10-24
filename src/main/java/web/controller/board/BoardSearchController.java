package web.controller.board;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.controller.Controller;
import web.model.service.BoardService;
import web.model.vo.BoardVO;
import web.util.JsonUtil;
import web.util.MyException;

public class BoardSearchController implements Controller {

	private BoardService boardService;

	public BoardSearchController(Object boardService) {
		this.boardService = (BoardService) boardService;
	}

	@Override
	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		String boardTitle = JsonUtil.getProperty(gson, json, "boardTitle", "게시글 제목",
			String.class);
		List<BoardVO> boardVOList = boardService.search(boardTitle);
		retJson.add("data", gson.toJsonTree(boardVOList));
	}
}
