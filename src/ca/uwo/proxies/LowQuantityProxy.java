package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class LowQuantityProxy extends Proxy {
	
	private static LowQuantityProxy instance = null;
	
	/**
	 * private constructor for LowQuantityProxy class.
	 */
	private LowQuantityProxy(Proxy next) {
		this.next = next;
	}
	
	/**
	 * method to implement the singleton design pattern
	 * @return the only instance of LowQuantityProxy
	 */
    public static LowQuantityProxy getInstance() 
    { 
        if (instance == null) 
            instance = new LowQuantityProxy(HighQuantityProxy.getInstance());
  
        return instance; 
    } 

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		Object quantities[] = orderDetails.values().toArray();
		int totalQuantity = 0;
		for (int i = 0; i < quantities.length; i++) {
			totalQuantity = totalQuantity + (int) quantities[i];
		}
		if (totalQuantity <= 10) {
			Facade facade = Facade.getInstance();
			facade.placeOrder(orderDetails, buyer);
		}
		else {
			next.placeOrder(orderDetails, buyer);
		}
		
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#restock(java.util.Map, ca.uwo.client.Supplier)
	 */
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		System.out.println("This method is unavailable at the moment.");
	}

}
