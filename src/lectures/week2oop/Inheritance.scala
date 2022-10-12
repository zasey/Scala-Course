package lectures.week2oop

object Inheritance {
  sealed abstract class DayOfTheWeek(val name: String, val isWeekend: Boolean)

  case object Monday extends DayOfTheWeek("Monday", false)
  case object Tuesday extends DayOfTheWeek("Tuesday",  false)
  case object Wednesday extends DayOfTheWeek("Wednesday", false)
  case object Thursday extends DayOfTheWeek("Thursday", false)
  case object Friday extends DayOfTheWeek("Friday", false)
  case object Saturday extends DayOfTheWeek("Saturday", true)
  case object Sunday extends DayOfTheWeek("Sunday", true)

}
