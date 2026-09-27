fun main() {
    val playerField = createEmptyField()
    val enemyField = createEmptyField()

    playerField[3][2] = '#'
    playerField[3][3] = '#'
    playerField[3][4] = '#'
    playerField[3][5] = '#'

    enemyField[5][7] = '#'
    enemyField[6][7] = '#'
    enemyField[7][7] = '#'
    enemyField[5][5] = 'x'
    enemyField[5][6] = 'o'

    printField(playerField, "Ваше поле")
    printField(enemyField, "Поле противника", false)
    printBothFields(playerField, enemyField)
    printBothFields(playerField, enemyField, true)
    printField(playerField, "Ваше поле", debug = true)
}

fun createEmptyField(size: Int = 10): Array<CharArray>{
    return Array(size) {CharArray(size){'.'} }
}

fun printField(field: Array<CharArray>, title: String, showShips: Boolean = true, debug: Boolean = false){
    if (debug) {
        println("=== $title (debug) ===")
        print("     ")
        for (c in field.indices) {
            print("$c   ")
        }
        println()
        print("   +")
        print("---+".repeat(field.size))
        println()
        for (r in field.indices) {
            print("$r  |")
            for (c in field.indices) {
                if (showShips) {
                    print(" ${field[r][c]} |")
                } else {
                    if (field[r][c] == '#') print(" . |")
                    else print(" ${field[r][c]} |")
                }
            }
            println()
            print("   +")
            print("---+".repeat(field.size))
            println()
        }
    }
    else {
        println("==== $title ====")
        print("  ")
        for (c in field.indices) print("$c ")
        println()
        for (r in field.indices) {
            print("$r ")
            for (c in field.indices) {
                if (showShips) print("${field[r][c]} ")
                else {
                    if (field[r][c] == '#') print(". ")
                    else print("${field[r][c]} ")
                }
            }
            println()
        }
    }
}

fun printBothFields(player: Array<CharArray>, enemy: Array<CharArray>, showEnemyShips: Boolean = false) {
    if (player.size != enemy.size || player[0].size != enemy[0].size) {
        println("Ошибка: размеры полей должны совпадать!")
    }
    else{
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




