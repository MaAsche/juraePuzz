package de.htwg.se.juraePuzz.model.model

import de.htwg.se.juraePuzz.model.Solver
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner
import de.htwg.se.juraePuzz.model.{Grid,Level}

@RunWith(classOf[JUnitRunner])
class SolverSpec extends WordSpec with Matchers {

  "A Solver" should {
    val grid = new Grid(2)
    val solver = new Solver(grid,Level("0000"))
    "generate out of a matrix a Level" in {
      solver.solve().s should be ("0000")
    }
    "checks that the current matirx ist solved or not" in {
      solver.check_level() should be (true)
      val solver1 = new Solver(grid,Level("000000000"))
      solver1.check_level() should be (false)
    }
  }
}

