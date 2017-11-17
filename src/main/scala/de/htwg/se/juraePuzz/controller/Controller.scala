package de.htwg.se.juraePuzz.controller

import de.htwg.se.juraePuzz.model.{Grid, Solver}
import de.htwg.se.juraePuzz.model.Level
import de.htwg.se.juraePuzz.util.Observable

class Controller(var grid: Grid) extends Observable {
  grid.init()
  def create_empty_grid(size:Int): Unit ={
    grid = new Grid(size)
    grid.init()
    notifyObservers
  }

  def crate_Level(i:Int): Unit ={
    val level1 = Level("S00K0E000")
    val level2 = Level("S0000GE00")
    val level3 = Level("GE000K00S")
    i match {
      case 1 => grid.fill(level1)
      case 2 => grid.fill(level2)
      case 3 => grid.fill(level3)
    }
    notifyObservers
  }
  def move(xS:Int, yS:Int, xT:Int, yT:Int) = {
    grid.move(xS, yS, xT, yT)
    notifyObservers
    if (new Solver(grid, Level("S00G00E00")).check_level()) {
      println("Level solved!")
    }
  }

  def render_grid() = {
    grid.render()
  }


}
