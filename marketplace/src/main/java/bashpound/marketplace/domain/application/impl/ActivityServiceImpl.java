package bashpound.marketplace.domain.application.impl;

import org.springframework.stereotype.Service;

import bashpound.marketplace.domain.application.ActivityService;
import bashpound.marketplace.domain.model.activity.Activity;
import bashpound.marketplace.domain.model.activity.ActivityRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

  private ActivityRepository activityRepository;

  public ActivityServiceImpl(ActivityRepository activityRepository) {
    this.activityRepository = activityRepository;
  }

  @Override
  public void saveActivity(Activity activity) {
    activityRepository.save(activity);
  }
}
