package de.htwg.se.juraePuzz.model

import de.htwg.se.juraePuzz.controller.Controller

trait LevelGenerateStrategyTemplate {
  def createLevel(controller: Controller): Level
}
