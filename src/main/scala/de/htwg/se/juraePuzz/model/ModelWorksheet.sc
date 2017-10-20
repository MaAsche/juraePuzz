
class Point(val x:Int,val y:Int)

class Rotation (var r:Int)

//Elemente auf dem Spielfeld
//Oberklasse Piece
abstract class Piece (val p:String,val r:Rotation,val point: Point){
}

class Curve(s:String,r:Rotation,p:Point) extends Piece(s, r, p){

}


case class Stright(var s:String, var r:Rotation,var point: Point)

case class StartPiece(var s:String, var r:Rotation,var point: Point)

case class FinishPiece(var s:String, var r:Rotation,var point: Point)

case class Wall(var s:String, var r:Rotation,var point: Point)


val t = new Point(3,2)

case class Field(tiles:Array[Piece])

val sizeX = 4
val sizeY = 4

val field1 = Field(Array.ofDim(sizeX * sizeY))

for (row <- 0 until sizeX; column <- 0 until sizeY) {
  field1.tiles(row + (sizeX * column)) = new Curve(")",new Rotation(45),new Point(column,row))
}

for (i <- 0 until sizeX * sizeY) {
  println(field1.tiles(i))
}

for (i <- field1.tiles) {
  if (i.point.x == sizeX - 1){
    println(i.p)
  }
  else{
    print(i.p)
  }
}




