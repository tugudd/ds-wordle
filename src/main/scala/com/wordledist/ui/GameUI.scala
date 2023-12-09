package com.wordledist.ui

import akka.actor.typed.ActorRef
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.layout.VBox


class GameUI(gameManagerActor: ActorRef[Any]) extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title.value = "Distributed Wordle Game"
    scene = new Scene(400, 300) {
      val guessInput = new TextField {
        promptText = "Enter your guess here"
      }

      val submitButton = new Button("Submit Guess") {
        onAction = handle {
          gameManagerActor ! gameManagerActor
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
