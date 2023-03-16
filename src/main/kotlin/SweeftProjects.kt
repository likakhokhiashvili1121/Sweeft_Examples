fun main() {
//1
    val singNum = intArrayOf(10,5,5,12,15,12)
    println(singleNumber(singNum))
//2
    val x = 100
    println(minSplit(x))
//3
    val notInclude = intArrayOf(15,12,7,16)
    println(notContains(notInclude))
//4
    val addBin = addBinary("1010","1111")
    println(addBin)
//5
    val count = countVariants(30)
    println(countVariants(count))

//6
    val data =  DeletionInConstantTime<Int>()
    data.add(6)
    data.add(10)
    println("items:")
    data.printItems()
    data.delete(2)
    println("after deletion:")
    data.printItems()

}
/////////////////////////////////1/////////////////////////////////

fun singleNumber(nums: IntArray): Int {
    var result = 0
    for (i in nums.indices) {
        result = result xor nums[i]
    }
    return result
}

/*
fun findUniqueNumber(numbers: List<Int>): Int {
    var result = 0
    for (number in numbers) {
        result = result xor number
    }
    return result
}
 */

/////////////////////////////////2/////////////////////////////////
fun minSplit(amount: Int): Int {
    val coins = intArrayOf(50, 20, 10, 5, 1)
    var count = 0
    var balance = amount
    for (coin in coins) {
        while (balance >= coin) {
            balance -= coin
            count++
        }
    }
    return count
}



/////////////////////////////////3/////////////////////////////////
fun notContains(array: IntArray): Int {
    array.sort()
    var min = 1
    for (i in array) {
        if (i > min) {
            return min
        } else if (i == min) {
            min++
        }
    }
    return min
}


/////////////////////////////////4/////////////////////////////////
fun addBinary(a: String, b: String): String {
    var carry = 0
    var i = a.length - 1
    var j = b.length - 1
    var result = ""
    // val result = StringBuilder()

    while (i >= 0 || j >= 0) {

        val binA = if (i >= 0) a[i--] - '0' else 0
        val binB = if (j >= 0) b[j--] - '0' else 0
        val sum = binA + binB + carry
        val binSum = sum % 2
        carry = sum / 2
        result = binSum.toString() + result
    }
    if (carry > 0) {
        result = carry.toString() + result
    }

    return result
    //return result.reverse().toString()
}


/////////////////////////////////5/////////////////////////////////
fun countVariants(stepsCount: Int): Int {
    if (stepsCount <= 1) {
        return 1
    }
    var oneStep = 1
    var twoStep = 1
    for (i in 2..stepsCount) {
        val currentStep = oneStep + twoStep
        twoStep = oneStep
        oneStep = currentStep
    }
    return oneStep
}

/////////////////////////////////6/////////////////////////////////
class DeletionInConstantTime<T> {

    private val items = mutableListOf<T>()
    private val itemPositions = mutableMapOf<T, Int>()


    fun add(item: T) {
        items.add(item)
        itemPositions[item] = items.lastIndex
    }

    fun delete(item: T) {
        val position = itemPositions[item] ?: return
        val lastPosition = items.lastIndex
        val lastItem = items[lastPosition]

        items[position] = lastItem
        itemPositions[lastItem] = position

        items.removeAt(lastPosition)
        itemPositions.remove(item)
    }

    fun printItems() {
        println(items)
    }
}



