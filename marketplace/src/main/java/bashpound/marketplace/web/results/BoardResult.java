package bashpound.marketplace.web.results;

import org.springframework.http.ResponseEntity;

import bashpound.marketplace.domain.common.file.FileUrlCreator;
import bashpound.marketplace.domain.model.board.Board;
import bashpound.marketplace.domain.model.card.Card;
import bashpound.marketplace.domain.model.cardlist.CardList;
import bashpound.marketplace.domain.model.cardlist.CardListId;
import bashpound.marketplace.domain.model.team.Team;
import bashpound.marketplace.domain.model.user.Users;
import bashpound.marketplace.utils.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardResult {

  public static ResponseEntity<ApiResult> build(Team team, Board board, List<Users> members,
                                                List<CardList> cardLists, List<Card> cards,
                                                FileUrlCreator fileUrlCreator) {
    Map<String, Object> boardData = new HashMap<>();
    boardData.put("id", board.getId().value());
    boardData.put("name", board.getName());
    boardData.put("personal", board.isPersonal());

    List<MemberData> membersData = new ArrayList<>();
    for (Users user: members) {
      membersData.add(new MemberData(user));
    }

    List<CardListData> cardListsData = new ArrayList<>();
    Map<CardListId, List<Card>> cardsByList = new HashMap<>();
    for (Card card: cards) {
      cardsByList.computeIfAbsent(card.getCardListId(), k -> new ArrayList<>()).add(card);
    }

    for (CardList cardList: cardLists) {
      cardListsData.add(new CardListData(cardList, cardsByList.get(cardList.getId()), fileUrlCreator));
    }

    ApiResult result = ApiResult.blank()
      .add("board", boardData)
      .add("members", membersData)
      .add("cardLists", cardListsData);

    if (!board.isPersonal()) {
      Map<String, String> teamData = new HashMap<>();
      teamData.put("name", team.getName());
      result.add("team", teamData);
    }
    return Result.ok(result);
  }

  private static class MemberData {
    private long userId;
    private String shortName;
    private String name;

    MemberData(Users user) {
      this.userId = user.getId().value();
      this.shortName = user.getInitials();
      this.name = user.getFirstName() + " " + user.getLastName();
    }

    public long getUserId() {
      return userId;
    }

    public String getShortName() {
      return shortName;
    }

    public String getName() {
      return name;
    }
  }

  private static class CardListData {
    private long id;
    private String name;
    private int position;
    private List<CardData> cards = new ArrayList<>();

    CardListData(CardList cardList, List<Card> cards, FileUrlCreator fileUrlCreator) {
      this.id = cardList.getId().value();
      this.name = cardList.getName();
      this.position = cardList.getPosition();
      if (cards != null) {
        for (Card card: cards) {
          this.cards.add(new CardData(card, fileUrlCreator));
        }
      }
    }

    public long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public int getPosition() {
      return position;
    }

    public List<CardData> getCards() {
      return cards;
    }
  }

  private static class CardData {
    private long id;
    private String title;
    private int position;
    private String coverImage;

    CardData(Card card, FileUrlCreator fileUrlCreator) {
      this.id = card.getId().value();
      this.title = card.getTitle();
      this.position = card.getPosition();
      this.coverImage = card.hasCoverImage() ?
        ImageUtils.getThumbnailVersion(fileUrlCreator.url(card.getCoverImage())) : "";
    }

    public long getId() {
      return id;
    }

    public String getTitle() {
      return title;
    }

    public int getPosition() {
      return position;
    }

    public String getCoverImage() {
      return coverImage;
    }
  }

}
