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

    player.placeShip(3,2)
    player.placeShip(3,3)
    player.placeShip(3,4)
    player.placeShip(3,5)

    enemy.placeShip(5,7)
    enemy.placeShip(6,7)
    enemy.placeShip(7,7)

//    enemy.markShot(3, 3, true)
//    enemy.markShot(4, 4, false)
    player.printField()
    enemy.printField(false)
    printBothFields(player, enemy)
    printBothFields(player, enemy, true)
    player.printField(debug = true)
}

fun printBothFields(player: Player, enemy: Player, showEnemyShips: Boolean = false) {
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
                if (showEnemyShips) {
                    print("${enemy.field()[r][c].toChar()} ")
                } else {
                    if (enemy.field()[r][c] == CellState.SHIP) print("${CellState.EMPTY.toChar()} ")
                    else print("${enemy.field()[r][c].toChar()} ")
                }
            }
            println()
        }
    }
}




