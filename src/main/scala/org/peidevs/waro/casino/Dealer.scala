
package org.peidevs.waro.casino

import org.peidevs.waro.domain._

class Dealer(val deckFactory:DeckFactory) {
    def deal(numCards:Int, players:List[Player]):Table =
        val numPlayers = players.size

        val hands = dealHands(numCards, numPlayers)

        val kitty = hands(0)

        for (index <- 1 to numPlayers)
            players(index - 1).hand = hands(index)

        new Table(players, kitty)
    end deal

    def play(table:Table) =
        table.kitty.foreach { prizeCard =>
            val winner = playRound(prizeCard, table.players)
            println(winner.emitLog())
        }
    end play

    // ------  internal

    def playRound(prizeCard:Int, players:List[Player]):Player =
        val bid = findRoundWinner(prizeCard, players)
        val winningBid = bid.offer
        val winner = bid.player

        val verbose = true
        if verbose then
            println("\nthis round: " + winner.name + " WINS " + prizeCard + " with " + winningBid)

        winner.winsRound(prizeCard)

        winner
    end playRound

    def findRoundWinner(prizeCard:Int, players:List[Player]):Bid =
        var max:Int = 0
        var winningBid:Bid = null

        players.map ( p => {
                val bid = p.getBid(prizeCard)

                val verbose = true
                if verbose then
                    println(p.name + " bids '" + bid.offer + "' against " + max)

                if bid.offer > max then
                    winningBid = bid
                    max = bid.offer
            }
        )

        return winningBid
    end findRoundWinner

    def partition(list:List[Int], size:Int):List[List[Int]] =
        if list.size > 0 then
            val sliceList:List[Int] = list.slice(0,size)
            val rest = list diff sliceList
            List(sliceList) ++ partition(rest, size)
        else
            Nil
    end partition

    def dealHands(numCards:Int, numPlayers:Int):List[List[Int]] =
        val deck:List[Int] = deckFactory.newDeck(numCards)
        val numCardsInHand:Int = getNumCardsInHand(numCards, numPlayers)
        partition(deck, numCardsInHand)

    def getNumCardsInHand(numCards:Int, numPlayers:Int):Int =
        (numCards / (numPlayers + 1))
}
