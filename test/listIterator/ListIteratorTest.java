package listIterator;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorTest {

	public static void main(String[] args) {
		ListIteratorTest tliterator = new ListIteratorTest();
		List<String> list = new LinkedList<String>();
		tliterator.initial(list);// 初始化这个链接表
		ListIterator<String> li = list.listIterator();// 将该链接表转化为ListIterator

		// 下面的代码进行ListIterator对象li的各种功能检测
		// 顺序输出迭代器中的元素
		while (li.hasNext()) {
			System.out.print(li.next().toString() + " ");
		}

		// 验证add方法，给li添加两个元素
		li.add("元素五");
		li.add("元素六");

		System.out.println();// 产生换行操作

		// 通过使用ListIterator的特有方法hasPrevious与previous实现List的元素
		// 逆序输出
		for (String str; li.hasPrevious();) {
			System.out.print(li.previous().toString() + " ");
		}

		System.out.println();// 产生换行操作

		// 顺序输出li迭代器中现有的元素
		while (li.hasNext()) {
			System.out.print(li.next().toString() + " ");
		}

		System.out.println();// 产生换行操作

		// 通过使用ListIterator的set方法来改变li中的元素
		for (String str; li.hasPrevious();) {
			str = li.previous().toString();
			li.set(str.replaceAll("元素", "元素编号"));
		}

		// 顺序输出li迭代器中现有的元素
		while (li.hasNext()) {
			System.out.print(li.next().toString() + " ");
		}

	}

	// 初始化List方法
	public void initial(List<String> list) {
		list.add("元素一");
		list.add("元素二");
		list.add("元素三");
		list.add("元素四");
	}
}
