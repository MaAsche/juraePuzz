case class Rotation(r:Int)
case class Piece(s:String,r:Rotation)

case class Matrix(size:Int) {
  val matrix = Array.ofDim[Piece](size, size)
  def fill(p:Piece, row:Int, col:Int) = matrix(row)(col) = p
  def get(row:Int, col:Int) = matrix(row)(col)
  def getSize():Int = size
}

case class Grid(size:Int){
  val matrix = Matrix(size)
  val kurve = Piece("K", Rotation(0))
  val gerade = Piece("G", Rotation(0))
  val start = Piece("S", Rotation(0))
  val ende = Piece("E", Rotation(0))
  val leer = Piece("0", Rotation(0))
  def print() = {
    for (i <- 0 until size; j <- 0 until size) {
      println(matrix.get(i,j))
    }
  }

  def init(): Unit = {
    for (i <- 0 until size; j <- 0 until size){
      matrix.fill(leer, i, j)
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
}

val g = Grid(4)
g.init()
g.fill(Piece("S", Rotation(0)), 0, 0)
g.render()



