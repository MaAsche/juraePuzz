package de.htwg.se.juraePuzz.aview.Gui
import de.htwg.se.juraePuzz.controller.ControllerInterface
import de.htwg.se.juraePuzz.controller.controllerBaseImpl.Controller

import scala.swing._
import scala.swing.event.{Event, KeyPressed, MouseClicked}


class CellChanged extends Event

class PiecePanel (row: Int, column: Int, controller: ControllerInterface) extends FlowPanel{

  val givenCellColor = new Color(200, 200, 255)
  val cellColor = new Color(224, 224, 255)
  val highlightedCellColor = new Color(192, 255, 192)
  var clicked = false

  def pieceText = {
    controller.gridMatrix.get(row, column).s
  }

  val label =
    new Label {
      text = pieceText.toString
      font = new Font("OLDENGL", 1, 36)
    }

  val piece = new BoxPanel(Orientation.Vertical) {
    contents +=  label
    preferredSize = new Dimension(51, 51)
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    reactions += {
      case e: MouseClicked => {
        label.text match{
          case "S" =>
          case "E" =>
          case "0" =>
          case _ => clicked = true
        }
        controller.toggleShow()

      }
    }
  }


  val candidatelist = (1 to 9).map {
    value =>
      new Label {
        text = {
          value match {
            case 2 => "u"
            case 4 => "l"
            case 6 => "r"
            case 8 => "d"
            case _ => " "
          }
        }
        preferredSize = new Dimension(17, 17)
        font = new Font("Verdana", 1, 9)
        //background = cellColor
        border = Swing.BeveledBorder(Swing.Raised)
        listenTo(mouse.clicks)
        //listenTo(controller)
        reactions += {
          case e: MouseClicked => { // (src, pt, mod, clicks, pops) => {
            //controller.move(row, column, value)
            //text = if (controller.available(row, column).contains(value)) value.toString else " "
            //controller.move()
            clicked = false
            value match {
              case 2 => controller.move(row, column, row - 1, column)
              case 4 => controller.move(row, column, row, column - 1)
              case 6 => controller.move(row, column, row, column + 1)
              case 8 => controller.move(row, column, row + 1, column)
              case _ =>
            }
            controller.toggleShow()
          }
        }
      }
  }
  val candidates = new GridPanel(3, 3) {
    setBackground(this)
    contents ++= candidatelist
  }
  contents += piece

  def redraw: Unit = {
    contents.clear()
    if (clicked) {
      contents += candidates
    }else{
      label.text = pieceText.toString
      contents += piece
    }
    repaint
  }

  def setBackground(p: Panel) = p.background = givenCellColor
}
