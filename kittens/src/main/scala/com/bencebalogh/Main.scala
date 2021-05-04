package com.bencebalogh

import cats._
import cats.implicits._
import cats.derived

case class Capacity(seatedTickets: Int, standingTickets: Int = 0)

object Capacity {
  implicit val showFoo: Semigroup[Capacity] = derived.semiauto.semigroup
}

object Main extends App() {
  val sectorA = Capacity(200, 10)
  val sectorB = Capacity(300, 15)
  val arenaCapacity = sectorA |+| sectorB

  println(arenaCapacity)
}
