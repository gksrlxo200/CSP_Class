package kr.ac.shinhan.csp;

import java.io.IOException; 
import javax.jdo.PersistenceManager;
import javax.servlet.http.*; 
 
 
 @SuppressWarnings("serial")
public class ReadMemberServlet extends HttpServlet{ 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
 			throws IOException { 
		
 		String key = req.getParameter("key"); 
 		Long longkey = Long.parseLong(key);
 		PersistenceManager pm = MemberManager.dogetManager();
 		
 		Member tm = pm.getObjectById(Member.class, longkey);
 		
 		String name = tm.getName();
		String id = tm.getId();
		String email = tm.getEmail();
		String kakaoid = tm.getKakaoid();
		String gitid = tm.getGitid();
		boolean checkid = tm.isChkinfo();
 		
 		 
 		resp.setCharacterEncoding("UTF-8"); 
 		resp.setContentType("text/plain");  
 		 
 		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<form method ="+"'POST'" + "action =/update?key="+ tm.getKey() +">");
		resp.getWriter().println("<table border=" + "1"+">");
		
		resp.getWriter().println("<tr>"+"<td>"+"이름"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'name'" + "value="+name+  ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"Id"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'id'" +  "value="+id+ ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"Email"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'email'" + "value="+email+  ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"KaKao Id"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'kakaoid'" + "value="+kakaoid+  ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"팀장여부"+"</td>"+"<td>"+"<input type ="+"'checkbox'" +"name="+ "'chk_info'" + "value="+checkid + ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"GitHub Id"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'gitid'" + "value="+gitid+  ">" + "</td>"+"</tr>");
		
		
		
		
		resp.getWriter().println("</table>");
		resp.getWriter().println("<input type="+"'submit'" + "value="+"'update'"+  ">");
		resp.getWriter().println("</form>");
		
		resp.getWriter().println("</form><a href="+"/RetrieveMemberServlet"+">이전으로</a>" +"        "+"<a href="+"index.html"+">처음으로</a>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
		
		
		 
 	} 
 
 
} 
