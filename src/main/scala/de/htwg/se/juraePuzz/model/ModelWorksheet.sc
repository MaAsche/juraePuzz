case class Rotation(r:Int)
case class Piece(s:String,r:Rotation)

case class Matrix(size:Int) {
  val matrix = Array.ofDim[Piece](size, size)
  def fill(p:Piece, row:Int, col:Int) = matrix(row)(col) = p
  def get(row:Int, col:Int) = matrix(row)(col)
  def getSize():Int = size
}

var m = Matrix(5)
m.size
m.fill(Piece("S", Rotation(0)), 0, 0)
m.get(0,0)

case class Level(s:String)

case class Grid(size:Int,l:Level){
  val matrix = Matrix(size)
  def print() = {
    for (i <- 0 until size; j <- 0 until size) {
      println(matrix.get(i,j))
    }
  }

  def init(): Unit = {
    for (i <- 0 until size; j <- 0 until size){
      matrix.fill(Piece("0", Rotation(0)), i, j)
    }
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

  def solve(): Boolean = {
    val sb = new StringBuilder()
    for (i <- 0 until matrix.getSize(); j <- 0 until matrix.getSize()){
      sb.append(matrix.get(i,j))
    }
    Level(sb.toString()).s.equals(l.s)
  }
}

val g = Grid(2, Level("E0G0"))
g.init()
g.fill(Piece("S", Rotation(0)), 0, 0)
g.fill(Piece("E",Rotation(0)),0,0)
g.fill(Piece("G",Rotation(0)),1,1)
g.move(1,1, 1, 0)
g.move(0,0, 0, 2)
g.move(0,1,1,1)
g.render()

g.solve()


val l = Level("")


