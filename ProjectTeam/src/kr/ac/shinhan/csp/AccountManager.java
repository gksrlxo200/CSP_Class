package kr.ac.shinhan.csp;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class AccountManager {
	
	public static UserAccount addAccount(String userID, String password, String name) {
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory(
				"transactions-optional").getPersistenceManager();
		
		UserAccount ua = new UserAccount(userID, password, name);
		pm.makePersistent(ua);

		return ua;
	}
	
	
	
	public static List<UserAccount> getAllaccountList() {
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory(
				"transactions-optional").getPersistenceManager();
		
		Query qry = pm.newQuery(UserAccount.class);
		List<UserAccount> accountList = (List<UserAccount>) qry.execute();

		return accountList;
	}

	
	public static List<UserAccount> getAccountById(String id) 
 	{ 
 		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager(); 
 		Query qry = pm.newQuery(UserAccount.class); 
 		qry.setFilter("id == idParam"); 
 		qry.declareParameters("String idParam"); 
 		 
		List<UserAccount> accountList = (List<UserAccount>) qry.execute(id); 
 		 
 		return accountList; 
 	}
 	

}
