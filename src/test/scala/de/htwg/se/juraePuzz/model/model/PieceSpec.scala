package de.htwg.se.juraePuzz.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PieceSpec extends WordSpec with Matchers {
  "A piece" should{
    "have a name" in {
      Piece("S", Rotation(0)).s should be("S")
    }
    "have a Rotation" in {
      Piece("S", Rotation(0)).r should be(Rotation(0))
    }
  }
}
