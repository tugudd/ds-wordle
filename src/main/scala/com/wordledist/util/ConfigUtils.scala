package com.wordledist.util

import com.typesafe.config.{Config, ConfigFactory}

object ConfigUtils {
  private val config: Config = ConfigFactory.load()

  def getAkkaConfig: Config = config.getConfig("akka")

  def getApplicationConfig: Config = config.getConfig("application")

  // Add any specific configuration retrieval methods here
}
