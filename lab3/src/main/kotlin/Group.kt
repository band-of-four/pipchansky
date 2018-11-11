import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "groups")
class Group() : Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long? = null

  var username: String? = null
  var groupname: String? = null

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  var user: User? = null

  constructor(groupname: String, user: User) : this() {
    this.username = user.username
    this.groupname = groupname
    this.user = user
  }
}
