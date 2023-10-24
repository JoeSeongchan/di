package web.model.dao;

import web.model.vo.BoardVO;
import web.util.DBUtil;
import web.util.MyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardDAO {

    public void create(BoardVO boardVO) throws MyException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into board (title,content) values(?,?);";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, boardVO.getTitle());
            stmt.setString(2, boardVO.getContent());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException("게시글 추가 중 오류 발생");
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    public List<BoardVO> list() throws MyException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from board;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<BoardVO> boardVOList = new ArrayList<>();
            while (rs.next()) {
                int boardId = rs.getInt("boardId");
                String title = rs.getString("title");
                String body = rs.getString("content");
                BoardVO boardVO = new BoardVO(boardId, title, body);
                boardVOList.add(boardVO);
            }
            return boardVOList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("모든 게시글 가져오는 중 오류 발생");
        } finally {
            DBUtil.close(conn, stmt, rs);
        }

    }


    public Optional<BoardVO> find(int boardId) throws MyException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from board where boardId=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, boardId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                BoardVO boardVO = new BoardVO(boardId, title, content);
                return Optional.of(boardVO);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new MyException("게시글 찾는 중 오류 발생");
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    public List<BoardVO> search(String boardTitle) throws MyException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from board where title like concat('%', ?, '%');";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, boardTitle);
            rs = stmt.executeQuery();
            List<BoardVO> boardVOList = new ArrayList<>();
            while (rs.next()) {
                int boardId = rs.getInt("boardId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                BoardVO boardVO = new BoardVO(boardId, title, content);
                boardVOList.add(boardVO);
            }
            return boardVOList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("제목으로 게시글 검색 중 오류 발생");
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    public void delete(String boardId) throws MyException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from board where boardId=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, boardId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException("게시글 삭제 중 오류 발생");
        } finally {
            DBUtil.close(conn, stmt);
        }

    }

    public void edit(int boardId, BoardVO boardVO) throws MyException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "update board set title=?, content=? where boardId=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, boardVO.getTitle());
            stmt.setString(2, boardVO.getContent());
            stmt.setInt(3, boardId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException("게시글 수정 중 오류 발생");
        } finally {
            DBUtil.close(conn, stmt);
        }

    }

}
