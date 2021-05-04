package com.bencebalogh

import scala.deriving.Mirror

trait Semigroup[T]:
  extension (x: T) def combine (y: T): T

object Semigroup:
   def apply[T](using s: Semigroup[T]) = s

given Semigroup[String] with
  extension (x: String) def combine (y: String) = x + y
given Semigroup[Int] with
  extension (x: Int) def combine (y: Int) = x + y
given Semigroup[Boolean] with
  extension (x: Boolean) def combine (y: Boolean) = x && y
given Semigroup[EmptyTuple] with
  extension (x: EmptyTuple) def combine (y: EmptyTuple) = EmptyTuple
given [H: Semigroup, T <: Tuple: Semigroup]: Semigroup[H *: T] with
  extension (x: H *: T) def combine(y: H *: T) =
    x.head.combine(y.head) *: x.tail.combine(y.tail)

case class Employee(name: String, number: Int, manager: Boolean)

given Semigroup[Employee] with
  extension (x: Employee) def combine(y: Employee): Employee = {
    val tuplesX = Tuple.fromProductTyped(x)
    val tuplesY = Tuple.fromProductTyped(y)
    val merged = tuplesX.combine(tuplesY)
    summon[Mirror.Of[Employee]].fromProduct(merged)
  }

@main def main = {
  val e1 = Employee("1", 2, false)
  val e2 = Employee("2", 3, true)

  println(e1.combine(e2))
}