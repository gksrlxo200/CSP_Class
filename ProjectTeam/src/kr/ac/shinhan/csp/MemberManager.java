package kr.ac.shinhan.csp;

import java.util.List; 

 
 import javax.jdo.JDOHelper; 
 import javax.jdo.PersistenceManager; 
 import javax.jdo.Query; 
 
 
 public class MemberManager { 
 	 
 	public static Member addMember(String name,String id,String email,String kakaoid,String gitid, boolean checked) 
 	{ 
 		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager(); 
 		Member m = new Member(name,id,email,kakaoid,gitid, checked); 
 		pm.makePersistent(m); 
		 
 		return m; 
 	} 
 	 
 	public static Member getMember(String key) 
 	{ 
 		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager(); 
 		Member m = pm.getObjectById(Member.class,key); 
 		 
 		return m; 
 	} 
 	 
 	public static void deleteMember(String key) 
 	{ 
 		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager(); 
 		Member m = MemberManager.getMember(key); 
		pm.deletePersistent(m); 
 	} 	 
 	public static List<Member> getMemberByName(String name) 
 	{ 
 		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager(); 
 		Query qry = pm.newQuery(Member.class); 
 		qry.setFilter("name == nameParam"); 
 		qry.declareParameters("String nameParam"); 
 		 
		List<Member> memberList = (List<Member>) qry.execute(name); 
 		 
 		return memberList; 
 	}
 	
 	 
 	public static List<Member> getAllMembers() 
 	{ 
 		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager(); 
 		Query qry = pm.newQuery(Member.class); 
 		List<Member> memberList = (List<Member>) qry.execute(); 
 
 
 		return memberList; 
 	} 
 	public static PersistenceManager dogetManager()
 	{
 		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
 		return pm;
 	}
 } 
