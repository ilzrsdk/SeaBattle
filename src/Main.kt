import ShipType.entries
import kotlin.random.Random

fun main() {
    val player = Player("Вы")
    val enemy = Player("Компьютер")

    printRules()

    println("=== РАССТАНОВКА ВАШИХ КОРАБЛЕЙ ===")
    placePlayerShips(player)

    println()
    println("=== КОМПЬЮТЕР РАССТАВЛЯЕТ КОРАБЛИ ===")
    placeEnemyShips(enemy)

    println()
    println("=== ИГРА НАЧАЛАСЬ ===")

    var playerTurn = true
    var moves = 0

    while (player.hasShips() && enemy.hasShips()) {
        printBothFields(player, enemy)
        println("Осталось кораблей: у вас ${player.countShips()}, у компьютера ${enemy.countShips()}")
        println()

        if (playerTurn) {
            println("Ваш ход. Введите координаты выстрела (row col) или quit:")
            val line = readln().trim()
            if (line == "quit") {
                println("Вы вышли из игры.")
                return
            }
            val input = line.split(" ")
            if (input.size != 2) {
                println("Ошибка: нужно два числа через пробел.")
                continue
            }
            val r = input[0].toIntOrNull()
            val c = input[1].toIntOrNull()
            if (r == null || c == null) {
                println("Ошибка: координаты должны быть числами.")
                continue
            }
            if (!enemy.isInside(r, c)) {
                println("Ошибка: координаты вне поля.")
                continue
            }
            val cell = enemy.field()[r][c]
            if (cell == CellState.HIT || cell == CellState.MISS) {
                println("Сюда уже стреляли!")
                continue
            }
            val hit = enemy.shoot(r, c)
            moves++
            if (hit) {
                println("Попадание!")
            } else {
                println("Промах.")
                playerTurn = false
            }
        }
        else {
            println("Ход компьютера...")
            var r: Int
            var c: Int
            do {
                r = Random.nextInt(0, 10)
                c = Random.nextInt(0, 10)
            } while (player.field()[r][c] == CellState.HIT || player.field()[r][c] == CellState.MISS)
            val hit = player.shoot(r, c)
            moves++
            if (hit) {
                println("Компьютер попал в ($r, $c)!")
            } else {
                println("Компьютер промахнулся в ($r, $c).")
                playerTurn = true
            }
        }
        println()
    }

    if (enemy.hasShips()) {
        println("Победил компьютер!")
    } else {
        println("Вы победили!")
    }
    println("Игра длилась $moves ходов.")
}


