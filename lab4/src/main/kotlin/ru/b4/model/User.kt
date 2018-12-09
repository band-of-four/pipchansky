package ru.b4.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "account")
data class User(@Column(nullable = false, unique = true) var username: String = "",
                var password: String = "") {

  @Id
  @GeneratedValue
  var id: Long? = null

}
