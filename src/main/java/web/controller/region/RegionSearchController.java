package web.controller.region;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.controller.Controller;
import web.model.service.RegionService;
import web.model.vo.RegionSearchConditionVO;
import web.model.vo.RegionVO;
import web.util.JsonUtil;
import web.util.MyException;

public class RegionSearchController implements Controller {

	private RegionService regionService;

	public RegionSearchController(Object regionService) {
		this.regionService = (RegionService) regionService;
	}

	@Override
	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		int sidoCode = JsonUtil.getProperty(gson, json, "sidoCode", "시도 코드", Integer.class);
		int gugunCode = JsonUtil.getProperty(gson, json, "gugunCode", "구군 코드", Integer.class);
		int contentTypeId = JsonUtil.getProperty(gson, json, "contentTypeId", "카테고리",
			Integer.class);

		RegionSearchConditionVO condition = new RegionSearchConditionVO(sidoCode, gugunCode,
			contentTypeId);
		List<RegionVO> regionList = regionService.search(condition);
		retJson.add("data", gson.toJsonTree(regionList));
	}
}
