case class Tile(x:Int, y:Int)

val t = Tile(3,2)

t.x
t.y

case class Field(tiles:Array[Tile])

val size = 3

val field1 = Field(Array.ofDim(size * size))

for (row <- 0 until size; column <- 0 until size) {
  field1.tiles(column + (size * row)) = Tile(column, row)
}

for (i <- 0 until size * size) {
  println(field1.tiles(i))
}

for (i <- field1.tiles) {
  println("x: " + i.x, "y: " + i.y)
}











