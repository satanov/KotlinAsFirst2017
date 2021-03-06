@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> =
        if (height <= 0 || width <= 0) throw IllegalArgumentException()
        else MatrixImpl(height, width, e) //функция была прописана в пункте "Ассоциативные массивы"

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height:Int, override val width: Int, e:E): Matrix<E> {

    //override val height: Int =
    //override val width: Int =
    //Начать нужно всегда с ответа на вопрос — какие данные описывают интересующий нас объект (матрицу)
    //и как их можно представить на данном языке программирования?
    //Для матрицы первая часть ответа такова — высота и ширина матрицы (целые числа) и
    //набор элементов матрицы (типа E).
    //Поскольку имеющиеся у матрицы функции не предполагают изменения её высоты и ширины,
    //их лучше всего объявить как свойства в конструкторе матрицы
    //определения свойств высоты и ширины исчезли из тела класса и переехали в
    //конструктор — при этом сохранив необходимый модификатор override

    /* Представление набора элементов.
    * Для этого нужен некоторый контейнер, ссылка на который хранилась бы в еще одном
    * свойстве матрицы. Лучше, чтобы это свойство было закрытым, чтобы возможные
    * действия с матрицей ограничивались лишь свойствами и функциями из интерфейса Matrix*/

    private val matrix = MutableList(height) {
        MutableList(width) {
            e //для заполнения элементов матрицы
        }
    }

    //Так как у нашего класса тип Matrix<E>, то мы должны реализовать абстрактные методы данного интерфейса
    //Это в свою очередь override fun get(x2) и override fun set(x2)
    //модификатор override сигнализирует об переопределнии метода. Мы переопредлеям всю функциональность,
    //которая была до этого


    override fun get(row: Int, column: Int): E = matrix[row][column]

    override fun get(cell: Cell): E = matrix[cell.row][cell.column]

    //Две операторных функции get предназначены для определения содержимого определённой ячейки матрицы
    //(для удобства, одна из них работает с двумя целочисленными параметрами, другая — с одним параметром-ячейкой).
    //Результат обеих функций имеет тип E

    override fun set(row: Int, column: Int, value: E) {
        matrix[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        matrix[cell.row][cell.column] = value
    }

    //операторные функции set предназначены для замены содержимого определённой ячейки матрицы.
    // Их последний параметр содержит элемент, который нужно записать в заданную ячейку.
    // Результат у данных функций отсутствует, но они меняют внутреннее состояние матрицы,
    // то есть имеют побочный эффект. Вызывать set можно как непосредственно,
    // так и с помощью индексации: matrix[cell] = value эквивалентно matrix.set(cell, value)

    override fun equals(other: Any?): Boolean {

        //equals - фунция для сравнения матриц

        /* Матрицы считаются равны, когда равны их высоты и ширины, а также равны
        * соответствующие друг другу элементы, но перед сравнениеv следует проверить,
        * что параметр other принадлежит к типу MatrixImpl<E>*/


        if (other is MatrixImpl<*> && height == other.height && width == other.width)
            //принадлежит ли параметр other  типу MatrixImpl<E> и //сравнение высот и ширин
            for (i in 0 until height)  //проверка на равенство соответствующих элементов
                for (j in 0 until width)
                    if (this[i, j] != other[i, j])
                        return false
        return true
    }

    override fun toString(): String {
        //toString - функция представления матрицы в виде строки
        val sb = StringBuilder("")

        //StringBuilder - специальный тип для построения внутри себя строки , Его использование здесь
        //эффективнее, чем определение val str = "", поскольку такой метод потребует многократного
        //создания новых строк. Внутри себя StringBuilder содержит постепенно расширяющуюся строку,
        //изначально пустую. Функция sb.append дописывает к этой строке новую

        for(i in 0 until height) {
            if (i != 0) {
                sb.append("\n")
            }
            for(j in 0 until width) {
                sb.append(String.format("%2d", this[i, j]), ' ')

                /* Напоминание из Lesson 5: Первым аргументом функции String.format() является
                * форматная строка, в которой особый смысл несет символ процента %. Этот
                 * символ вместе с несколькими последующими образует модификатор формата,
                 * который функцией формата будет заменен на ее следующий элемент. Конкретно
                 * %2d означает "подставить в строку целое число, заняв не меньше двух
                 * позиций(пустые по умолчанию заполняются пробелами)"*/

            }
        }
        /*"$sb" в конце достает из построителя накопленную строку */
        return sb.toString()//"$sb" //или sb.toString()
    }
    override fun hashCode(): Int {

        //hashCode() каким-то чудесным образом появился сам, после нажатия Alt+enter на названии функции

        //спросим у нашего эксперта:

        //ты переопоеделил икуалс, будь добр заебошить хешкод (c) Альмир

        var result = height
        result = 31 * result + width
        result = 31 * result + matrix.hashCode()
        return result
    }
}

