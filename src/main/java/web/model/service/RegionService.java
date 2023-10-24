package web.model.service;

import java.util.List;
import web.model.dao.RegionDAO;
import web.model.vo.GugunVO;
import web.model.vo.RegionSearchConditionVO;
import web.model.vo.RegionVO;
import web.model.vo.SidoVO;
import web.util.MyException;

public class RegionService {

	private RegionDAO regionDAOImpl;

	public RegionService(Object regionDAOImpl) {
		this.regionDAOImpl = (RegionDAO) regionDAOImpl;
	}

	public List<SidoVO> getSidoList() throws MyException {
		return regionDAOImpl.getSidoList();
	}

	public List<GugunVO> getGunguList(int sidoCode) throws MyException {
		return regionDAOImpl.getGunguList(sidoCode);
	}

	public List<RegionVO> search(RegionSearchConditionVO condition) throws MyException {
		return regionDAOImpl.search(condition);
	}
}
