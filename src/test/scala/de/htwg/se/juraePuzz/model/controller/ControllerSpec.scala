package de.htwg.se.juraePuzz.model.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.juraePuzz.model.Grid
import de.htwg.se.juraePuzz.controller.{Controller, GameStatus}
import de.htwg.se.juraePuzz.util.{Observable, Observer}

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A Controller" should {
    var grid = new Grid(2)
    var controller = new Controller(grid)

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
      controller.create_empty_grid(3)
      controller.create_Level(1)
      observer.updated should be (true)
      controller.grid.toString() should be ("S00\nK0E\n0G0\n")
    }
    "should have status not created" in {
      controller.create_empty_grid(4)
      controller.create_Level(1)
      controller.gameStatus should be (GameStatus.NOT_CREATED_LEVEL)
    }
    "should have status solved" in {
      controller.create_empty_grid(3)
      controller.create_Level(1)
      controller.move(2, 1, 1, 1)
      controller.gameStatus should be (GameStatus.SOLVED)
    }
    "should have status illegal turn" in {
      controller.create_empty_grid(3)
      controller.create_Level(1)
      controller.move(2, 1, 1, 2)
      controller.gameStatus should be (GameStatus.ILLEGAL_TURN)
    }
    "should have status not solved yet" in {
      controller.create_empty_grid(3)
      controller.create_Level(1)
      controller.move(2, 1, 2, 2)
      controller.gameStatus should be (GameStatus.NOT_SOLVED_YET)
    }
    "gamestatus should have a message" in {
      val gs = GameStatus.IDLE
      GameStatus.message(gs) should be("")
    }
    "a status text" in {
      controller.gameStatus = GameStatus.SOLVED
      controller.statusText should be("Puzzle solved")
    }
  }
}