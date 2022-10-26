package exercises

class Logger(msgNum: Int = 0) {

  def info: Logger = {
    println("INFO New Message")
    new Logger(msgNum + 1)
  }

  def info(msgNum: Int): Logger = {
    if(msgNum <= 0) this
    else info.info(msgNum - 1)
  }

  def print = println(msgNum)
}

object LogSystem extends App {

  val logger = new Logger
  logger.info.print
  logger.info(3).print
  logger.info.info.info.print
}
