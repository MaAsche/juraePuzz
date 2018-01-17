package de.htwg.se.juraePuzz.model.gridBaseImpl

import java.util

import de.htwg.se.juraePuzz.model.GridInterface

import scala.util.Sorting

class Solver(g:GridInterface) {

  var solved = false

  val l = new util.LinkedList[Node]()

  l.add(new Node(g, 0, null))

  def tiwn(): GridInterface = {
    var newgrid = new Grid(g.getSize())

  }

  class Node(g: GridInterface, m: Int, p: Node) extends Comparable[Node]{
    val grid = g
    val moves = m
    val prev = p
    val dist = grid.manhatten()

    override def compareTo(other: Node): Int = {
      this.moves + this.dist - other.moves - other.dist
    }
  }

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

  private def root(node: Node): Node = {
    var current = node
    while (current.prev != null) {
      current = current.prev
    }
    current
  }

  def isSolveable(): Boolean = {
    solved
  }
}



