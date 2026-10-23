import ShipType.entries

fun main() {
    val player = Player("Вы")
    val enemy = Player("Противник")

    player.registerShot(true)
    player.registerShot(false)
    player.registerShot(true)

    println(player.status())

    player.takeDamage()
    player.takeDamage()
    println(player.shipsLeft)
    println(player.isAlive())

    player.reset()
    println(player.status())



    enemy.markShot(3, 3, true)
    enemy.markShot(4, 4, false)
    //player.printField()
    //enemy.printField(false)
    printBothFields(player, enemy)
    printBothFields(player, enemy)
    //player.printField(debug = true)

    for (ship in ShipType.entries) {
        println("$ship — ${ship.size}")
    }

    println(ShipType.BATTLESHIP.isBig())
    println(ShipType.BOAT.isBig())
}

fun printBothFields(player: Player, enemy: Player) {
    if (player.field().size != enemy.field().size || player.field()[0].size != enemy.field()[0].size) {
        println("Ошибка: размеры полей должны совпадать!")
    }
    else {
        println("====== ${player.name} ======                  === ${enemy.name} ===")
        print("   ")
        for (c in player.field().indices) print("$c ")
        print("      ")
        print("    ")
        for (c in enemy.field().indices) print("$c ")
        println()
        for (r in enemy.field().indices) {
            print("$r  ")
            for (c in player.field().indices) print("${player.field()[r][c].toChar()} ")
            print("       ")
            print("$r  ")
            for (c in enemy.field().indices) {
                val state = enemy.field()[r][c]
                val shown = if (state == CellState.SHIP) CellState.EMPTY else state
                print("${shown.toChar()} ")
            }
            println()
        }
    }
}

fun printField(player: Player, hideShips: Boolean, debug: Boolean = false){
    if (debug) {
        println("=== ${player.name} (debug) ===")
        print("     ")
        for (c in player.field().indices) {
            print("$c   ")
        }
        println()
        print("   +")
        print("---+".repeat(player.field().size))
        println()
        for (r in player.field().indices) {
            print("$r  |")
            for (c in player.field().indices) {
                val state = player.field()[r][c]
                val shown = if (hideShips && state == CellState.SHIP) CellState.EMPTY else state
                print("${shown.toChar()} ")
            }
            println()
            print("   +")
            print("---+".repeat(player.field().size))
            println()
        }
    }
    else {
        println("==== ${player.name} ====")
        print("  ")
        for (c in player.field().indices) print("$c ")
        println()
        for (r in player.field().indices) {
            print("$r ")
            for (c in player.field().indices) {
                val state = player.field()[r][c]
                val shown = if (hideShips && state == CellState.SHIP) CellState.EMPTY else state
                print("${shown.toChar()} ")
            }
            println()
        }
    }

    fun printRules() {
        println("=========================================")
        println("           МОРСКОЙ БОЙ - ПРАВИЛА")
        println("=========================================")
        println("")
    }

}


