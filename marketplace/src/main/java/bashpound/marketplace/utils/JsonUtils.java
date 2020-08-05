package bashpound.marketplace.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import bashpound.marketplace.utils.JsonUtils;

import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonUtils {

	  private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

	  private JsonUtils() {
	  }

	  public static String toJson(Object object) {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    log.debug(object+"is to be parced to JSON");
	      return mapper.writeValueAsString(object);
	    } catch (JsonProcessingException e) {
	      log.error("Failed to convert object to JSON string", e);
	      return null;
	    }
	  }

	  public static <T> T toObject(String json, Class<T> clazz) {
	    try {
	      ObjectMapper objectMapper = new ObjectMapper();
	      log.debug(json+"is parced to Objects"); //런타임 환경에서 반드시 비활성화 (로그로 Password 노출)
	      return objectMapper.readValue(json, clazz);
	      
	    } catch (IOException e) {
	      log.error("Failed to convert string `" + json + "` class `" + clazz.getName() + "`", e);
	      return null;
	    }
	  }
	  

	  public static void write(Writer writer, Object value) throws IOException {
	    new ObjectMapper().writeValue(writer, value);
	  }

	}
