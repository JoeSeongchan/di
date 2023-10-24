package web.controller.member;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.controller.Controller;
import web.util.MyException;

public class KakaoLoginController implements Controller {

	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response,
		JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		String url = "https://kauth.kakao.com/oauth/authorize?client_id=e574c9b060f356f2d5070ac44167e37e&redirect_uri=http://localhost:8080/enjoytrip/kakaoLogin&response_type=code";
		//((HttpServletResponse) response).sendRedirect(url);
		retJson.addProperty("url", url);
	}

}
