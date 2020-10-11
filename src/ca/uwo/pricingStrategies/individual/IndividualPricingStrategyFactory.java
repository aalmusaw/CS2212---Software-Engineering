package ca.uwo.pricingStrategies.individual;

import ca.uwo.pricingStrategies.individual.IndividualPricingStrategy1;
import ca.uwo.pricingStrategies.individual.IndividualPricingStrategy;
import ca.uwo.pricingStrategies.individual.IndividualPricingStrategy2;

public class IndividualPricingStrategyFactory<V> {

	
	/**
	 * create strategy for the total price calculation.
	 * @param type each type is attached to one strategy.
	 * @return one concrete implementation of {@link AggregatePricingStrategy}.
	 */
	public static IndividualPricingStrategy create(String type) {
		switch(type) {
		case "strategy2":
			return new IndividualPricingStrategy2();
		default:
			return new IndividualPricingStrategy1();
		}
		
	}
}
