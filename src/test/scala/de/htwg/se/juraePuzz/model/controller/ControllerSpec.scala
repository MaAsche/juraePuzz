package de.htwg.se.juraePuzz.model.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.juraePuzz.model.{Grid}
import de.htwg.se.juraePuzz.controller.Controller
import de.htwg.se.juraePuzz.util.{Observable, Observer}

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A Controller" should {
    val grid = new Grid(2)
    val controller = new Controller(grid)

    val observer = new Observer {
      var updated: Boolean = false

      def isUpdated: Boolean = updated

      override def update: Unit = updated = true
    }
    controller.add(observer)
    "notify its Observer after create grid" in {
      controller.create_empty_grid(2)
      observer.updated should be (true)
      controller.grid.getSize() should be (2)
    }
    "notify its Observer after created a Level" in {
      controller.create_Level(1)
      observer.updated should be (true)
      controller.grid.getSize() should be (2)
    }
  }
}