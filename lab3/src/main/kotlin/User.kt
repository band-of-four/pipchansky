import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user")
class User : Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  var username: String? = null
  var password: String? = null

  @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
  var history: List<RequestResult>? = null

}
