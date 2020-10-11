package ca.uwo.proxies;
import java.io.IOException;
import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class SupplierProxy extends Proxy{
	
	private static SupplierProxy instance;
	
	/**
	 * private constructor for SupplierProxy class.
	 */
	private SupplierProxy(Proxy next) {
		this.next = next;
	}
	
	/**
	 * method to implement the singleton design pattern
	 * @return the only instance of SupplierProxy
	 */
    public static Proxy getInstance() 
    { 
        if (instance == null) 
            instance = new SupplierProxy(LowQuantityProxy.getInstance());
  
        return instance; 
    } 
	
	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		next.placeOrder(orderDetails, buyer);
		
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
