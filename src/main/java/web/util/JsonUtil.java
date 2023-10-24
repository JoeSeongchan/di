package web.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonUtil {

	public static <T> T getProperty(Gson gson, JsonObject json, String propertyId,
		String propertyName, Class<T> klass)
		throws MyException {
		try {
			T property = gson.fromJson(json.get(propertyId), klass);
			return property;
		} catch (NullPointerException | JsonSyntaxException e) {
			Logger logger = LogManager.getLogger(JsonUtil.class);
			logger.error("유효하지 않은 " + propertyName + "입니다.");
			throw new MyException("유효하지 않은 " + propertyName + "입니다.");
		}
	}
}
