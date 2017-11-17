package de.htwg.se.juraePuzz.aview

import de.htwg.se.juraePuzz.controller.Controller
import de.htwg.se.juraePuzz.model.Grid
import de.htwg.se.juraePuzz.util.Observer
import de.htwg.se.juraePuzz.util.Observer

class Tui (controller: Controller) extends Observer{

  controller.add(this)
  val size = 3

  def process_input_line(input: String):Unit = {
    input match {
      case "n" => controller.create_empty_grid(size)
      case "q" =>
      case "c" => controller.crate_Level(2)
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
        case xS :: yS :: xT :: yT :: Nil => controller.move(xS, yS, xT, yT)
        case _ =>
      }
    }
  }

  override def update: Unit = controller.render_grid()

}
