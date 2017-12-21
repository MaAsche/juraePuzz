package de.htwg.se.juraePuzz.aview.Gui


import de.htwg.se.juraePuzz.controller.Controller

import scala.swing._
import scala.swing.event.{Event, MouseClicked}

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

  listenTo(controller)

  def buttonPanel = new FlowPanel{
    contents += new Button("New") {
      listenTo(mouse.clicks)
      reactions += {
        case e: MouseClicked =>{
          controller.create_Level(2)
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
    contents += new Button("Undo step") {
      listenTo(mouse.clicks)
      reactions += {
        case e : MouseClicked => redraw
      }
    }
    contents += new Button("Redo step")
    contents += new GridPanel(controller.grid.getSize(), controller.grid.getSize())
  }

  val statusline = new TextField(controller.statusText, 20)

  contents = new BorderPanel {
    add(buttonPanel, BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }
   visible = true
  redraw

  reactions += {
    case event: CellChanged => redraw
  }

  def redraw = {
    for {
      row <- 0 until controller.grid.getSize()
      col <- 0 until controller.grid.getSize()
    } cells(row)(col).redraw
    resizable = false
    resizable = true
    statusline.text = controller.statusText
    repaint
  }
}


