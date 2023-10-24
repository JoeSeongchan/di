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
import web.model.vo.GugunVO;
import web.util.JsonUtil;
import web.util.MyException;

// 관광지 정보 요청에 응답한다
public class GugunController implements Controller {

	private RegionService regionService;

	public GugunController(Object regionService) {
		this.regionService = (RegionService) regionService;
	}

	@Override
	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		int sidoCode = JsonUtil.getProperty(gson, json, "sidoCode", "시도 코드", Integer.class);
		List<GugunVO> gugunCode = regionService.getGunguList(sidoCode);
		retJson.add("data", gson.toJsonTree(gugunCode));
	}
}

