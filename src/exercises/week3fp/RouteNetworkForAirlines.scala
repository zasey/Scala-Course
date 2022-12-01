package exercises.week3fp

import scala.annotation.tailrec

object RouteNetworkForAirlines extends App {


  def add(network: Map[String, Set[String]], location: String): Map[String, Set[String]] = {
    network + (location -> Set())
  }

  def remove(network: Map[String, Set[String]], location: String): Map[String, Set[String]] = {
    @tailrec
    def loop(destinations: Set[String], acc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (destinations.isEmpty) acc
      else loop(destinations.tail, disconnect(acc, location, destinations.head))
    val disconnected = loop(network(location), network)
    disconnected - location
  }

  def connect(network: Map[String, Set[String]], pointA: String, pointB: String): Map[String, Set[String]] = {
    val routesForA: Set[String] = network(pointA)
    val routesForB: Set[String] = network(pointB)
    network + (pointA -> (routesForA + pointB)) + (pointB -> (routesForB + pointA))
  }

  def disconnect(network: Map[String, Set[String]], pointA: String, pointB: String): Map[String, Set[String]] = {
    val routesForA: Set[String] = network(pointA)
    val routesForB: Set[String] = network(pointB)
    network + (pointA -> (routesForA - pointB)) + (pointB -> (routesForB - pointA))
  }

  def nLocationsWithNoFlights(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }

  def nFlights(network: Map[String, Set[String]], location: String): Int = {
    network(location).size
  }

  def mostFlights(network: Map[String, Set[String]]): String = {
    network.maxBy(loc => nFlights(network, loc._1))._1
  }

  def isConnected(network: Map[String, Set[String]], pointA: String, pointB: String): Boolean = {

  }
}
