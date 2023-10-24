package web.controller.member;

import web.model.service.OAuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/kakaoLogin")
public class KakaoLoginServlet extends HttpServlet {

    OAuthService oAuthService;

    public void init() throws ServletException {
        oAuthService = new OAuthService();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String access_Token = oAuthService.getKaKaoAccessToken(code);

        String email = oAuthService.createKakaoUser(access_Token);
        if (email != null) {
            // db체크
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            Cookie c = new Cookie("id", email);
            response.addCookie(c);
            response.sendRedirect(getServletContext().getContextPath());
        }
    }


}
