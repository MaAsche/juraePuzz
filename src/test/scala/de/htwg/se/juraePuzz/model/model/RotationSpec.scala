package de.htwg.se.juraePuzz.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RotationSpec extends WordSpec with Matchers {
  "A Rotation" should {
    "have a value" in {
      Rotation(0).r should be(0)
    }
  }
}
