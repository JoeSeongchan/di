package web.model.service;

import java.util.List;
import web.model.dao.BoardDAO;
import web.model.vo.BoardVO;
import web.util.MyException;

public class BoardService {

	private BoardDAO boardDAO;

	public BoardService(Object boardDAO) {
		this.boardDAO=(BoardDAO) boardDAO;
	}

	public List<BoardVO> list() throws MyException {
		return boardDAO.list();
	}


	public BoardVO find(int boardId) throws MyException {
		return boardDAO.find(boardId).orElseThrow(() -> new MyException("유효하지 않은 게시글입니다."));
	}

	public List<BoardVO> search(String boardTitle) throws MyException {
		if (boardTitle == null) {
			throw new MyException("유효하지 않은 검색조건입니다.");
		}
		return boardDAO.search(boardTitle);
	}

	public void delete(String boardId) throws MyException {
		if (boardId == null) {
			throw new MyException("유효하지 않은 게시글 접근입니다.");
		}
		boardDAO.delete(boardId);
	}

	public void edit(int boardId, BoardVO boardVO) throws MyException {
		boardDAO.edit(boardId, boardVO);
	}

	public void create(BoardVO boardVO) throws MyException {
		boardDAO.create(boardVO);
	}
}
