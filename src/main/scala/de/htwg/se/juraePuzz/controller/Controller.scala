package de.htwg.se.juraePuzz.controller

import de.htwg.se.juraePuzz.model.Grid
import de.htwg.se.juraePuzz.model.Level

class Controller(var grid: Grid) {

  def create_empty_grid(size:Int): Unit ={
    grid = Grid(size)
    grid.init()
  }

  def crate_Level(i:Int): Unit ={
    val level1 = Level("S00K0E000")
    i match {
      case 1 => grid.fill(level1)
    }
  }
}
