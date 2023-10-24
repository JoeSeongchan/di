package web.model.dao;

import web.model.vo.MemberSecVO;
import web.util.DBUtil;
import web.util.MyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberSecDAOImpl implements MemberSecDAO {

    @Override
    public String readSalt(String id) throws MyException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            String sql = "select * from memberSec where id=?;";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("salt");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("회원 등록 실패");
        } finally {
            DBUtil.close(stmt, con);
        }
    }

    @Override
    public void write(MemberSecVO memberSecVO) throws MyException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBUtil.getConnection();
            String sql = "insert into memberSec (id, salt, secKey) values(?,?,?);";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, memberSecVO.getId());
            stmt.setString(2, memberSecVO.getSalt());
            stmt.setString(3, memberSecVO.getSecKey());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("회원가입 실패");
        } finally {
            DBUtil.close(stmt, con);
        }
    }
}
