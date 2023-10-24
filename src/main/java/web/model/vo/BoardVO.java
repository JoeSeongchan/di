package web.model.vo;

import web.util.DataUtil;
import web.util.MyException;

import java.io.Serializable;

public class BoardVO implements Serializable {

    private static final long serialVersionUID = 1L;

    int boardId;
    String title, content;

    public BoardVO() {
        super();
    }


    public BoardVO(String title, String content) throws MyException {
        setTitle(title);
        setContent(content);
    }

    public BoardVO(int boardId, String title, String content) throws MyException {
        setBoardId(boardId);
        setTitle(title);
        setContent(content);
    }


    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) throws MyException {
        if (boardId < 0) {
            throw new MyException("유효하지 않은 게시글 ID입니다.");
        }
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws MyException {
        if (title == null || title.length() == 0) {
            throw new MyException("유효하지 않은 게시글 제목입니다.");
        }
        if (title.length() > 100) {
            throw new MyException("유효한 제목이 아닙니다.");
        }
        this.title = DataUtil.escapeXSS(title);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws MyException {
        if (content.length() > 10000) {
            throw new MyException("유효한 게시글 내용이 아닙니다. 게시글 내용 글자수가 1만 이하여야 합니다.");
        }
        this.content = DataUtil.escapeXSS(content); // XSS 공격 대비
    }

}
