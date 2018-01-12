package de.htwg.se.juraePuzz.model.gridBaseImpl

import de.htwg.se.juraePuzz.controller.controllerBaseImpl.Controller

class GetSpecifiedLevel extends LevelGenerateStrategyTemplate {
  override def createLevel(controller: Controller): Level = {
    val size = controller.grid.getSize()*controller.grid.getSize()
    var l = Array.ofDim[Int](size)
    for (i <- 0 until (size) - 1) {
      l(i)=((Math.random() * size).toInt + 1)
    }
    Level(l)
  }
}