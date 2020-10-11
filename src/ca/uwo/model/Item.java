package ca.uwo.model;

import java.util.ArrayList;
import java.util.List;

import ca.uwo.model.item.states.InStockState;
import ca.uwo.model.item.states.ItemState;
import ca.uwo.model.item.states.ItemStateFactory;
import ca.uwo.model.item.states.LowStockState;
import ca.uwo.model.item.states.OutOfStockState;
import ca.uwo.utils.ItemResult;
import ca.uwo.viewer.Messenger;
import ca.uwo.viewer.StockManager;
import ca.uwo.viewer.Viewer;

/**
 * @author kkontog, ktsiouni, mgrigori This class represents the data objects
 *         (instances of Item class) in the database.
 */
public class Item {
	private int id;
	private String name;
	private int availableQuantity;
	private double price;
	// This is the attribute that references the object's state
	private ItemState state;
	private List<Viewer> viewers;

	/**
	 * constructor for the Item class.
	 * 
	 * @param id
	 *            identifier of the item.
	 * @param name
	 *            name of the item.
	 * @param quantity
	 *            quantity of the item.
	 * @param price
	 *            price of the item.
	 */
	public Item(int id, String name, int quantity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.availableQuantity = quantity;
		this.price = price;
		this.viewers = new ArrayList<Viewer>();
		
		// Adding viewers thus implementing part of the Observer design pattern
		this.viewers.add(StockManager.getInstance());
		this.viewers.add(Messenger.getInstance());

		// When you add states to items make sure you
		// initialize them using the proper STATE!!!!
		if (quantity == 0) {
			state = ItemStateFactory.create("Out Of Stock");
		}
		else if (quantity < 10) {
			state = ItemStateFactory.create("Low Stock");
		}
		else state = ItemStateFactory.create("In Stock");

	}

	/**
	 * @return id of the item.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return name of the item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return available quantity of the item.
	 */
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity
	 *            available quantity of the item in stock.
	 */
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	/**
	 * @return price of the item.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * @return the state of the item
	 */
	public ItemState getState() {
		return state;
	}
	
	/**
	 * A setter method to set the state of the current object
	 * @param s the new state of the object
	 */
	public void setState(ItemState s) {
		this.state = s;
	}
	
	

	/**
	 * deplete the item.
	 * this method may change the state attribute depending on the
	 * available quantity left.
	 * 
	 * @param quantity
	 *            the quantity of item to deplete.
	 * @return execution result of the deplete action.
	 */
	public ItemResult deplete(int quantity) {
		// Deplete the item with quantity and return the execution result of
		// deplete depending on item state
		ItemResult itemResult = this.state.deplete(this, quantity);
		this.setAvailableQuantity(availableQuantity);
		if  (availableQuantity == 0) {
			setState(ItemStateFactory.create("Out of Stock"));
		}
		else if (availableQuantity < 10) {
			this.setState(ItemStateFactory.create("Low Stock"));
		}
		return itemResult;
	}

	/**
	 * replenish the item.
	 * this method may change the state attribute depending on the
	 * available quantity left.
	 * 
	 * @param quantity
	 *            the quantity of item to replenish.
	 * @return execution result of the replenish action.
	 */
	public ItemResult replenish(int quantity) {
		// Replenish the item with quantity and return the execution result of
		// replenish action.
		ItemResult itemResult = this.state.replenish(this, quantity);
		if (availableQuantity > 10) this.setState(ItemStateFactory.create("In Stock"));
		else if (availableQuantity > 0) {
			this.setState(ItemStateFactory.create("Low Stock"));
		}
		else {
			this.setState(ItemStateFactory.create("Out of Stock"));
		}
		return itemResult;
	}

	public void notifyViewers() {
		for(int i = 0; i < viewers.size(); i++){
			viewers.get(i).inform(this);
		}
	}

}
