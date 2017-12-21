package de.htwg.se.juraePuzz.controller

import de.htwg.se.juraePuzz.model.{GetSpecifiedLevel, Grid, Level, Solver}
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
    var st1 = new GetSpecifiedLevel()
    if (grid.fill(st1.createLevel(i))) {
      gameStatus = CREATE_LEVEL
    } else {
      gameStatus = NOT_CREATED_LEVEL
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
