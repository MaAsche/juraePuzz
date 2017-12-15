package de.htwg.se.juraePuzz.aview.Gui


import de.htwg.se.juraePuzz.controller.{Controller}
import scala.swing._
import scala.swing.event.MouseClicked

class SwingGui(controller: Controller) extends Frame{
  title = "juraePuzz"

  var cells = Array.ofDim[PiecePanel](controller.grid.getSize(), controller.grid.getSize())

  def gridPanel = new GridPanel(controller.grid.getSize(), controller.grid.getSize()) {

    for {
      row <- 0 until controller.grid.getSize()
      col <- 0 until controller.grid.getSize()
    } {
        val piecePanel = new PiecePanel(row, col, controller)
        cells(row)(col) = piecePanel
        contents += piecePanel
        listenTo(piecePanel)
      }
  }

  def buttonPanel = new FlowPanel{
    contents += new Button("New") {
      listenTo(mouse.clicks)
      reactions += {
        case e: MouseClicked =>{
          controller.create_Level(1)
          redraw
        }
      }
    }

    contents += new Button("Quit"){
      listenTo(mouse.clicks)
      reactions += {
        case e: MouseClicked => System.exit(0)
      }
    }
    contents += new Button("Undo step")
    contents += new Button("Redo step")
    contents += new GridPanel(controller.grid.getSize(), controller.grid.getSize())
  }
  contents = new BorderPanel {
    add(buttonPanel, BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
  }
   visible = true

  def redraw = {
    for {
      row <- 0 until controller.grid.getSize()
      col <- 0 until controller.grid.getSize()
    } cells(row)(col).redraw
    resizable = false
    resizable = true
    repaint
  }
}


