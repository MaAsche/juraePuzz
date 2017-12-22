package de.htwg.se.juraePuzz.controller

import de.htwg.se.juraePuzz.aview.Gui.CellChanged
import de.htwg.se.juraePuzz.model.{GetSpecifiedLevel, Grid, Level, Solver}
import de.htwg.se.juraePuzz.util.{Observable, UndoManager}
import de.htwg.se.juraePuzz.controller.GameStatus._

import scala.swing.Publisher
import scala.swing.event.Event

class Controller(var grid: Grid) extends Observable with Publisher{

  var gameStatus: GameStatus = IDLE
  val undoManager = new UndoManager

  def create_empty_grid(size:Int): Unit ={
    grid = new Grid(size)
    notifyObservers
    toggleShow
  }

  def toggleShow() = publish(new CellChanged)

  def statusText: String = GameStatus.message(gameStatus)

  def create_Level(): Unit ={
    var st1 = new GetSpecifiedLevel()
    if (grid.fill(st1.createLevel(this))) {
      gameStatus = CREATE_LEVEL
    } else {
      gameStatus = NOT_CREATED_LEVEL
    }
    notifyObservers
    toggleShow()
  }

  def move(xS:Int, yS:Int, xT:Int, yT:Int) = {
    if (undoManager.doStep(new SetCommand(xS, yS, xT, yT, this))) {
      if (new Solver(grid).check_level()) {
        gameStatus = SOLVED
      } else {
        gameStatus = NOT_SOLVED_YET
      }
    } else {
      gameStatus = ILLEGAL_TURN
    }
    notifyObservers
    toggleShow()

  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
    toggleShow
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
    toggleShow
  }

  def solve(): Unit ={
    grid.solve()
    gameStatus = SOLVED
    notifyObservers
    toggleShow()
  }
}
