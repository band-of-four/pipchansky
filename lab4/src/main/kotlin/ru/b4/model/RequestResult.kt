package ru.b4.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "request_result")
data class RequestResult(var x: Double = 0.0,
                         var y: Double = 0.0,
                         var r: Double = 0.0,
                         var isHit: Boolean = false) {

  @Id
  @GeneratedValue
  var id: Long? = null

}