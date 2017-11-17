package de.htwg.se.juraePuzz.model

class Solver(g:Grid,l:Level) {
  def solve(): Level = {
    val sb = new StringBuilder()
    for (i <- 0 until g.matrix.getSize(); j <- 0 until g.matrix.getSize()) {
      sb.append(g.matrix.get(i, j).s)
    }
    Level(sb.toString())
  }
  def check_level(): Boolean ={
    if(l.length() == solve().length()){
      l.s.equals(solve().s)
    }else{
      false
    }
  }
}
