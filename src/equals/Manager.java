package equals;

import java.time.LocalDate;

public class Manager extends Employee {
	private double bonus;

	public Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		this.bonus = 0;
	}

	@Override
	public double getSalary() {
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public double getBonus(){
		return bonus;
	}

	@Override
	public int hashCode() {

		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Manager other = (Manager) obj;
		// super.equals checked that this and other belong to the same class
		return bonus == other.bonus;
	}

	@Override
	public String toString() {

		return super.toString() + "[bonus=" + bonus + "]";
	}

}
