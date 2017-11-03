package de.htwg.se.juraePuzz.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Sebastian")
    "have a name"  in {
      player.name should be("Sebastian")
    }
    "have a nice String representation" in {
      player.toString should be("Sebastian")
    }
  }}
}
