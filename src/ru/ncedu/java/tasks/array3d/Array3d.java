package ru.ncedu.java.tasks.array3d;

import java.io.PrintStream;

/**
 * ЦЕЛЬ ЗАДАЧИ - разобраться с массивами, строками и циклами в Java.<br/>
 * <br/>
 * ЗАДАНИЕ<br/>
 * Создать 3-мерный массив строк, причем не равномерный (не параллепипед).<br/>
 * <br/>
 * ТРЕБОВАНИЯ<br/>
 * Основание массива - прямоугольный равнобедренный треугольник с катетом 10 элементов.<br/>
 * Вершины при острых углах имеют следующие координаты на плоскости x-y: (0,0) и (9,9), а вершина
 *   при прямом угле - (0,9).<br/>
 *   Это НЕ означает, что x должен быть первым индексом массива: рекомендуется сделать
 *   первым индексом y - для экономии памяти и во избежание спецпроверок на границы значений x,y.<br/>
 * Высота в элементах (число значений третьей координаты z) = (x + 1) * (y + 1),
 *   где x и y - индексы точки на треугольнике.<br/>
 * Массив должен изначально (в конструкторе) заполняться объектами класса {@link StringBuilder} 
 *  или {@link StringBuffer} или {@link String}, содержащими индексы массива в виде "x,y,z".<br/>
 * <br/>
 * Вот как должна выглядеть реализация данного интерфейса:<br/>
 *  <code>public class Array3dImpl implements Array3d<String>{  }</code> или<br/>
 *  <code>public class Array3dImpl implements Array3d<StringBuffer>{  }</code> или<br/>
 *  <code>public class Array3dImpl implements Array3d<StringBuilder>{  }</code><br/>
 *  
 * @author Andrey Shevtsov
 */
public interface Array3d<T extends CharSequence>
{
	/**
	 * @param x первый индекс элемента (по оси x)
	 * @param y второй индекс элмента (по оси y)
	 * @param z третий индекс элемента (по оси z)
	 * @return элемент, хранимый в массиве по указанным координатам <code>(x, y, z)</code>
	 * (в т.ч. <code>null</code>, если по указанным координатам хранится <code>null</code>)
	 * @throws (Array)IndexOutOfBoundsException если позиция, заданная координатами, находится вне массива
	 */
	T get (int x, int y, int z) throws IndexOutOfBoundsException;
	/**
	 * Помещает элемент по указанным координатам в массив
	 * @param x первый индекс элемента (по оси x)
	 * @param y второй индекс элмента (по оси y)
	 * @param z третий индекс элемента (по оси z)
	 * @param value элемент, который следует пометить в массив
	 * @return элемент, хранившийся по указанным координатам - аналогично методу {@link #get(int, int, int)}
	 * @throws (Array)IndexOutOfBoundsException если позиция, заданная координатами, находится вне массива
	 */
	T set (int x, int y, int z, T value) throws IndexOutOfBoundsException;
	/**
	 * Печатает массив в указанный поток вывода.
	 * Формат печати следующий:
	 * <ul>
	 *	<li>
	 *		Каждый элемент печатается на новой строке в виде:<br/>
	 *		<code>[x][y][z]:get(x,y,z)</code><br/>
	 *		Где <code>x, y, z</code> - координаты элемента, а <code>get(x,y,z)</code> - сам элемент</li>
	 *	<li>
	 *		Элементы должны выводиться в порядке возрастания x, при равенстве x - в порядке возрастания y, при равенстве x и y - <br/>
	 *		в порядке возрастания z. То есть, элемент <code>[1][1][0]</code> идет после элемента <code>[1][0][1]</code>.
	 *	</li>
	 * </ul>
	 * Метод не является тестируемым, но мы узнаем, если вы его не реализуете.
	 * Проверить корректность метода (для себя, в методе main) вы можете, передав в него System.out.
	 * @param ps {@link PrintStream}, в который надо напечатать массив.
	 * @throws NullPointerException если <code>ps == null</code>
	 */
	void printArray (PrintStream ps);
}
