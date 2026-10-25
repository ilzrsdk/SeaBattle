import java.lang.reflect.Field

class Player(val name: String, val size: Int = 10) {

    private val _field: Array<Array<CellState>> = Array(size) { Array(size){ CellState.EMPTY } }
    fun field(): Array<Array<CellState>> = _field

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

    fun isInside(row: Int, col: Int): Boolean{
        return row in 0..9 && col in 0..9
    }

    fun canPlaceShip(startRow: Int, startCol: Int, size: Int, horizontal: Boolean): Boolean {
        for (i in 0 until size) {
            val r = if (horizontal) startRow else startRow + i
            val c = if (horizontal) startCol + i else startCol

            if (!isInside(r, c)) return false

            if (_field[r][c] != CellState.EMPTY) return false

            for (dr in -1..1) {
                for (dc in -1..1) {
                    if (dr == 0 && dc == 0) continue
                    val nr = r + dr
                    val nc = c + dc

                    if (isInside(nr, nc) && _field[nr][nc] == CellState.SHIP) {
                        return false
                    }
                }
            }
        }
        return true
    }

    fun hasShips(): Boolean {
        for (r in 0..9) {
            for (c in 0..9) {
                if (_field[r][c] == CellState.SHIP) return true
            }
        }
        return false
    }

    fun registerShot(hit: Boolean) {
        _shots++
        if (hit) _hits++
    }

    fun accuracy(): Double =
        if (_shots == 0) 0.0 else _hits * 100.0 / _shots


    fun placeShip(startRow: Int, startCol: Int, size: Int, horizontal: Boolean): Boolean {
        if (!canPlaceShip(startRow, startCol, size, horizontal)) {
            return false
        }
        for (i in 0 until size) {
            val r = if (horizontal) startRow else startRow + i
            val c = if (horizontal) startCol + i else startCol
            _field[r][c] = CellState.SHIP
        }
        return true
    }

    fun countShips(): Int {
        var count = 0
        for (r in _field.indices) {
            for (c in _field[r].indices) {
                if (_field[r][c] != CellState.SHIP) continue
                val leftShip = c > 0 && _field[r][c - 1] == CellState.SHIP
                val upShip = r > 0 && _field[r - 1][c] == CellState.SHIP
                if (!leftShip && !upShip) count++
            }
        }
        return count
    }

    fun shoot(row: Int, col: Int): Boolean {
        if (!isInside(row, col)) {
            println("Выстрел за границей поля!")
            return false
        }

        val current = _field[row][col]
        if (current == CellState.HIT || current == CellState.MISS) {
            println("Сюда уже стреляли!")
            return false
        }

        val hit = (current == CellState.SHIP)
        _field[row][col] = if (hit) CellState.HIT else CellState.MISS

        registerShot(hit)
        return hit
    }

    fun markShot(row: Int, col: Int, hit: Boolean) {
        if (row in 0..<_field.size && col in 0..<_field.size) {
            _field[row][col] = if (hit) CellState.HIT else CellState.MISS
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
                _field[r][c] = CellState.EMPTY
            }
        }
    }




}