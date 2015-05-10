package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Update extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException { 
		String key =  req.getParameter("key");
		Long longKey = Long.parseLong(key);
		
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String kakaoid = req.getParameter("kakaoid");
		String gitid = req.getParameter("gitid");		
		boolean check = req.getParameter("chk_info") != null;
		
		PersistenceManager pm = MemberManager.dogetManager();
		Member tm =  pm.getObjectById(Member.class,longKey);
		
		tm.setName(name);
		tm.setId(id);
		tm.setEmail(email);
		tm.setKakaoid(kakaoid);
		tm.setGitid(gitid);
		tm.setChk_info(check);
		pm.close();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
	
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<table border=1>");
		resp.getWriter().println("<tr>"+ "<td>" +"�̸�  " +"</td>" +"<td>" + name + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"�й�  " +"</td>" +"<td>" + id + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"�����ּ� : " +"</td>" +"<td>" + email + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"īī���� ���̵� : " +"</td>" +"<td>" + kakaoid + "</td>" + "</tr>");
		if(check != true)
			resp.getWriter().println("<tr>"+ "<td>" +"���忩��" +"</td>" +"<td>" + "����ƴ�" + "</td>" + "</tr>");
		else
			resp.getWriter().println("<tr>"+ "<td>" +"���忩��" +"</td>" +"<td>" + "����" + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"GitHub ID" +"</td>" +"<td>" + gitid + "</td>" + "</tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("<a href=" +"/RetrieveMemberServlet" + ">" + "���� �Ϸ�! �ڷΰ���" + "</a>"+"</br>");
		resp.getWriter().println("<a href=" +"index.html" + ">" + "���� �Ϸ�! ó������" + "</a>"+"</br>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
		
	}
}