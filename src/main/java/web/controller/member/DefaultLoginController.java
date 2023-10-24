package web.controller.member;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.controller.Controller;
import web.model.service.MemberService;
import web.model.vo.MemberVO;
import web.util.JsonUtil;
import web.util.MyException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DefaultLoginController implements Controller {

    private MemberService memberService;
    private Logger logger;

    public DefaultLoginController(Object memberService) {
        this.memberService = (MemberService) memberService;
        logger = LogManager.getLogger(DefaultLoginController.class);
    }


    @Override
    public void process(ServletContext application, HttpServletRequest request,
                        HttpServletResponse response, JsonObject json,
                        Gson gson, JsonObject retJson) throws ServletException, IOException, MyException {
        String id = JsonUtil.getProperty(gson, json, "id", "id", String.class);
        String pw = JsonUtil.getProperty(gson, json, "pw", "pw", String.class);
        if (id == null || pw == null) {
            logger.error("아이디, 비밀번호가 유효하지 않습니다.");
            throw new MyException("아이디, 비밀번호가 유효하지 않습니다.");
        }
        MemberVO m = new MemberVO(id, pw);
        if (memberService.login(m)) {
            retJson.addProperty("data", "로그인 ok");
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            Cookie c = new Cookie("id", id);
            response.addCookie(c);
        } else {
            retJson.addProperty("data", "로그인 실패");
        }
    }
}
