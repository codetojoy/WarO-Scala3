
package org.peidevs.waro.domain

class PlayerStats(var total: Int, var numRoundsWon:Int, var numGamesWon:Int) {
    def winsRound(prizeCard:Int): Unit =
        numRoundsWon = numRoundsWon + 1
        total = total + prizeCard

    def winsGame(): Unit =
        numGamesWon = numGamesWon + 1

    def clear(): Unit =
        total = 0
        numRoundsWon = 0

    def emitLog:String =
        "(" + total + " , " + numRoundsWon + " , " + numGamesWon + " )"
}
