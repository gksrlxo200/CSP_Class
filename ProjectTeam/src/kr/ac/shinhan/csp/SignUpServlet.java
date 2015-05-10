package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SignUpServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		boolean ck = false;
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		
		
		PersistenceManager pm = MemberManager.dogetManager();
		List<UserAccount> userlist = AccountManager.getAllaccountList();
		Query qry = pm.newQuery(UserAccount.class);

		
		
		for (UserAccount uat : userlist) {
			if (id.equals(uat.getUserID()))
				ck = true;
		}

			
			
			if (ck == true) {
				resp.getWriter().println("<html>");
				resp.getWriter().println("<body>");
				resp.getWriter().println("<h1>" + "ȸ������" + "<h1>");
				resp.getWriter().println("�̹� �����ϴ� ���̵� �Դϴ�.");
				resp.getWriter().println(
						"<br>  <br>  <a href='signup.html'>ȸ�� ���� ȭ������</a>" + "</br>");
				resp.getWriter().println("</body>");
				resp.getWriter().println("</html>");
			}

			else {
				UserAccount ua = AccountManager.addAccount(id, password, name);

				resp.getWriter().println("<html>");
				resp.getWriter().println("<body>");
				resp.getWriter().println("<h1>" + "ȸ������" + "<h1>");
				resp.getWriter().println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
				resp.getWriter().println(
						"<br>  <br>  <a href='login.html'>ó������</a>" + "</br>");
				resp.getWriter().println("</body>");
				resp.getWriter().println("</html>");

			}
		}

}
