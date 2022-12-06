package exercises

import scala.annotation.tailrec

abstract class LogList[+A] {
  def last : A
  def previous : LogList[A]
  def isEmpty : Boolean
  def all : String
  def add[B >: A](msg: B): LogList[B]
}

class Log[+A](h: A, t: LogList[A]) extends LogList[A]{

  def add[B >: A](msg: B): LogList[B] = {
    new Log(msg, this)
  }

  def previous: LogList[A] = this.t

  def isEmpty: Boolean = false

  def last : A = this.h

  def all: String = {
    @tailrec
    def loop(log: LogList[A] = this, acc: String = this.h.toString): String = {
      if (log.previous.isEmpty) acc
      else

        loop(log.previous, s"$acc ${log.previous.last}")

    }

    loop()
  }
}

object Empty extends LogList[Nothing] {

  def add[B >: Nothing](msg: B): LogList[B] ={
    new Log(msg, Empty)
  }

  def previous: LogList[Nothing] = {
    throw new NoSuchElementException()
  }

  def isEmpty : Boolean = true

  def last: Nothing = {
    throw new NoSuchElementException()
  }

  def all:String  = ""

}