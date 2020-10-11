package ca.uwo.pricingStrategies.individual;

public class IndividualPricingStrategy1 implements IndividualPricingStrategy {

	/**
	 * A method to calculate total price per ordered quantity
	 * @param price the price per unit
	 * @param quantity the quantity ordered
	 * @return total price per total quantity
	 */
	public double calculate(int quantity, double price) {
		return price*quantity;
	}

}
