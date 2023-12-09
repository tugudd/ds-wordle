package scala.com.wordledist.cluster

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.wordledist.actors.GameManager
import com.wordledist.messages.{CreateGame, NodeCommand, ShutdownNode, StartGame}


object NodeManager {
  def apply(): Behavior[NodeCommand] = Behaviors.receive { (context, message) =>
    message match {
      case CreateGame(gameId, players) =>
        val gameManager = context.spawn(GameManager(), s"gameManager-$gameId")
        players.foreach(player => gameManager ! StartGame(Set(player)))
        Behaviors.same

      case ShutdownNode =>
        context.log.info("Node shutdown requested.")
        Behaviors.stopped

      case _ =>
        Behaviors.unhandled
    }
  }
}
