import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "users")
class User : Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "client_id")
  var id: Long? = null

  var username: String? = null
  var password: String? = null

  @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
  var history: List<RequestResult>? = null

  @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
  var groups: List<Group>? = null
}
