import sun.awt.geom.Curve

case class Piece(var p:String)

case class Tile(x:Int, y:Int, p:Piece)

val t = Tile(3,2, Piece("x"))

t.x
t.y
t.p

case class Field(tiles:Array[Tile])

val sizeX = 4
val sizeY = 4

val field1 = Field(Array.ofDim(sizeX * sizeY))

for (row <- 0 until sizeX; column <- 0 until sizeY) {
  field1.tiles(row + (sizeX * column)) = Tile(row, column, Piece("x"))
}

for (i <- 0 until sizeX * sizeY) {
  println(field1.tiles(i))
}

for (i <- field1.tiles) {
  if (i.x == sizeX - 1){
    println(i.p.p)
  }
  else{
    print(i.p.p)
  }
}
class Rotation (var r:Int)

case class Curve(var k:String,var r:Rotation,var pos:Int)

class Stright(var s:String, var r:Rotation,var pos:Int)

class StartPiece()

class FinishPiece()


val c1 = Curve("c",new Rotation(45),4);






