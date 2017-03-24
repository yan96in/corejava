package map;

import java.util.HashMap;
import java.util.Map;

public class MapSet {
	public static void main(String[] args) {
		Map<String, String> staff = new HashMap<>();
		staff.put("144-25-5464", new String("Amy Lee"));
		staff.put("143-25-5464", new String("Harry Hacker"));
		staff.put("145-25-5464", new String("Gary Cooper"));
		staff.put("146-25-5464", new String("Francesca Cruz"));

		// print all entries
		System.out.println(staff);

		// remove an entry
		staff.remove("146-25-5464");

		// replace an entry
		staff.put("145-25-5464", "France Miller");

		// look up a value
		System.out.println(staff.get("144-25-5464"));

		// iterate through all entries
		staff.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
	}
}
