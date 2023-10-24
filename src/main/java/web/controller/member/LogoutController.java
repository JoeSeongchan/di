package web.controller.member;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.controller.Controller;
import web.util.MyException;

public class LogoutController implements Controller {

	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response,
		JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
	}

}
