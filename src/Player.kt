import java.lang.reflect.Field

class Player(val name: String, val size: Int = 10) {

    private val _field: Array<CharArray> = Array(size) { CharArray(size){'.'} }
    fun field(): Array<CharArray> = _field

    private var _shots: Int = 0
    val shots: Int
        get() = _shots

    private var _hits: Int = 0
    val hits: Int
        get() = _hits

    private var _shipsLeft: Int = 20
    val shipsLeft: Int
        get() = _shipsLeft

    fun status(): String {
        return "Игрок $name: палуб осталось $_shipsLeft, выстрелов $_shots, попаданий $_hits, точность ${String.format("%.1f", accuracy())}%"
    }

    fun registerShot(hit: Boolean) {
        _shots++
        if (hit) _hits++
    }

    fun accuracy(): Double =
        if (_shots == 0) 0.0 else _hits * 100.0 / _shots


    fun placeShip(row: Int, col: Int) {
        if (row in 0..<_field.size && col in 0..<_field.size) {
            _field[row][col] = '#'
        }
    }

    fun markShot(row: Int, col: Int, hit: Boolean) {
        if (row in 0..<_field.size && col in 0..<_field.size) {
            _field[row][col] = if (hit) 'x' else 'o'
        }
    }

    fun takeDamage(): Boolean {
        _shipsLeft--
        return isAlive()
    }

    fun isAlive(): Boolean{
        return if (_shipsLeft > 0) {
            true
        } else {
            false
        }
    }

    fun reset() {
        _shots = 0
        _hits = 0
        _shipsLeft = 20
        for (r in _field.indices) {
            for (c in _field[r].indices) {
                _field[r][c] = '.'
            }
        }
    }

    fun printField(showShips: Boolean = true, debug: Boolean = false){
        if (debug) {
            println("=== $name (debug) ===")
            print("     ")
            for (c in _field.indices) {
                print("$c   ")
            }
            println()
            print("   +")
            print("---+".repeat(_field.size))
            println()
            for (r in _field.indices) {
                print("$r  |")
                for (c in _field.indices) {
                    if (showShips) {
                        print(" ${_field[r][c]} |")
                    } else {
                        if (_field[r][c] == '#') print(" . |")
                        else print(" ${_field[r][c]} |")
                    }
                }
                println()
                print("   +")
                print("---+".repeat(_field.size))
                println()
            }
        }
        else {
            println("==== $name ====")
            print("  ")
            for (c in _field.indices) print("$c ")
            println()
            for (r in _field.indices) {
                print("$r ")
                for (c in _field.indices) {
                    if (showShips) print("${_field[r][c]} ")
                    else {
                        if (_field[r][c] == '#') print(". ")
                        else print("${_field[r][c]} ")
                    }
                }
                println()
            }
        }
    }


}