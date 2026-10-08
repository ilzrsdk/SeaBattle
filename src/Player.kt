class Player(size: Int = 10) {
    var name: String = ""
    var field: Array<CharArray> = Array(size) { CharArray(size){'.'} }

    var shots: Int = 0
    var hits: Int = 0

    var shipsLeft: Int = 20

    fun status(): String {
        return "Игрок $name: палуб осталось $shipsLeft, выстрелов $shots, попаданий $hits, точность ${String.format("%.1f", accuracy())}%"
    }

    fun printStats() {
        println(status())
    }

    fun registerShot(hit: Boolean) {
        shots++
        if (hit) {
            hits++
        }
    }

    fun accuracy(): Double {
        return if (shots == 0) {
            0.0
        } else {
            hits * 100.0 / shots
        }
    }

    fun takeDamage(): Boolean {
        shipsLeft--
        return if (shipsLeft > 0) {
            true
        } else {
            false
        }
    }

    fun isAlive(): Boolean{
        return if (shipsLeft > 0) {
            true
        } else {
            false
        }
    }

    fun reset() {
        shots = 0
        hits = 0
        shipsLeft = 20
        for (r in field.indices) {
            for (c in field[r].indices) {
                field[r][c] = '.'
            }
        }
    }

    fun printField(showShips: Boolean = true, debug: Boolean = false){
        if (debug) {
            println("=== $name (debug) ===")
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
            println("==== $name ====")
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


}