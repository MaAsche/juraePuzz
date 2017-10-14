case class Piece(p:String)

case class Tile(x:Int, y:Int, p:Piece)

val t = Tile(3,2, Piece("x"))

t.x
t.y
t.p

case class Field(tiles:Array[Tile])

val size = 4

val field1 = Field(Array.ofDim(size * size))

for (row <- 0 until size; column <- 0 until size) {
  field1.tiles(column + (size * row)) = Tile(column, row, Piece("y"))
}

for (i <- 0 until size * size) {
  println(field1.tiles(i))
}

for (i <- field1.tiles) {
  if (i.x == size - 1) println(i.p.p)
  else print(i.p.p)
}











