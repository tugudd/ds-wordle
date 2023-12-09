package scala.com.wordledist.cluster

import akka.actor.typed.{Behavior, ActorRef}
import akka.actor.typed.scaladsl.Behaviors
import com.wordledist.actors.GameManager._
import com.wordledist.messages.GameMessages._


object NodeManager {
  def apply(): Behavior[NodeCommand] = Behaviors.receive { (context, message) =>
    message match {
      case GameMessages.CreateGame(gameId, players) =>
        val gameManager = context.spawn(GameManager(), s"gameManager-$gameId")
        players.foreach(player => gameManager ! GameMessages.StartGame(Set(player)))
        Behaviors.same

      case GameMessages.ShutdownNode =>
        context.log.info("Node shutdown requested.")
        Behaviors.stopped

      case _ =>
        Behaviors.unhandled
    }
  }
}
