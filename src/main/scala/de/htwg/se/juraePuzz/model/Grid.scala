package de.htwg.se.juraePuzz.model

class Grid(size:Int) {
  val matrix = Matrix(size)


  def init(): Unit = {
    for (i <- 0 until size; j <- 0 until size){
      matrix.fill(Piece("0", Rotation(0)), i, j)
    }
  }

  def getSize():Int = {
    matrix.getSize()
  }

  def render(): Unit = {
    for (i <- 0 until matrix.getSize(); j <- 0 until matrix.getSize()){
      printf("%s", matrix.get(i,j).s)
      if (j == matrix.getSize() - 1) {
        println("")
      }
    }
  }

  def fill(p:Piece, row:Int, col:Int): Unit = {
    matrix.fill(p, row, col)
  }

  def fill(l:Level): Unit = {
    for (i <- 0 until matrix.getSize(); j <- 0 until matrix.getSize()){
      matrix.fill(Piece(l.s.charAt(j + i * matrix.getSize()).toString,Rotation(0)),i,j)
    }
  }

  def move(xS:Int, yS:Int, xT:Int, yT:Int): Unit = {
    if (checkMove(xS, yS, xT, yT)) {
      val pS = matrix.get(xS, yS)
      val pT = matrix.get(xT, yT)
      matrix.fill(pS, xT, yT)
      matrix.fill(pT, xS, yS)
    } else {
      println("ungültig")
    }
  }

  def checkMove(xS:Int, yS:Int, xT:Int, yT:Int): Boolean = {
    if (xS >= matrix.size ||
      xT >= matrix.size ||
      yS >= matrix.size ||
      yT >= matrix.size) {
      return false
    }

    val pT = matrix.get(xT, yT)
    val pS = matrix.get(xS, yS)

    if (pS.s == "S" || pS.s == "E" || pS.s == "0") {
      return false
    }

    if (xS == xT) {
      if (yS - yT == -1 || yS - yT == 1) {
        if (pT.s == "0"){
          return true
        }

      }
    }

    if (yS == yT) {
      if (xS - xT == -1 || xS - xT == 1) {
        if (pT.s == "0"){
          return true
        }
      }
    }
    false
  }

  def solve(): Level = {
    val sb = new StringBuilder()
    for (i <- 0 until matrix.getSize(); j <- 0 until matrix.getSize()){
      sb.append(matrix.get(i,j).s)
    }
    Level(sb.toString())
  }
}
