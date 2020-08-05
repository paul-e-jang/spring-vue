package bashpound.marketplace.domain.model.board;

import bashpound.marketplace.domain.common.model.AbstractBaseEntity;
import bashpound.marketplace.domain.model.user.UserId;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "board")
public class Board extends AbstractBaseEntity {

  private static final long serialVersionUID = -7789177855101967490L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 128, unique = true)
  private String name;

  @Column(name = "description", nullable = false, length = 256, unique = true)
  private String description;

  @Column(name = "user_id")
  private long userId;

  @Column(name = "archived")
  private boolean archived;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  /**
   * Create new board
   */
  public static Board create(UserId userId, String name, String description) {
    Board board = new Board();
    board.userId = userId.value();
    board.name = name;
    board.description = description;
    board.archived = false;
    board.createdDate = new Date();
    return board;
  }

  public BoardId getId() {
    return new BoardId(id);
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public UserId getUserId() {
    return new UserId(userId);
  }


  public boolean isArchived() {
    return archived;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Board)) return false;
    Board board = (Board) o;
    return userId == board.userId &&
      Objects.equals(name, board.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, userId);
  }

  @Override
  public String toString() {
    return "Board{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", userId=" + userId +
      ", archived=" + archived +
      ", createdDate=" + createdDate +
      '}';
  }
}
