package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Prints all constructors of a class
	 */
	public static void printConstructor(Class cl) {
		Constructor[] constructors = cl.getConstructors();
		for (Constructor c : constructors) {
			String name = c.getName();
			System.out.print("   ");
			String modifiers = Modifier.toString(c.getModifiers());
			if (modifiers.length() > 0)
				System.out.print(modifiers + " ");
			System.out.println(name + "(");

			// print parameter types
			Class[] paramTypes = c.getParameterTypes();
			for (int j = 0; j < paramTypes.length; j++) {
				if (j > 0)
					System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}

	/*
	 * Prints all methods of a class
	 */
	public static void printMethod(Class cl) {
		Method[] methods = cl.getDeclaredMethods();
		for (Method m : methods) {
			Class retType = m.getReturnType();
			String name = m.getName();
			System.out.print("   ");
			// print modifier,return type and method name
			String modifiers = Modifier.toString(m.getModifiers());
			if (modifiers.length() > 0)
				System.out.print(modifiers + " ");
			System.out.print(retType.getName() + " " + name + "(");

			// print parameter types
			Class[] paramTypes = m.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0)
					System.out.print(", ");
				System.out.print(paramTypes[i].getName());
			}
			System.out.println(");");

		}
	}

	/**
	 * Prints all fields of a class
	 */
	public static void printFields(Class cl) {
		Field[] fields = cl.getDeclaredFields();
		for (Field f : fields) {
			Class type = f.getType();
			String name = f.getName();
			System.out.print("   ");
			String modifiers = Modifier.toString(f.getModifiers());
			if (modifiers.length() > 0)
				System.out.print(modifiers + " ");
			System.out.println(type.getName() + " " + name + ";");
		}
	}
}