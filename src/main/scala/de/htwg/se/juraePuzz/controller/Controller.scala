package de.htwg.se.juraePuzz.controller

import de.htwg.se.juraePuzz.model.{Grid, Solver}
import de.htwg.se.juraePuzz.model.Level
import de.htwg.se.juraePuzz.util.{Observable, UndoManager}
import de.htwg.se.juraePuzz.controller.GameStatus._

class Controller(var grid: Grid) extends Observable {

  var gameStatus: GameStatus = IDLE
  val undoManager = new UndoManager

  def create_empty_grid(size:Int): Unit ={
    grid = new Grid(size)
    notifyObservers
  }

  def create_Level(i:Int): Unit ={
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
    if (undoManager.doStep(new SetCommand(xS, yS, xT, yT, this))) {

      if (new Solver(grid, Level("S00G00E00")).check_level()) {
        gameStatus = SOLVED
      } else {
        gameStatus = NOT_SOLVED_YET
      }
    } else {
      gameStatus = ILLEGAL_TURN
    }
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }
}
