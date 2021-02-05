@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.sqrt
import kotlin.math.pow

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    if (n == 0)
        return 1
    var count = 0
    var m = n
    while (m != 0) {
        count += 1
        m /= 10
    }
    return count
}
/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 1 || n == 2)
        return 1
    var res1 = 1
    var res2 = 1
    var res = 0
    for (i in 3..n) {
        res = (res2 + res1)
        res1 = res2
        res2 = res
    }
    return res
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var m1 = kotlin.math.min(n, m)
    var n1 = kotlin.math.max(n, m)
    while (n1 % m1 != 0) {
        n1 += kotlin.math.max(n, m)
    }
    return n1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var i = 2
    while (i <= sqrt(n.toDouble())) {
        if (n % i == 0)
            return i
        i++
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var i = 2
    while (i <= sqrt(n.toDouble())) {
        if (n % i == 0)
            return n / i
        i++
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var a = kotlin.math.max(m, n)
    var b = kotlin.math.min(m, n)
    var r = a % b
    while (r != 0) {
        a = b
        b = r
        r = a % b
    }
    return b == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val start = sqrt(m.toDouble()).toInt()
    val end = sqrt(n.toDouble()).toInt()
    return end - start > 0 || start * start == m || end * end == n
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var x0 = x
    var count = 0
    while (x0 != 1) {
       if (x0 % 2 == 0) x0 = x0 / 2
       else x0 = 3 * x0 + 1
       count++
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var x0 = x
    while (x0 > 2 * kotlin.math.PI - eps) {
        x0 -= 2 * kotlin.math.PI
    }
    var xi = x0
    var sum = xi
    var i = 0
    while (kotlin.math.abs(xi) >= eps) {
        i++
        if (i % 2 == 1){
            xi = x0.pow(1 + 2 * i) / factorial(1 + 2 * i)
            sum -= xi
        } else {
            xi = x0.pow(1 + 2 * i) / factorial(1 + 2 * i)
            sum += xi
        }
    }
    return sum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var x0 = x
    while (x0 > 2 * kotlin.math.PI - eps) {
        x0 -= 2 * kotlin.math.PI
    }
    var xi = 1.0
    var sum = xi
    var i = 0
    while (kotlin.math.abs(xi) >= eps) {
        i++
        if (i % 2 == 1){
            xi = x0.pow(2 * i) / factorial(2 * i)
            sum -= xi
        } else {
            xi = x0.pow(2 * i) / factorial(2 * i)
            sum += xi
        }
    }
    return sum
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var n = n
    var nRevert = 0
    while (n != 0) {
        nRevert = nRevert * 10 + n % 10
        n /= 10
    }
    return nRevert
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    return n - revert(n) == 0
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n = n
    var curDigit = n % 10
    while (n != 0) {
        n /= 10
        if (n % 10 != curDigit && n != 0)
            return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = 1
    var i = 1
    var sqrSeq = 1
    while (count < n) {
        sqrSeq = 1 // всю последовательность хранить так себе идея
        i++
        var square = i * i
        var m = 1
        var zerosInM = 0
        while (square != 0) {
            square /= 10
            m *= 10
            zerosInM++
        }
        sqrSeq = sqrSeq * m + i * i
        count = count + zerosInM
    }
    return sqrSeq / (10.toDouble().pow(count - n)).toInt() % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    if (n == 1 || n == 2) return 1
    var count = 2
    var sqrSeq = 1
    var i = 1
    while (count < n) {
        sqrSeq = 1 // всю последовательность хранить так себе идея
        i++
        var fibNumber = fib(i) + fib(i - 1)
        var m = 1
        var zerosInM = 0
        while (fibNumber != 0) {
            fibNumber /= 10
            m *= 10
            zerosInM++
        }
        sqrSeq = sqrSeq * m + fib(i) + fib(i - 1)
        count = count + zerosInM
    }
    return sqrSeq / (10.toDouble().pow(count - n)).toInt() % 10
}
