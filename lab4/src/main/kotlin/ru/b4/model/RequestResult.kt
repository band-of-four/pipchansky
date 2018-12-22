package ru.b4.model

import javax.persistence.*

@Entity
@Table(name = "request_result")
data class RequestResult(var x: Double = 0.0,
                         var y: Double = 0.0,
                         var isHit: Boolean = false) {

  @Id
  @GeneratedValue
  var id: Long? = null

  @ManyToOne(fetch = FetchType.EAGER)
  var user: User? = null

}
