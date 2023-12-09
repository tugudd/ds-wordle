package com.wordledist.ui

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Label, TextField, Button}
import scalafx.scene.layout.VBox
import scalafx.Includes._

class GameUI(gameManagerActor: ActorRef[GameManager.GameCommand]) extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title.value = "Distributed Wordle Game"
    scene = new Scene(400, 300) {
      val guessInput = new TextField {
        promptText = "Enter your guess here"
      }

      val submitButton = new Button("Submit Guess") {
        onAction = handle {
          gameManagerActor ! GameManager.PlayerGuess("playerId", guessInput.text.value)
          guessInput.clear()
        }
      }

      val gameStatus = new Label("Game status will be displayed here.")

      content = new VBox {
        children = Seq(guessInput, submitButton, gameStatus)
      }
    }
  }
}
