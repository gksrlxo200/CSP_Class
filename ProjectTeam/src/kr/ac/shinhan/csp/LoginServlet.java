package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String token = null;
		String expireData = null;
		
		
		
		boolean checked = req.getParameter("remember") != null;
		boolean success = false;



		Query qry = MemberManager.dogetManager().newQuery(UserAccount.class);
		
		qry.setFilter("userID == idParam");
		qry.declareParameters("String idParam");
		

		List<UserAccount> userAccount = (List<UserAccount>) qry.execute(id);
		
		
		
		if (userAccount.size() == 0) {
			success = false;
		}

		else if (userAccount.get(0).getPassword().equals(password)) {
			success = true;
		}

		else {
			success = false;
		}

		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");

		if (!success) {
			resp.getWriter().println("Fail to login");
			resp.getWriter().println("<a href='login.html'>Login Again</a>");
		}

		
		
		if (success) {
			HttpSession s = req.getSession();
			s.setAttribute("s_id",id);
			if(checked == true)
			{
				token = UUID.randomUUID().toString();
				
				
				Cookie cookie = new Cookie("token_id", token);
				cookie.setMaxAge(60 * 24 * 30 * 60);
				resp.addCookie(cookie);
				
				//Calendar cal = Calendar.getInstance();
				//String ntime = new String();
				
				//현재 년도, 월, 일
				
				/*ntime = String.valueOf(cal.get(Calendar.YEAR));
				ntime += String.valueOf(cal.get(Calendar.MONTH)+1);
				ntime += String.valueOf(cal.get(Calendar.DATE)+ 30);*/
				
				
				
				expireData =  Integer.toString(cookie.getMaxAge());	
				UserLoginToken usertoken = new UserLoginToken(token, id, expireData);
				PersistenceManager pm = MemberManager.dogetManager();
				pm.makePersistent(usertoken);
				
				
				
		
				
			}
			
			
			resp.sendRedirect("/index.html");
		}

		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");

	}

}
