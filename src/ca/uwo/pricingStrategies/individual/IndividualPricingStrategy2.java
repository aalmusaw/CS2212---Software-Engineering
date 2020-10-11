package ca.uwo.pricingStrategies.individual;

public class IndividualPricingStrategy2 implements IndividualPricingStrategy {

	/**
	 * A 10% discount method
	 * @param price the price per unit
	 * @param quantity the quantity ordered
	 * @return 90% of the total price per total quantity
	 */
	public double calculate(int quantity, double price) {
		return 0.9*price*quantity;
	}

}
