package com.wordledist.ui

import scalafx.scene.control.{Label, ListView}
import scalafx.scene.layout.VBox
import scalafx.Includes._

class PlayerUI(playerId: String) {
  val playerGuesses = new ListView[String] {
    items = Seq()
  }

  val playerScore = new Label(s"Score: 0")

  val playerStatus = new Label("Waiting for game to start...")

  val playerUI = new VBox {
    children = Seq(
      new Label(s"Player: $playerId"),
      playerGuesses,
      playerScore,
      playerStatus
    )
  }

  def updateGuesses(guesses: Seq[String]): Unit = {
    playerGuesses.items = guesses
  }

  def updateScore(score: Int): Unit = {
    playerScore.text = s"Score: $score"
  }

  def updateStatus(status: String): Unit = {
    playerStatus.text = status
  }
}
