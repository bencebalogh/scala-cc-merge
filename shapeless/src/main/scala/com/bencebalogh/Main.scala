package com.bencebalogh

import cats._
import cats.implicits._
import shapeless._

trait SemigroupKMerge {
  object genericMerge extends Poly2 {
    implicit def cases[F[_], V](implicit F: SemigroupK[F]): Case.Aux[F[V], F[V], F[V]] =
      at[F[V], F[V]](F.combineK)
  }
}

case class Capacity(seatedTickets: Option[Int], standingTickets: Option[Int] = None)

object Capacity extends SemigroupKMerge {
  lazy val g = Generic[Capacity]
  implicit val semigroupInfo: Semigroup[Capacity] = new Semigroup[Capacity] {
    override def combine(x: Capacity, y: Capacity): Capacity =
      g.from(g.to(x).zipWith(g.to(y))(genericMerge))
  }
}

object Main extends App() {
  val sectorA = Capacity(Some(200), None)
  val sectorB = Capacity(Some(300), Some(15))
  val arenaCapacity = sectorA |+| sectorB

  println(arenaCapacity)
}