package exercises

import scala.annotation.tailrec

abstract class LogList {
  def last : String
  def previous : LogList
  def isEmpty : Boolean
  def all : String
  def add(msg: String): LogList
}

class Log(head: String, tail: LogList) extends LogList {

  def add(msg: String): LogList = {
    new Log(msg, this)
  }

  def previous: LogList = this.tail

  def isEmpty: Boolean = false

  def last : String = this.head

  def all : String = {
    @tailrec
    def loop(log: LogList = this, acc: String = this.head): String = {
      if(log.previous.isEmpty) acc
      else loop(log.previous, s"$acc ${log.previous.last}")
    }
    loop()
  }
}

object Empty extends LogList {

  def add(msg: String): LogList = {
    new Log(msg, Empty)
  }

  def previous: LogList = {
    throw new NoSuchElementException()
  }

  def isEmpty : Boolean = true

  def last: String = {
    throw new NoSuchElementException()
  }

  def all: String = ""

}

object LinkedList extends App {
  val list = new Log("m1", new Log("m2", new Log("m3", Empty)))
  println(list.all)
}
