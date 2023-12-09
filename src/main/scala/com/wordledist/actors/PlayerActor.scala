package com.wordledist.actors

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import com.wordledist.messages.{GameCommand, GameOver, GuessFeedback, PlayerCommand, PlayerGuess, SubmitGuess, WordCommand}


case class Player(id: String, name: String)

object PlayerActor {
  def apply(player: Player, gameManager: ActorRef[GameCommand]): Behavior[PlayerCommand] = Behaviors.receive { (context, message) =>
    message match {
      case SubmitGuess(guess) =>
        gameManager ! PlayerGuess(player.id, guess)
        Behaviors.same

      case GuessFeedback(guess, result) =>
        context.log.info(s"Player ${player.name} guessed '$guess' and the result was: $result")
        Behaviors.same

      case GameOver =>
        context.log.info(s"Player ${player.name} - Game Over")
        Behaviors.stopped

      case _ =>
        Behaviors.unhandled
    }
  }
}
