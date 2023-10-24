package web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import web.util.MyException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet(urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {

    Map<String, Object> objectMap;
    ServletContext application;

    public void init() throws ServletException {
        try {
            application = getServletContext();
            objectMap = new XmlBeanFactory(application.getRealPath("/WEB-INF/beans.xml")).objectMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        JsonObject json = (JsonObject) JsonParser.parseReader(request.getReader());
        String sign = json.get("sign").getAsString();

        Gson gson = new Gson();
        JsonObject retJson = new JsonObject();


        try {
            if (sign != null) {
                // 더 나쁜 코드를 만들지 않기 위해서 .xml을 만들어야한다.
                // 하지만 메모리상에 만들어지는 것은 많아진다. trade off가 있다.
                ((Controller) objectMap.get(sign)).process(application, request, response, json,
                        gson, retJson);
            } else {
                retJson.addProperty("error", true);
                retJson.addProperty("msg", "잘못된 요청입니다.");
            }
        } catch (MyException e) {
            retJson.addProperty("error", true);
            retJson.addProperty("msg", "잘못된 요청입니다.");
        }
        out.append(retJson.toString());
        out.close();
    }//end process

}
