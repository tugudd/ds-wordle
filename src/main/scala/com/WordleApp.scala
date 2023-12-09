package com.wordledist

import akka.actor.typed.ActorSystem
import com.wordledist.actors.GameManager
import com.wordledist.cluster.ClusterListener
import com.wordledist.ui.GameUI

import scala.com.wordledist.cluster.NodeManager

object WordleApp {
  def main(args: Array[String]): Unit = {
    // Initialize AKKA Actor System
    val system: ActorSystem[Nothing] = ActorSystem(NodeManager(), "WordleGameSystem")

    // Starting the Cluster Listener
    system.systemActorOf(ClusterListener(), "clusterListener")

    // Optionally, start the UI if needed
    new GameUI(system.systemActorOf(GameManager(), "gameManager")).main(Array.empty)
  }
}
