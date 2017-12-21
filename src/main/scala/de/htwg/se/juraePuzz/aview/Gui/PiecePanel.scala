package de.htwg.se.juraePuzz.aview.Gui
import scala.swing._
import scala.swing.event.{KeyPressed, MouseClicked}
import de.htwg.se.juraePuzz.controller.{Controller}

class PiecePanel (row: Int, column: Int, controller: Controller) extends FlowPanel{

  val givenCellColor = new Color(200, 200, 255)
  val cellColor = new Color(224, 224, 255)
  val highlightedCellColor = new Color(192, 255, 192)

  def pieceText = {
    controller.grid.matrix.get(row, column).s
  }

  val label =
    new Label {
      text = pieceText
      font = new Font("OLDENGL", 1, 36)
    }
  val piece = new BoxPanel(Orientation.Vertical) {
    contents +=  label
    preferredSize = new Dimension(51, 51)
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(keys)
    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) => {
        label.text match {
          case "S" => background = new Color(255, 0, 0)
          case "E" => background = new Color(255, 0, 0)
          case _ =>
        }
      }
    }
  }


  val candidatelist = (1 to controller.grid.getSize()).map {
    (value =>
      new Label {
        text = "F"
        preferredSize = new Dimension(17, 17)
        font = new Font("Verdana", 1, 9)
        //background = cellColor
        border = Swing.BeveledBorder(Swing.Raised)
        listenTo(mouse.clicks)
        //listenTo(controller)
        reactions += {
          case MouseClicked(src, pt, mod, clicks, pops) => {
            //controller.move(row, column, value)
            //text = if (controller.available(row, column).contains(value)) value.toString else " "
            repaint
          }
        }
      })
  }
  val candidates = new GridPanel(controller.grid.getSize(), controller.grid.getSize()) {
    setBackground(this)
    contents ++= candidatelist
  }
  contents += candidates

  contents += piece
  def redraw = {
    contents.clear()
    label.text = pieceText
    contents += piece
    repaint
  }

  def setBackground(p: Panel) = p.background = givenCellColor
}
