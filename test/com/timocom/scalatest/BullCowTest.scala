package com.timocom.scalatest

import org.scalatest.{OneInstancePerTest, FlatSpec}
import org.scalatest.matchers.ShouldMatchers

class BullCowTest extends FlatSpec with ShouldMatchers with OneInstancePerTest {

  val game = new Game

  "RandomGenerator" should "generate 4 numbers" in {
    game.randomNumber().toString.length should be (4)
  }

  it should "not have same digits" in {
    val number = game.randomNumber()
    number.toString.distinct.length should be (number.toString.length)
  }

  it should "be distinct with every call" in {
    val number = game.randomNumber()
    val next = game.randomNumber()

    number should not be (next)
  }

  "cows" should "be the contains count of the input" in {
    game.calcCows(1234,4321) should be (4)
    game.calcCows(1234,8165) should be (1)
    game.calcCows(1234,4367) should be (2)
    game.calcCows(1234,2348) should be (3)
    game.calcCows(1234,5678) should be (0)
  }

  "bulls" should "be the exact number of hits" in {
    game.calcBulls(1234,1423) should be (1)
    game.calcBulls(1234,4321) should be (0)
    game.calcBulls(1234,1324) should be (2)
    game.calcBulls(1234,1238) should be (3)
    game.calcBulls(1234,1234) should be (4)
    game.calcBulls(8431,8888) should be (1)
  }
}
