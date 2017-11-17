package de.htwg.se.juraePuzz.model.model

import de.htwg.se.juraePuzz.model.Level
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class LevelSpec extends WordSpec with Matchers {
  "A Level" should {
    "have a value" in {
      Level("0").s should be("0")
      Level("0").length() should be (1)
    }
  }
}