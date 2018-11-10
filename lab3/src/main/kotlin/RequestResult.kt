import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "result")
class RequestResult() : Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "result_id")
  var id: Long? = null

  var x: Double = 0.0
  var y: Double = 0.0
  var r: Double = 0.0
  var isHit: Boolean = false

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  var user: User? = null

  constructor(x: Double, y: Double, r: Double, isHit: Boolean) : this()

}
