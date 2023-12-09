package com.wordledist

import akka.actor.typed.ActorSystem
import com.wordledist.actors.{GameManager, PlayerActor, WordActor}
import com.wordledist.ui.GameUI
import com.wordledist.cluster.{ClusterListener, NodeManager}
import com.wordledist.util.ConfigUtils

object WordleApp {
  def main(args: Array[String]): Unit = {
    // Initialize AKKA Actor System
    val system = ActorSystem[Nothing](NodeManager(), "WordleGameSystem", ConfigUtils.getAkkaConfig)

    // Starting the Cluster Listener
    system.systemActorOf(ClusterListener(), "clusterListener")

    // Optionally, start the UI if needed
    new GameUI(system.systemActorOf(GameManager(), "gameManager")).main(Array.empty)
  }
}
