fun main() {
    val player = Player()
    player.name = "Вы"
    val enemy = Player()
    enemy.name = "Противник"

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

    player.field[3][2] = '#'
    player.field[3][3] = '#'
    player.field[3][4] = '#'
    player.field[3][5] = '#'

    enemy.field[5][7] = '#'
    enemy.field[6][7] = '#'
    enemy.field[7][7] = '#'
    enemy.field[5][5] = 'x'
    enemy.field[5][6] = 'o'

    //player.printField()
    //enemy.printField(false)
    //printBothFields(player, enemy)
    //printBothFields(player, enemy, true)
    //player.printField(debug = true)
}

fun printBothFields(player: Player, enemy: Player, showEnemyShips: Boolean = false) {
    if (player.field.size != enemy.field.size || player.field[0].size != enemy.field[0].size) {
        println("Ошибка: размеры полей должны совпадать!")
    }
    else {
        println("====== Ваше поле ======       === Поле противника ===")
        print("   ")
        for (c in player.field.indices) print("$c ")
        print("      ")
        print("    ")
        for (c in enemy.field.indices) print("$c ")
        println()
        for (r in enemy.field.indices) {
            print("$r  ")
            for (c in player.field.indices) print("${player.field[r][c]} ")
            print("       ")
            print("$r  ")
            for (c in enemy.field.indices) {
                if (showEnemyShips) {
                    print("${enemy.field[r][c]} ")
                } else {
                    if (enemy.field[r][c] == '#') print(". ")
                    else print("${enemy.field[r][c]} ")
                }
            }
            println()
        }
    }
}




