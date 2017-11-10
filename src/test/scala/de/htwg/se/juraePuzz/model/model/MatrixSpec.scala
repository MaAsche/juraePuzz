package de.htwg.se.juraePuzz.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MatrixSpec extends WordSpec with Matchers {
  "A matrix" should {
    def m = Matrix(5)
    m.fill(Piece("S", Rotation(0)), 0, 0)
    "have a size" in {
      m.size should be (5)
    }
    "have a piece at 0,0" in {
      m.get(0,0) should be(Piece("S", Rotation(0)))
    }
  }
}
