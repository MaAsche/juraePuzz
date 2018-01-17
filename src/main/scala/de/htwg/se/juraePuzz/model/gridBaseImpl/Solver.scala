package de.htwg.se.juraePuzz.model.gridBaseImpl

import java.util
import java.util.PriorityQueue

import de.htwg.se.juraePuzz.model.GridInterface

import scala.util.Sorting

class Solver(g:GridInterface) {
  def solve(): Level = {
    var sb = Array.ofDim[Int](g.getSize() * g.getSize())
    for (i <- 0 until g.getMatrix().size; j <- 0 until g.getMatrix().size) {
      sb(j + i * g.getSize()) = (g.getMatrix().get(i, j).s)
    }

    Sorting.quickSort(sb)
    for (i <- 0 until sb.length-2){
      sb(i)=sb(i+2)
    }
    sb(sb.length-1)=0
    sb(sb.length-2)=0
    Level(sb)
  }
  def check_level(): Boolean ={
    val l = g.getLevel()
    l.s.corresponds(solve().s){_ == _}
  }

  def solve_with_algo(): Boolean = {
    val list = new util.LinkedList[GridInterface]()

    list.add(g)

    for (i <- 0 until g.getSize(); j <- 0 until g.getSize()) {
      if (g.getMatrix().get(i,j).s == 0){

        val g1 = new Grid(3)
        g1.fill(g.getLevel())
        g1.move(i + 1, j, i, j)

        val g2 = new Grid(3)
        g2.fill(g.getLevel())
        g2.move(i - 1, j, i, j)

        val g3 = new Grid(3)
        g3.fill(g.getLevel())
        g3.move(i, j + 1, i, j)

        val g4 = new Grid(3)
        g4.fill(g.getLevel())
        g4.move(i, j - 1, i, j)

        if (!list.contains(g1)){
          list.add(g1)
        }
        if (!list.contains(g2)){
          list.add(g2)
        }
        if (!list.contains(g3)){
          list.add(g3)
        }
        if (!list.contains(g4)){
          list.add(g4)
        }

        println(list)
        return true
      }
    }
    false
  }
}



