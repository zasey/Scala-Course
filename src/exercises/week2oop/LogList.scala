//package exercises.week2oop
//
//import scala.annotation.tailrec
//
//abstract class LogList[+A] {
//  def last : String
//  def previous : LogList[A]
//  def isEmpty : Boolean
//  def all : String
//  def add(msg: String): LogList[A]
//}
//
//class Log[+A](h: A, t: LogList[A]) extends LogList[A]{
//
//  def add(msg: A): LogList[A] = {
//    new Log[A](msg, this)
//  }
//
//  def previous: LogList[A] = this.tail
//
//  def isEmpty: Boolean = false
//
//  def last : String = this.head
//
//  def all : String = {
//    @tailrec
//    def loop(log: LogList[A] = this, acc: String = this.head): String = {
//      if(log.previous.isEmpty) acc
//      else
//
//        loop(log.previous, s"$acc ${log.previous.last}")
//
//    }
//    loop()
//  }
//}
//
//object Empty extends LogList {
//
//  def add(msg: String): LogList = {
//    new Log(msg, Empty)
//  }
//
//  def previous: LogList = {
//    throw new NoSuchElementException()
//  }
//
//  def isEmpty : Boolean = true
//
//  def last: String = {
//    throw new NoSuchElementException()
//  }
//
//  def all: String = ""
//
//}