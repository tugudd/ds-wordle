package com.wordledist.util

import scala.io.Source
import scala.util.Random

class WordDictionary {
  private val wordList: List[String] = loadWordList()

  private def loadWordList(): List[String] = {
    val source = Source.fromResource("wordlist.txt")
    val words = source.getLines().toList
    source.close()
    words
  }

  def getRandomWord: String = {
    val randomIndex = Random.nextInt(wordList.length)
    wordList(randomIndex)
  }
}
