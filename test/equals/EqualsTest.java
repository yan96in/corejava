package equals;

public class EqualsTest {
	public static void main(String[] args) {
		Employee alice1 = new Employee("Alice Adams", 75000, 1987, 12, 15);
		Employee alice2=alice1;
		Employee alice3 = new Employee("Alice Adams", 75000, 1987, 12, 15);
		Employee bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);
		System.out.println("alice1==alice2:" + (alice1 == alice2));
		System.out.println("alice1==alice3:" + (alice1 == alice3));
		System.out.println("alice1.equals(alice3):" + alice1.equals(alice3));
		System.out.println("alice1.equals(bob):" + alice1.equals(bob));
	}
}
