class Avgizer {
    private val data = mutableListOf<TextNumberLine>()
    fun add(line: String) {
        if (line.hasDigit()) {
            TextNumberLine.parse(line).let {
                data.add(it)
            }
        }
    }

    fun analyse(): List<String> {
        val groupedMap = mutableMapOf<String, Float>()
        // Sum loop
        for (textNumber in data) {
            groupedMap[textNumber.text] = groupedMap[textNumber.text]?.plus(textNumber.number) ?: textNumber.number
        }

        // Avg loop
        for (key in groupedMap.keys) {
            val count = data.count { it.text == key }
            groupedMap[key] = groupedMap[key]?.div(count) ?: -1f
        }
        return groupedMap.map { groupedItem ->
            val count = data.count { it.text == groupedItem.key }
            "${groupedItem.key} = ${groupedItem.value} (input count : $count)"
        }
    }
}

private val digitRegex = "\\d+".toRegex()

private data class TextNumberLine(
    val text: String,
    val number: Float
) {
    companion object {
        fun parse(line: String): TextNumberLine {
            val number = digitRegex.findAll(line)
                .lastOrNull()
                ?.groupValues
                ?.lastOrNull()
                ?: error("$line deosn't have numbers in it")
            val newLine = line.replaceFirst(number, "")
            return TextNumberLine(newLine, number.toFloat())
        }
    }
}


private fun String.hasDigit(): Boolean {
    return this.contains(digitRegex)
}