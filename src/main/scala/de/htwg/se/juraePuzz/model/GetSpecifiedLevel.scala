package de.htwg.se.juraePuzz.model

class GetSpecifiedLevel extends LevelGenerateStrategyTemplate {
  override def createLevel(nr: Int): Level = {
    nr match {
      case 1 => Level("S00K0E000")
      case 2 => Level("S0000GE00")
      case 3 => Level("GE000K00S")
      case _ => Level("000000000")
    }
  }
}