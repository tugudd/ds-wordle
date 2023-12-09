package com.wordledist

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import com.wordledist.actors.{GameManager, PlayerActor, WordActor}
import com.wordledist.messages.GameMessages._
import org.scalatest.wordspec.AnyWordSpecLike

class WordleGameTest extends ScalaTestWithActorTestKit with AnyWordSpecLike {

  "A GameManager" must {
    "start a game with given players" in {
      val gameManager = spawn(GameManager())
      val players = Set("player1", "player2")
      gameManager ! StartGame(players)
      // Assertions for game start
    }

    "validate guesses correctly" in {
      val wordActor = spawn(WordActor())
      val testProbe = createTestProbe[GuessResult]()
      wordActor ! ValidateGuess("player1", "testWord")
      val result = testProbe.expectMessageType[GuessResult]
      // Assertions for guess validation
    }
  }

  "A PlayerActor" must {
    "submit guesses to GameManager" in {
      val player = Player("player1", "Player One")
      val gameManagerProbe = createTestProbe[GameManager.GameCommand]()
      val playerActor = spawn(PlayerActor(player, gameManagerProbe.ref))
      playerActor ! SubmitGuess("guess")
      gameManagerProbe.expectMessage(PlayerGuess(player.id, "guess"))
      // Additional assertions
    }
  }

  // Additional tests can be added for other components like WordActor, ClusterListener, etc.

}
