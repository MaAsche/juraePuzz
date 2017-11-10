

case class Point(val x:Int,val y:Int)

case class Rotation (var r:Int)

//Elemente auf dem Spielfeld
//Oberklasse Piece
class Piece (val p:String,val r:Rotation,val point: Point){

}
case class Curve(s:String, val r:Rotation,  val p:Point)
case class Stright(var s:String, var r:Rotation,var point: Point)
case class StartPiece(var s:String, var r:Rotation,var point: Point)
case class FinishPiece(var s:String, var r:Rotation,var point: Point)

case class Grid(private val cells:Matrix[Piece]){

}

case class Matrix[T] (size:Int){
  val matrix = Array.ofDim[Piece](size,size)
  matrix(0)(0) = new Piece("asd",Rotation(45),Point(1,2))
}

val matrix = new Matrix[Piece](2)
val grid = new Grid(matrix)


val t = new Point(3,2)

case class Field(tiles:Array[Curve])

val sizeX = 4
val sizeY = 4

val field1 = Field(Array.ofDim(sizeX * sizeY))

for (row <- 0 until sizeX; column <- 0 until sizeY) {
  field1.tiles(row + (sizeX * column)) = Curve("-",Rotation(45),Point(row,column))

}

for (i <- 0 until sizeX * sizeY) {
  println(field1.tiles(i))
}

for (i <- field1.tiles) {
  if (i.p.x == sizeX - 1){
    println(i.p)
  }
  else{
    print(i.p)
  }
}
