package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class RetrieveMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PersistenceManager pm = MemberManager.dogetManager();
		List<Member> memberList = MemberManager.getAllMembers();
		Query qry = pm.newQuery(Member.class);
		
		
		
		
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");

		String getUserID = null;

		HttpSession session = req.getSession(false);
		if (session == null)
			resp.getWriter().println("");
		else {
			getUserID = (String) session.getAttribute("s_id");
			if (getUserID != null) {
				resp.getWriter().println(getUserID + "님 환영합니다.");
			}

			resp.getWriter().println("<HTML>");
			resp.getWriter().println("<body>");

			resp.getWriter().println("<table border = 1>");

			resp.getWriter().println("<tr>");
			resp.getWriter().println("<th>" + "이름" + "</th>");
			resp.getWriter().println("<th>" + "Id" + "</th>");
			resp.getWriter().println("<th>" + "Mail" + "</th>");
			resp.getWriter().println("<th>" + "KaKao Id" + "</th>");
			resp.getWriter().println("<th>" + "팀장여부" + "</th>");
			resp.getWriter().println("<th>" + "Git ID" + "</th>");
			resp.getWriter().println("<th>" + "삭제여부" + "</th>");
			resp.getWriter().println("</tr>");

			for (Member tm : memberList) {
				if (tm.getKey() == null)
					resp.getWriter().println("<h1>회원 정보가 없습니다!<h1>");
				else {
					resp.getWriter().println("<tr>");
					resp.getWriter().println(
							"<td>" + "<a href =/ReadMemberServlet?key="
									+ tm.getKey() + ">" + tm.getName()
									+ "</td>");
					resp.getWriter().println("<td>" + tm.getId() + "</td>");
					resp.getWriter().println("<td>" + tm.getEmail() + "</td>");
					resp.getWriter()
							.println("<td>" + tm.getKakaoid() + "</td>");
					if (tm.isChkinfo() == true)
						resp.getWriter().println("<td>" + "팀장" + "</td>");
					else
						resp.getWriter().println("<td>" + "팀장 아님" + "</td>");
					resp.getWriter().println("<td>" + tm.getGitid() + "</td>");

					resp.getWriter().println(
							"<td>" + "<a href =/delete?key=" + tm.getKey()
									+ ">" + "삭제" + "</td>");
				}
			}
			resp.getWriter().println("</table>");
			resp.getWriter().println("</body>");
			resp.getWriter().println("</HTML>");

		}

	}

}
