package com.wordledist.actors

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.wordledist.messages.{ValidateGuess, WordCommand}
import com.wordledist.util.WordDictionary

object WordActor {
  def apply(): Behavior[WordCommand] = Behaviors.setup { context =>
    val wordDictionary = new WordDictionary()
    val targetWord = wordDictionary.getRandomWord
    context.log.info(s"Target word for this game session: $targetWord")

    Behaviors.receiveMessage {
      case ValidateGuess(playerId, guess) =>
        val result = guess.equalsIgnoreCase(targetWord)
        //        re ! GuessResult(playerId, guess, result)
        Behaviors.same

      case _ =>
        Behaviors.unhandled
    }
  }
}
