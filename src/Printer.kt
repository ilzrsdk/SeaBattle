fun printRules() {
    println("========================================")
    println("       МОРСКОЙ БОЙ — ПРАВИЛА")
    println("========================================")
    println("Поля могут быть размера X×X")
    println()
    println("Пример для поля 10×10: ")
    println("Координаты: сначала строка (0-9), потом столбец (0-9).")
    println()
    println("Корабли, которые нужно расставить:")
    println("  1 корабль  × 4 палубы")
    println("  2 корабля  × 3 палубы")
    println("  3 корабля  × 2 палубы")
    println("  4 корабля  × 1 палуба")
    println("Итого: 10 кораблей, 20 палуб.")
    println()
    println("Правила расстановки:")
    println("  • Корабли не могут выходить за границы поля.")
    println("  • Корабли не могут пересекаться.")
    println("  • Корабли не могут касаться друг друга даже углами.")
    println()
    println("Как вводить корабль:")
    println("  1. Введите координату НАЧАЛЬНОЙ палубы: row col")
    println("     Например: 3 4 — строка 3, столбец 4.")
    println("  2. Введите тип корабля: 1, 2, 3 или 4 (число палуб).")
    println("  3. Введите направление:")
    println("     h — горизонтально вправо (→)")
    println("     v — вертикально вниз (↓)")
    println()
    println("Пример: корабль из 3 палуб, начало (2, 5), горизонтально.")
    println("  Ввод: 2 5, затем 3, затем h.")
    println("  Займёт клетки: (2,5), (2,6), (2,7).")
    println()
    println("После каждого корабля поле перерисовывается.")
    println("========================================")
    println()
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


}