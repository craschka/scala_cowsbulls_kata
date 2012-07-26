package org.craschka.scalatest

import util.Random
import collection.immutable.IndexedSeq

object BullCow {
  def main(args: Array[String]): Unit = {

    val game = new Game
    game.start()
  }
}

class Game {
  val number = randomNumber()

  def start() {

    print("Guess a 4-digit number with no duplicate digits: ")

    val guesses = evaluate(0, 0)
    println("You won after " + guesses + " guesses!")
    println("number was " + number)
  }


  def evaluate(_bulls: Int, guesses: Int): Int = {
    if (_bulls == 4) {
      return guesses
    }
    val input = Console.readInt()
    val cows = calcCows(number, input)
    val bulls = calcBulls(number, input)
    println("%d Cows and %d Bulls.".format(cows, bulls))

    evaluate(bulls, guesses + 1)
  }

  def calcBulls(input: Int, number: Int): Int = {
    input.toString.map(a => if (number.toString.charAt(input.toString.indexOf(a)) == a) 1 else 0).foldRight(0)((x, accu) => x + accu)
  }

  def calcCows(input: Int, number: Int): Int = {
    input.toString.map(a => if (number.toString.contains(a)) 1 else 0).foldRight(0)((x, accu) => x + accu)
  }

  def randomNumber(): Int = {
    var list = random;
    if (list.toString.distinct.length != list.toString.length) list = randomNumber()
    list
  }

  def random: Int = {
    Random.nextInt(9000) + 1000
  }
}
