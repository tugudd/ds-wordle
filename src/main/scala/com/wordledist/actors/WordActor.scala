package com.wordledist.actors

import akka.actor.typed.{Behavior, ActorRef}
import akka.actor.typed.scaladsl.Behaviors
import com.wordledist.util.WordDictionary
import com.wordledist.messages.GameMessages._

object WordActor {
  def apply(): Behavior[WordCommand] = Behaviors.setup { context =>
    val wordDictionary = new WordDictionary()
    val targetWord = wordDictionary.getRandomWord
    context.log.info(s"Target word for this game session: $targetWord")

    Behaviors.receiveMessage {
      case ValidateGuess(playerId, guess) =>
        val result = guess.equalsIgnoreCase(targetWord)
        sender() ! GuessResult(playerId, guess, result)
        Behaviors.same

      case _ =>
        Behaviors.unhandled
    }
  }
}
