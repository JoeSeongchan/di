package web.controller.member;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.controller.Controller;
import web.model.service.MemberService;
import web.model.vo.MemberVO;
import web.util.JsonUtil;
import web.util.MyException;

public class MemberRegistController implements Controller {

	private MemberService memberService;

	public MemberRegistController(Object memberService) {
		this.memberService = (MemberService) memberService;
	}

	public void process(ServletContext application, HttpServletRequest request,
		HttpServletResponse response,
		JsonObject json, Gson gson, JsonObject retJson)
		throws ServletException, IOException, MyException {
		String id = JsonUtil.getProperty(gson, json, "id", "사용자 ID", String.class);
		String pw = JsonUtil.getProperty(gson, json, "pw", "사용자 PW", String.class);
		String name = JsonUtil.getProperty(gson, json, "name", "사용자 이름", String.class);
		String pinNo = JsonUtil.getProperty(gson, json, "pinNo", "사용자 PIN 넘버", String.class);
		MemberVO m = new MemberVO(id, pw, name, pinNo);

		memberService.registMember(m);
		retJson.addProperty("data", "가입 ok"); // string의 값일때는 addProperty
	}

}
