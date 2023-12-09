package com.wordledist.actors

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import com.wordledist.messages.{GameCommand, GameOver, GuessFeedback, GuessResult, PlayerCommand, PlayerGuess, StartGame, ValidateGuess, WordCommand}

import scala.collection.mutable

object GameManager {
  def apply(): Behavior[Any] = Behaviors.receive { (context, message) =>
    message match {
//      case StartGame(players) =>
//        val wordActor = context.spawn(WordActor(), "wordActor")
////        val playerActors = players.map(player => context.spawn(PlayerActor(Player(player, player), wordActor), s"playerActor-${player.id}"))
////        gameInProgress(wordActor, playerActors.toSet, mutable.Set.empty)

      case _ =>
        Behaviors.unhandled
    }
  }

  private def gameInProgress(wordActor: ActorRef[WordCommand], 
                             playerActors: Set[ActorRef[PlayerCommand]],
                             guesses: mutable.Set[String]): Behavior[GameCommand] =
    Behaviors.receive { (context, message) =>
      message match {
        case PlayerGuess(playerId, guess) =>
          wordActor ! ValidateGuess(playerId, guess)
          Behaviors.same

        case GuessResult(playerId, guess, result) =>
          playerActors.find(_.path.name == s"playerActor-$playerId").foreach(_ ! GuessFeedback(guess, result))
          if (result) {
            guesses += guess
            if (guesses.size == playerActors.size) {
              playerActors.foreach(_ ! GameOver)
              Behaviors.stopped
            } else {
              Behaviors.same
            }
          } else {
            Behaviors.same
          }

        case _ =>
          Behaviors.unhandled
      }
    }
}
