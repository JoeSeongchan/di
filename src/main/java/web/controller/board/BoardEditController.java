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

public class BoardEditController implements Controller {

	private BoardService boardService;

	public BoardEditController(Object boardService) {
		this.boardService = (BoardService) boardService;
	}

	// TODO: 없는 board id을 가진 게시글을 수정하려고 할 때 오류 메시지를 반환해야 함
	@Override
	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		int boardId = JsonUtil.getProperty(gson, json, "boardId", "게시글 ID", Integer.class);
		String newTitle = JsonUtil.getProperty(gson, json, "newTitle", "새로운 제목", String.class);
		String newContent = JsonUtil.getProperty(gson, json, "newContent", "새로운 내용",
			String.class);

		BoardVO boardVO = new BoardVO(boardId, newTitle, newContent);
		boardService.edit(boardId, boardVO);
	}
}
