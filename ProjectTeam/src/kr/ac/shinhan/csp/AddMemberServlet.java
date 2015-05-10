package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AddMemberServlet extends HttpServlet {
	public void doPost (HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String email =req.getParameter("email");
		String kakaoid =req.getParameter("kakaoid");
		String gitid =req.getParameter("gitid");
		boolean checked = req.getParameter("chk_info") != null;
				
		Member tm = new Member(name, id, email, kakaoid,gitid, checked);
		PersistenceManager pm = MemberManager.dogetManager();
		pm.makePersistent(tm);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<table border="+ "1" + "cellpadding="+ "5" +">");
		
		resp.getWriter().println("<tr><td>이름 :</td><td>"+ name +"</td></tr>");
		resp.getWriter().println("<tr><td>학번 :</td><td>"+id +"</td></tr>");
		resp.getWriter().println("<tr><td>메일주소 :</td><td>"+email +"</td></tr>");
		resp.getWriter().println("<tr><td>카카오톡 ID :</td><td>"+kakaoid +"</td></tr>");
			if(checked == false)
			resp.getWriter().println("<tr><td>팀장여부 : "+ "팀장아님" +"</td></tr>");
			else
			resp.getWriter().println("<tr><td>팀장여부 : "+ "팀장"  +"</td></tr>");
			
		resp.getWriter().println("<tr><td>카카오톡 ID :</td><td>"+gitid +"</td></tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("</form><a href="+"index.html"+">저장 완료 후 뒤로가기</a>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}
