package web.controller.region;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.controller.Controller;
import web.model.service.RegionService;
import web.model.vo.SidoVO;
import web.util.MyException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SidoController implements Controller {

    private RegionService regionService;

    public SidoController(Object regionService) {
        this.regionService = (RegionService) regionService;
    }


    @Override
    public void process(ServletContext application, HttpServletRequest request,
                        HttpServletResponse response, JsonObject json, Gson gson, JsonObject retJson)
            throws ServletException, IOException, MyException {
        List<SidoVO> sidoList = regionService.getSidoList();
        retJson.add("data", gson.toJsonTree(sidoList));
    }
}
