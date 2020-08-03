package bashpound.marketplace.domain.model.activity;

import java.util.HashMap;
import java.util.Map;

import bashpound.marketplace.utils.JsonUtils;

class ActivityDetail {

  private Map<String, Object> detail = new HashMap<>();

  private ActivityDetail() {
  }

  static ActivityDetail blank() {
    return new ActivityDetail();
  }

  public ActivityDetail add(String key, Object value) {
    detail.put(key, value);
    return this;
  }

  String toJson() {
    return JsonUtils.toJson(detail);
  }
}
