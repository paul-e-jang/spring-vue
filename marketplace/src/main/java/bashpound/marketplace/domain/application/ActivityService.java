package bashpound.marketplace.domain.application;

import bashpound.marketplace.domain.model.activity.Activity;

public interface ActivityService {

  /**
   * Save an activity
   *
   * @param activity the activity instance
   */
  void saveActivity(Activity activity);
}
