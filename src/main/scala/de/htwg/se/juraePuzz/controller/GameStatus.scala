package de.htwg.se.juraePuzz.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, SOLVED, NOT_SOLVED_YET, ILLEGAL_TURN = Value
  val map = Map[GameStatus, String] (
    IDLE -> "",
    SOLVED -> "Puzzle solved",
    NOT_SOLVED_YET -> "Next turn",
    ILLEGAL_TURN -> "Illegal turn")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }
}