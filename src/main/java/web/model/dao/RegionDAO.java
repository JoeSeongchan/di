package web.model.dao;

import java.util.List;
import web.model.vo.GugunVO;
import web.model.vo.RegionSearchConditionVO;
import web.model.vo.RegionVO;
import web.model.vo.SidoVO;
import web.util.MyException;

public interface RegionDAO {

	List<RegionVO> search(RegionSearchConditionVO condition) throws MyException;

	List<SidoVO> getSidoList() throws MyException;

	List<GugunVO> getGunguList(int sidoCode) throws MyException;
}
