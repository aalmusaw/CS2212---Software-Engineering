package ca.uwo.proxies;

import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This is one concrete implementation of {@link ca.uwo.proxies.Proxy} base class, it is the first proxy
 * the {@link ca.uwo.client.Client} will encounter. If the request of client is not issued by this class, 
 * it is forwarded to the {@link ca.uwo.proxies.SupplierProxy}, then {@link ca.uwo.proxies.LowQuantityProxy}, 
 * lastly {@link ca.uwo.proxies.HighQuantityProxy}. The link between those proxies implements Chain of Responsibility 
 * design pattern.
 */
public class WelcomeProxy extends Proxy {
	
	private static WelcomeProxy instance;
	private ArrayList<String[]> buyerDB;
	
	/**
	 * private constructor for WelcomeProxy class.
	 */
	private WelcomeProxy(Proxy next) {
		this.next = next;
		buyerDB  = new ArrayList<String[]>();
		loadDataBase();
	}
	
	/**
	 * method to implement the singleton design pattern
	 * @return the only instance of WelcomeProxy
	 */
    public static WelcomeProxy getInstance() 
    { 
        if (instance == null) 
            instance = new WelcomeProxy(SupplierProxy.getInstance()); 
  
        return instance; 
    } 
    
    /**
     * This function reads from our User Database File buyer_file. 
     */
    private void loadDataBase() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("buyer_file")));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineTokens = line.split("\t");
				String[] UsernamePass = {lineTokens[1], lineTokens[2]};
				buyerDB.add(UsernamePass);
			}
			br.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
    }
	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer)  {

		if(authenticate(buyer)){
			System.out.println("Authentication Succeeded. Placing Order...");
			next.placeOrder(orderDetails, buyer);
		}
		else {
			System.out.println("Authentication Failed. Order will not be placed.");
		}
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#restock(java.util.Map, ca.uwo.client.Supplier)
	 */
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		next.restock(restockDetails, supplier);
	}
	
	/**
	 * This method checks if the buyer passed exists in our database.
	 * @param buyer
	 * @return true if the buyer exists in the database, and false otherwise.
	 */
	private boolean authenticate(Buyer buyer) {
		String buyerName = buyer.getUserName();
		String buyerPassword = buyer.getPassword();
		for (int i = 0; i < buyerDB.size(); i++) {
			if (buyerDB.get(i)[0].equals(buyerName)) {
				if (buyerDB.get(i)[1].equals(buyerPassword)) return true;
			}
		}
		return false;
		
	}
}