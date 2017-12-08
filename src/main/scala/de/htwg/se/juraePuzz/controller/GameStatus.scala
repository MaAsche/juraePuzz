package de.htwg.se.juraePuzz.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, SOLVED, NOT_SOLVED_YET, ILLEGAL_TURN, CREATE_LEVEL, NOT_CREATED_LEVEL = Value
  val map = Map[GameStatus, String] (
    IDLE -> "",
    SOLVED -> "Puzzle solved",
    NOT_SOLVED_YET -> "Next turn",
    ILLEGAL_TURN -> "Illegal turn",
    CREATE_LEVEL -> "generated Level",
    NOT_CREATED_LEVEL -> "unable to generate Level")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }
}