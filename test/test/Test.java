package test;

public class Test {

	public static void main(String[] args) {
		test("before changed");
	}
	public static void test(String s){
		System.out.println(s);
		s="changed";
		test2(s);
	}
	
	public static void test2(String s){
		System.out.println(s);		
	}
}
