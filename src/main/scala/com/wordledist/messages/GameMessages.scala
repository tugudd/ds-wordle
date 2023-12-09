package com.wordledist.messages

sealed trait GameCommand
sealed trait PlayerCommand
sealed trait WordCommand
sealed trait NodeCommand

// Messages related to the game manager
case class StartGame(players: Set[String]) extends GameCommand
case class PlayerGuess(playerId: String, guess: String) extends GameCommand
case class GuessResult(playerId: String, guess: String, result: Boolean) extends GameCommand

// Messages for player actors
case class SubmitGuess(guess: String) extends PlayerCommand
case class GuessFeedback(guess: String, result: Boolean) extends PlayerCommand
case object GameOver extends PlayerCommand

// Messages for word actor
case class ValidateGuess(playerId: String, guess: String) extends WordCommand

// Messages for node management
case class CreateGame(gameId: String, players: Seq[String]) extends NodeCommand
case object ShutdownNode extends NodeCommand

// Add additional message types as needed
