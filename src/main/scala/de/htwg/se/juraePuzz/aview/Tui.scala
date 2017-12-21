package de.htwg.se.juraePuzz.aview

import de.htwg.se.juraePuzz.controller.{Controller, GameStatus}
import de.htwg.se.juraePuzz.util.Observer
import de.htwg.se.juraePuzz.controller.GameStatus._

class Tui (controller: Controller) extends Observer{

  controller.add(this)
  val size = 3

  def process_input_line(input: String):Unit = {
    input match {
      case "n" => controller.create_empty_grid(size)
      case "q" =>
      case "z" => controller.undo
      case "y" => controller.redo
      case "c" => controller.create_Level(2)
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
        case xS :: yS :: xT :: yT :: Nil => controller.move(xS, yS, xT, yT)
        case _ =>
      }
    }
  }

  override def update: Unit = {
    println(controller.grid)
    println(controller.statusText)
  }
}
