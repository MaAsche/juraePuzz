package de.htwg.se.juraePuzz.aview.Gui

import de.htwg.se.juraePuzz.controller.Controller

import scala.swing._
import scala.swing.event.MouseClicked

class SwingGui(controller: Controller) extends Frame{
  title = "juraePuzz"

  var cellPanel = new GridPanel(controller.grid.getSize(), controller.grid.getSize()) {
    for {
      row <- 0 until controller.grid.getSize()
      col <- 0 until controller.grid.getSize()
    } {
      contents += new BoxPanel(Orientation.Vertical) {
        contents += new Label{
          text = controller.grid.matrix.get(row, col).s
          font = new Font("OLDENGL", 1, 40)
        }
        preferredSize = new Dimension(51, 51)
        border = Swing.BeveledBorder(Swing.Raised)
        listenTo(mouse.clicks)
      }
    }
  }

  def buttonPanel = new FlowPanel{
    contents += new Button("New") {
      listenTo(mouse.clicks)
      reactions += {
        case e: MouseClicked =>
          controller.create_Level(1)
          cellPanel = new GridPanel(controller.grid.getSize(), controller.grid.getSize()) {
            for {
              row <- 0 until controller.grid.getSize()
              col <- 0 until controller.grid.getSize()
            } {
              contents += new BoxPanel(Orientation.Vertical) {
                contents += new Label{
                  text = controller.grid.matrix.get(row, col).s
                  font = new Font("OLDENGL", 1, 40)
                }
                preferredSize = new Dimension(51, 51)
                border = Swing.BeveledBorder(Swing.Raised)
                listenTo(mouse.clicks)
              }
            }
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
    add(cellPanel, BorderPanel.Position.Center)
  }

  visible = true
}
