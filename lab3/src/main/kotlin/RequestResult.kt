import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "result")
class RequestResult() : Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long? = null

  var x: Double = 0.0
  var y: Double = 0.0
  var r: Double = 0.0
  var isHit: Boolean = false

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  var user: User? = null

  constructor(x: Double, y: Double, r: Double, isHit: Boolean) : this()

}
