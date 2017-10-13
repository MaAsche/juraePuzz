package de.htwg.se.juraePuzz

import de.htwg.se.juraePuzz.model.Player

object juraePuzz {
  def main(args: Array[String]): Unit = {
    val student = Player("Sebastian")
    println("Hello, " + student.name)
  }
}
