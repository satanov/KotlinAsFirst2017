@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1
import lesson1.task1.sqr
import java.lang.Math.*
import javax.print.attribute.IntegerSyntax

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
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
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
    for (m in 2..n/2) {
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
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    if (n == 0) return 1
    var num = n
    var sum = 0
    while(num != 0) {
        sum ++
        num /= 10
    }
    return sum
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var previousIndex = 1
    var nextIndex = 1
    if((n == 1) || (n == 2)) return 1
    for(cicle in 2 until n) {
        val sum = previousIndex + nextIndex
        previousIndex = nextIndex
        nextIndex = sum
    }
    return nextIndex
}
/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var n1 = n
    var m1 = m
    val multi = m1 * n1
    while (m1 != n1) {
        if(m1 > n1) m1 -= n1
        else n1 -= m1
    }
    return multi / m1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int):Int {
    var index= 2
    var minDen = 1
    val sqrtN = Math.sqrt(n.toDouble()).toInt()
    while (index <= sqrtN) {
        if(n % index == 0) {
            minDen = index
            break
        }
        index++
    }
    return if(minDen == 1) n
    else minDen
}
/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxDen = 1
    for(i in n-1 downTo 1) {
        if(n % i == 0) {
            maxDen = i
            break
        }
    }
    return maxDen
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var m1 = m
    var n1 = n
    while(m1 !=0 && n1 !=0 ) {
        if ( m1 >= n1) m1 %= n1
        else n1 %= m1
    }
    m1 += n1
    return m1 == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val squareRootForM = sqrt(m.toDouble()).toInt()
    val squareRootForN = sqrt(n.toDouble()).toInt()
    if (m == 0 && n == 0) return true
    for(i in squareRootForM..squareRootForN) {
        if(pow(i.toDouble(), 2.0) in (m..n)) return true
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    val changedX = x % (2 * PI)
    var member = changedX
    var index = 2
    var point = -1
    var sin = 0.0
    while (abs(member) >= eps) {
        sin +=  -point * member
        member *= sqr(changedX) / (index * (index + 1))
        index += 2
        point *= -1
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val changedX = x % (2 * PI)
    var member = 1.0
    var index = 2
    var point = 1
    var cos = 0.0
    while (abs(member) >= eps) {
        cos += point * member
        member *= sqr(changedX) / (index * (index - 1))
        point *= -1
        index += 2
    }
    return cos
}
/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var k = 0
    var n1 = n
    while (n1 > 0){
        k = k * 10 + (n1 % 10)
        n1 /= 10
    }
    return k
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean =
        n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean
{
    var n1 = n
    var previous = n1 % 10
    while(n1 != 0) {
        val main = n1 % 10
        if(main != previous) {
            return true
        }
        n1 /= 10
        previous = main
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int{
    var member = n
    var index = 0
    var sqrIndex = 0
    while (0 < member){
        index++
        sqrIndex = sqr(index.toDouble()).toInt()
        member -= digitNumber(sqrIndex)
    }
    val positiveMember = abs((member - 1).toDouble())
    return sqrIndex / pow(10.0, positiveMember - 1).toInt() % 10
}
/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var member = n
    var index = 0
    var fibIndex = 0
    while(0 < member) {
        index++
        fibIndex = fib(index)
        member -= digitNumber(fibIndex)
    }
    val positiveMember = abs((member - 1).toDouble())
    return fibIndex / pow(10.0, positiveMember - 1).toInt() % 10
}
