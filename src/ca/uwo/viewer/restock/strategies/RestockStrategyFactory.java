package ca.uwo.viewer.restock.strategies;

import ca.uwo.viewer.restock.strategies.RestockStrategy;
import ca.uwo.viewer.restock.strategies.RestockStrategy1;
import ca.uwo.viewer.restock.strategies.RestockStrategy2;

public class RestockStrategyFactory {
	
	public static RestockStrategy create(String type) {
		String newtype = type.toLowerCase();
		newtype = type.replaceAll("\\s+","");
		switch(newtype) {
		case "strategy1":
			return new RestockStrategy1();
		case "strategy2":
			return new RestockStrategy2();
		default:
			return new RestockStrategy1();
		}
	}

}
