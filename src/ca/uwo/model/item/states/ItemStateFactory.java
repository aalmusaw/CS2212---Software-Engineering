package ca.uwo.model.item.states;

public class ItemStateFactory {
	/**
	 * A factory method to create a new state
	 * @param type the type of the state, i.e. OutOfStock, LowStock, etc
	 * @return a new instance of the specified ItemState
	 */
	public static ItemState create(String type) {
		String newtype = type.toLowerCase();
		newtype = newtype.replaceAll("\\s+","");
		switch(newtype) {
		case "outofstock":
			return new OutOfStockState();
		case "lowstock":
			return new LowStockState();
		default:
			return new InStockState();
		}
	}
}
