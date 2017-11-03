package de.htwg.se.juraePuzz.model


import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PointSpec extends WordSpec with Matchers {
  "A new Point" should{
    val p = new Point(2,3)
    "have coordinates" in {
      p.x should be(2)
      p.y should be(3)
    }
  }
}
