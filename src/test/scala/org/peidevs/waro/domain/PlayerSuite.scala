package org.peidevs.waro.domain

import org.peidevs.waro.strategy._

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSuite extends AnyFunSuite {
    test("make a bid with pop strategy") {
        var player = new Player("Chopin", 60, new PopCard(), List(2,3,4))

        // test
        val bid = player.getBid(10)

        assert(2 == bid.offer)
        assert(player == bid.player)
    }
}
