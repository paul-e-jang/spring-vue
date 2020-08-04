package bashpound.marketplace.domain.model.user;

import javax.persistence.*;

import bashpound.marketplace.domain.common.model.AbstractBaseEntity;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class Users extends AbstractBaseEntity {

  private static final long serialVersionUID = -538781580460070724L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "username", nullable = false, length = 50, unique = true)
  private String username;

  @Column(name = "email_address", nullable = false, length = 100, unique = true)
  private String emailAddress;

  @Column(name = "password", nullable = false, length = 60)
  private String password;

  @Column(name = "first_name", nullable = true, length = 45)
  private String firstName;

  @Column(name = "last_name", nullable = true, length = 45)
  private String lastName;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  public Users() {
  }


  public static Users create(String username, String emailAddress, String password) {
    Users users = new Users();
    users.username = username;
    users.emailAddress = emailAddress;
    users.password = password;
    users.firstName = "";
    users.lastName = "";
    users.createdDate = new Date();
    return users;
  }

  public UserId getId() {
    return new UserId(id);
  }

  public String getUsername() {
    return username;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Users)) return false;
    Users users = (Users) o;
    return Objects.equals(username, users.username) &&
      Objects.equals(emailAddress, users.emailAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, emailAddress);
  }

  @Override
  public String toString() {
    return "Users{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", emailAddress='" + emailAddress + '\'' +
      ", password=<Protected> " +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", createdDate=" + createdDate +
      '}';
  }
}
