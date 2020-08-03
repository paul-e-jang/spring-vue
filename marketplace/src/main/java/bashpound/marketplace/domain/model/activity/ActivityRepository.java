package bashpound.marketplace.domain.model.activity;

import java.util.List;

import bashpound.marketplace.domain.model.card.CardId;

public interface ActivityRepository {

  /**
   * Save activity
   *
   * @param activity the activity to save
   */
  void save(Activity activity);

  /**
   * Get the activities related to a card
   *
   * @param cardId the id of the card
   * @return a list of card activities
   */
  List<Activity> findCardActivities(CardId cardId);
}
