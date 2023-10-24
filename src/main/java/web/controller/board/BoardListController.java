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
import web.util.MyException;

public class BoardListController implements Controller {

	private BoardService boardService;

	public BoardListController(Object boardService) {
		this.boardService = (BoardService) boardService;
	}

	@Override
	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		List<BoardVO> boardVOList = boardService.list();
		retJson.add("data", gson.toJsonTree(boardVOList));
	}
}
