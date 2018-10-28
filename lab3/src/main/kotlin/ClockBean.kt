import java.io.Serializable
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.zone.ZoneRulesException

class ClockBean : Serializable {

  var zoneName: String = "Europe/Moscow"

  fun getTime() : String {
    return try {
      getTimeWithZone(zoneName)
    } catch (e: ZoneRulesException) {
      "Your location is not supported, Moscow Time: " + getTimeWithZone("Europe/Moscow")
    }
  }

  private fun getTimeWithZone(zoneName: String) = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss")
      .format(ZonedDateTime.now(ZoneId.of(zoneName))).toString()

}
