fun main() {
    val player = Player()
    val enemy = Player()

    player.name = "Ваше поле"
    enemy.name = "Поле противника"

    player.field[3][2] = '#'
    player.field[3][3] = '#'
    player.field[3][4] = '#'
    player.field[3][5] = '#'

    enemy.field[5][7] = '#'
    enemy.field[6][7] = '#'
    enemy.field[7][7] = '#'
    enemy.field[5][5] = 'x'
    enemy.field[5][6] = 'o'

    player.printField()
    enemy.printField(false)
    printBothFields(player.field, enemy.field)
    printBothFields(player.field, enemy.field, true)
    player.printField(debug = true)
}

fun printBothFields(player: Array<CharArray>, enemy: Array<CharArray>, showEnemyShips: Boolean = false) {
    if (player.size != enemy.size || player[0].size != enemy[0].size) {
        println("Ошибка: размеры полей должны совпадать!")
    }
    else {
        println("====== Ваше поле ======       === Поле противника ===")
        print("   ")
        for (c in player.indices) print("$c ")
        print("      ")
        print("    ")
        for (c in enemy.indices) print("$c ")
        println()
        for (r in enemy.indices) {
            print("$r  ")
            for (c in player.indices) print("${player[r][c]} ")
            print("       ")
            print("$r  ")
            for (c in enemy.indices) {
                if (showEnemyShips) {
                    print("${enemy[r][c]} ")
                } else {
                    if (enemy[r][c] == '#') print(". ")
                    else print("${enemy[r][c]} ")
                }
            }
            println()
        }
    }
}




