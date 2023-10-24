package web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import web.model.vo.GugunVO;
import web.model.vo.RegionSearchConditionVO;
import web.model.vo.RegionVO;
import web.model.vo.SidoVO;
import web.util.DBUtil;
import web.util.MyException;

public class RegionDAOImpl implements RegionDAO {

	public List<RegionVO> search(RegionSearchConditionVO condition) throws MyException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from attraction_info where sido_code=? and gugun_code=? and content_type_id=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, condition.getSidoCode());
			stmt.setInt(2, condition.getGugunCode());
			stmt.setInt(3, condition.getContentTypeId());
			rs = stmt.executeQuery();
			List<RegionVO> regionVOList = new ArrayList<>();
			while (rs.next()) {
				String title = rs.getString("title");
				String addr1 = rs.getString("addr1");
				String addr2 = rs.getString("addr2");
				String zipCode = rs.getString("zipcode");
				String firstImage = rs.getString("first_image");
				double latitude = rs.getDouble("latitude");
				double longitude = rs.getDouble("longitude");
				RegionVO regionVO = new RegionVO(title, addr1, addr2, zipCode, firstImage, latitude,
					longitude);
				regionVOList.add(regionVO);
			}
			return regionVOList;
		} catch (SQLException e) {
			throw new MyException("지역 정보 검색 중 오류 발생");
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
	}

	public List<SidoVO> getSidoList() throws MyException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from sido;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<SidoVO> sidoVOList = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("sido_name");
				int code = rs.getInt("sido_code");
				SidoVO sidoVO = new SidoVO(code, name);
				sidoVOList.add(sidoVO);
			}
			return sidoVOList;
		} catch (SQLException e) {
			throw new MyException("시도 코드 검색 중 오류 발생");
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
	}

	public List<GugunVO> getGunguList(int sidoCode) throws MyException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from gugun where sido_code=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, sidoCode);
			rs = stmt.executeQuery();
			List<GugunVO> gugunVOList = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("gugun_name");
				int code = rs.getInt("gugun_code");
				GugunVO gugunVO = new GugunVO(code, name, sidoCode);
				gugunVOList.add(gugunVO);
			}
			return gugunVOList;
		} catch (SQLException e) {
			throw new MyException("군구 코드 검색 중 오류 발생");
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
	}
}
