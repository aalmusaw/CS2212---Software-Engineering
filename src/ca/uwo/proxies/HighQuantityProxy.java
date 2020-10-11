package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class HighQuantityProxy extends Proxy {
	
	private static HighQuantityProxy instance;
	
	/**
	 * private constructor for HighQuantityProxy class.
	 */
	private HighQuantityProxy(Proxy next) {
	}
	
	/**
	 * method to implement the singleton design pattern
	 * @return the only instance of HighQuantityProxy
	 */
    public static HighQuantityProxy getInstance() 
    { 
        if (instance == null) 
            instance = new HighQuantityProxy(null);
  
        return instance; 
    } 

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		Facade facade = Facade.getInstance();
			facade.placeOrder(orderDetails, buyer);
	}
	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#restock(java.util.Map, ca.uwo.client.Supplier)
	 */
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = Facade.getInstance();
		facade.restock(restockDetails, supplier);
	}

}
