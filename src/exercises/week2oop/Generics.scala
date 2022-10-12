package exercises.week2oop

object Generics extends App {
  // дополните код
  trait Event {
    def id: String
    def evType: String
    def operation: String
  }

  case class UserEvent(id: String, evType: String, operation: String) extends Event
  case class SystemEvent(id: String, evType: String, operation: String) extends Event

  trait Registrable[T  <: Event] {
    def update(event: T, status: String) : T
  }

  object EventOps {
    val userEvent = new Registrable[UserEvent] {
      def update(event: UserEvent, status: String): UserEvent = event.copy(operation = status)
    }

    val systemEvent = new Registrable[SystemEvent] {
      def update(event: SystemEvent, status: String): SystemEvent = event.copy(operation = status)
    }
  }

  class EventOps[E <: Event](event: E)(ops: Registrable[E]) {
    def update(status: String) = ops.update(event, status)
  }

  val user1Event = UserEvent("1", "user_event", "account_create")
  val user1Ops = new EventOps(user1Event)(EventOps.userEvent)
  val user1EventUpd = user1Ops.update("permission_add")

  println(s"User1 | Operation: ${user1EventUpd.operation}")
}
