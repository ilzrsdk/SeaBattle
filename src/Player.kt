class Player(size: Int = 10) {
    var name: String = ""
    var field: Array<CharArray> = Array(size) { CharArray(size){'.'} }

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