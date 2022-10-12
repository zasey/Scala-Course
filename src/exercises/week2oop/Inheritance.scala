package exercises.week2oop

object Inheritance extends App{

  class Button(label: String) {
    def this() = this("test")
    def click(): String = s"button -$label- has been clicked"
  }

  class RoundedButton(label: String) extends Button(label) {
    override def click(): String = s"rounded ${super.click()}"
  }

  println(new RoundedButton("LABEL").click)

  abstract class Event {
    def trigger(eventName: String): Unit
  }

  class Listener(val eventName: String, var event: Event) {
    def register(evt: Event) { event = evt }
    def sendNotification() { event.trigger(eventName) }
  }

  val notification: Listener = new Listener("mousedown", null)

  notification.register(new Event{
    def trigger(eventName: String): Unit = println(s"trigger $eventName event")
  })

  notification.sendNotification

//------------------------------------------------------------------------

  trait Configs {
    val ACCOUNT_TYPE_DEFAULT = "free"
    val ACCOUNT_TYPE_PAID = "paid"
    val SUBSCRIPTION_STATUS = "active"
  }

  object Settings {
    case class AccountSettings(
                                email: String,
                                password: String,
                                picture: String)

    case class SubscriptionSettings(
                                     payment: String,
                                     notifications: String,
                                     expiration: String)
  }

  trait Subscriber {
    def subscribe(settings: Settings.SubscriptionSettings): Unit = println("subscribed")
  }

  trait Unsubscriber {
    def unsubscribe(): Unit = println("unsubscribed")
  }

  abstract class Account(accountID: String, settings: Settings.AccountSettings) {
    def info(): Unit
  }

  class FreeAccount (
                      accountID: String,
                      settings: Settings.AccountSettings)
    extends Account(accountID, settings) with Subscriber with Configs {

    override def info(): Unit = println(s"Account Type: $ACCOUNT_TYPE_DEFAULT")
  }

  class PaidAccount (
                      accountID: String,
                      settings: Settings.AccountSettings)
    extends Account(accountID, settings) with Unsubscriber with Configs {

    override def info(): Unit = {
      println(s"Account Type: $ACCOUNT_TYPE_PAID")
      println(s"Subscription Status: $SUBSCRIPTION_STATUS")
    }
  }

}
