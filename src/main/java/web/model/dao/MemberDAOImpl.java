package web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import web.model.vo.MemberVO;
import web.util.DBUtil;
import web.util.MyException;

public class MemberDAOImpl implements MemberDAO {

	// 회원정보 등록
	@Override
	public void createMember(MemberVO m) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into member(id,pw,name,pinNo) values(?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, m.getId());
			stmt.setString(2, m.getPw());
			stmt.setString(3, m.getName());
			stmt.setString(4, m.getPinNo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("회원 등록 실패");
		} finally {
			DBUtil.close(stmt, con);
		}

	}

	// 비밀번호 가져오기
	@Override
	public String readPw(String id) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from member where id=?;";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("pw");
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("로그인 실패");
		} finally {
			DBUtil.close(stmt, con, rs);
		}
	}


}
