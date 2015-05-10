package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Delete extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException { 
		
		String key =  req.getParameter("key");
		Long longKey = Long.parseLong(key);
		
		PersistenceManager pm = MemberManager.dogetManager();
		Member tm = pm.getObjectById(Member.class,longKey);
		pm.deletePersistent(tm);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		
		resp.getWriter().println("<a href=" +"/RetrieveMemberServlet" + ">" + "삭제 완료! 뒤로가기" + "</a>"+"</br>");
		resp.getWriter().println("<a href=" +"index.html" + ">" + "삭제 완료! 처음으로" + "</a>"+"</br>");
	
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
		
	}
}
