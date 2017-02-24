package chapter4.param;

import static java.lang.System.out;

public class ParamTest {
	public static void main(String[] args) {
		// test 1: Method can't modify numeric parameters
		out.println("Testing tripleValue:");
		double percent = 10;
		out.println("Before:percent=" + percent);
		tripleValue(percent);
		out.println("After: percent=" + percent);

		// test 2: Methods can change the state of object parameters
		out.println("\nTesting tripleSalary:");
		Employee harry = new Employee("Harry", 50000);
		out.println("Before :salary=" + harry.getSalary());
		tripleSalary(harry);
		out.println("After: salary=" + harry.getSalary());

		// test 3: Methods can't attach new objects to object parameters
		out.println("\nTesting swap:");
		Employee a = new Employee("Alice", 70000);
		Employee b = new Employee("Bob", 60000);
		out.println("Before: a=" + a.getName());
		out.println("Before: b=" + b.getName());
		swap(a, b);
		out.println("After: a=" + a.getName());
		out.println("After: b=" + b.getName());
	}

	public static void tripleValue(double x)// doesn't work
	{
		x = 3 * x;
		System.out.println("End of method:x=" + x);
	}

	public static void tripleSalary(Employee x)// works
	{
		x.raiseSalary(200);
		System.out.println("end of method:salary=" + x.getSalary());
	}

	public static void swap(Employee x, Employee y) {
		Employee temp = x;
		x = y;
		y = temp;
		System.out.println("End of method:x=" + x.getName());
		System.out.println("End of method:y=" + y.getName());
	}
}

class Employee {
	private String name;
	private double salary;

	public Employee(String name, double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}