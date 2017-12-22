package de.htwg.se.juraePuzz.model

import scala.util.Sorting

class Solver(g:Grid) {
  def solve(): Level = {
    var sb = Array.ofDim[Int](g.getSize() * g.getSize())
    for (i <- 0 until g.matrix.size; j <- 0 until g.matrix.size) {
      sb(j + i * g.getSize()) = (g.matrix.get(i, j).s)
    }

    Sorting.quickSort(sb)
    for (i <- 0 until sb.length-1){
      sb(i)=sb(i+1)
    }
    sb(sb.length-1)=0
    Level(sb)
  }
  def check_level(): Boolean ={
    val l = g.getLevel()
    if(l.length() == solve().length()){
      l.s.corresponds(solve().s){_ == _}
    }else{
      false
    }
  }
}
