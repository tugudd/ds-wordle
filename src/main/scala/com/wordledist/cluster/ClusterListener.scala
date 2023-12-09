package com.wordledist.cluster

import akka.actor.typed.{Behavior, ActorRef}
import akka.actor.typed.scaladsl.Behaviors
import akka.cluster.ClusterEvent._
import akka.cluster.typed.{Cluster, Subscribe}

object ClusterListener {
  def apply(): Behavior[ClusterDomainEvent] = Behaviors.setup { context =>
    val cluster = Cluster(context.system)
    cluster.subscriptions ! Subscribe(context.self, classOf[ClusterDomainEvent])

    Behaviors.receiveMessage {
      case MemberUp(member) =>
        context.log.info(s"Member is Up: ${member.address}")
        Behaviors.same

      case UnreachableMember(member) =>
        context.log.info(s"Member detected as unreachable: ${member}")
        Behaviors.same

      case MemberRemoved(member, previousStatus) =>
        context.log.info(s"Member is Removed: ${member.address} after $previousStatus")
        Behaviors.same

      case _: MemberEvent => // ignore
        Behaviors.same
    }
  }
}
