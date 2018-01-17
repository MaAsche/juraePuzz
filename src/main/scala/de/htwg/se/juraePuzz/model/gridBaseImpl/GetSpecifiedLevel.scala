package de.htwg.se.juraePuzz.model.gridBaseImpl

import java.util

import de.htwg.se.juraePuzz.controller.controllerBaseImpl.Controller

class GetSpecifiedLevel extends LevelGenerateStrategyTemplate {
  override def createLevel(controller: Controller): Level = {
    val size = controller.grid.getSize()*controller.grid.getSize()
    var l = Array.ofDim[Int](size)
    for (i <- 0 until (size) - 2) {
      l(i)= i + 1
    }
    shuffle(l)
    Level(l)
  }
  private def shuffle(array: Array[Int]) = {
    for (i <- 0 until array.length) {
      val r = i + (Math.random() * (array.length-i)).toInt
      val tmp = array(i)
      array(i) = array(r)
      array(r) = tmp
    }
  }
}