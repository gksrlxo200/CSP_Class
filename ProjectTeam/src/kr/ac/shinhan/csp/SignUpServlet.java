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
				resp.getWriter().println("<h1>" + "회원가입" + "<h1>");
				resp.getWriter().println("이미 존재하는 아이디 입니다.");
				resp.getWriter().println(
						"<br>  <br>  <a href='signup.html'>회원 가입 화면으로</a>" + "</br>");
				resp.getWriter().println("</body>");
				resp.getWriter().println("</html>");
			}

			else {
				UserAccount ua = AccountManager.addAccount(id, password, name);

				resp.getWriter().println("<html>");
				resp.getWriter().println("<body>");
				resp.getWriter().println("<h1>" + "회원가입" + "<h1>");
				resp.getWriter().println("회원가입이 완료되었습니다.");
				resp.getWriter().println(
						"<br>  <br>  <a href='login.html'>처음으로</a>" + "</br>");
				resp.getWriter().println("</body>");
				resp.getWriter().println("</html>");

			}
		}

}
