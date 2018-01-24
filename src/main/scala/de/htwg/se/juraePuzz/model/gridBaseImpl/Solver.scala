package de.htwg.se.juraePuzz.model.gridBaseImpl

import java.util
import java.util.PriorityQueue

import de.htwg.se.juraePuzz.model.GridInterface

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.util.Sorting

class Solver(g:GridInterface) {
  def solve(): Level = {
    var sb = Array.ofDim[Int](g.getSize() * g.getSize())
    for (i <- 0 until g.getMatrix().size; j <- 0 until g.getMatrix().size) {
      sb(j + i * g.getSize()) = (g.getMatrix().get(i, j).s)
    }

    Sorting.quickSort(sb)
    for (i <- 0 until sb.length - 2) {
      sb(i) = sb(i + 2)
    }
    sb(sb.length - 1) = 0
    sb(sb.length - 2) = 0
    Level(sb)
  }

  def check_level(): Boolean = {
    val l = g.getLevel()
    l.s.corresponds(solve().s) {
      _ == _
    }
  }


  val list = new ArrayBuffer[GridInterface]()

  val visited = new ArrayBuffer[GridInterface]()

  def solve_with_algo(): Level = {

    import java.io.IOException
    // Clear the queue and add the initial state.
    var copy_g = g.copy()
    list.clear
    list += copy_g

    while (!list.isEmpty) {
      // Get the best next state.
    //  println(list)
      var index = 0
      for (i<-0 until list.size){
        var min = 1000
        if (list(i).manhatten() < min) {
          min = list(i).manhatten()
          index = i
        }
      }

      copy_g = list.remove(index)
      //println("visited " + copy_g)
      // Check if the state is a solution.
      if (copy_g.isGoal()) {
        //println("Goal: " + copy_g.getLevel())
        return copy_g.getLevel()
      }

      val g1 = copy_g.copy()
      val g2 = copy_g.copy()
      val g3 = copy_g.copy()
      val g4 = copy_g.copy()

      visited += copy_g
      println("visited " + visited.contains(copy_g))

      var x_pos=0
      var y_pos=0
      for (i <- 0 until copy_g.getSize(); j <- 0 until copy_g.getSize()) {
        if (copy_g.getMatrix().get(i,j).s==0) {
          x_pos=i
          y_pos=j
        }

        if (g1.move(x_pos + 1, y_pos, x_pos, y_pos) && !visited.contains(g1)){
            list += g1
        }
          if (g2.move(x_pos - 1, y_pos, x_pos, y_pos)&& !visited.contains(g2)){
              list += g2
          }
          if (g3.move(x_pos, y_pos + 1, x_pos, y_pos)&& !visited.contains(g3)){
              list += g3
          }
          if (g4.move(x_pos, y_pos - 1, x_pos, y_pos)&& !visited.contains(g4)){
              list += g4
          }

      }
      //println("Liste " + list)

    }
    //println("noooop")
    return new Level(Array.fill(g.getSize()*g.getSize())(0))
  }

}



