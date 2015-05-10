package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class Entry extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String c;
		boolean value;
		Cookie[] cookieList = req.getCookies();

		if (cookieList != null) {
			for (int i = 0; i < cookieList.length; i++) {
				 c = cookieList[i].getName();

		value = c.equals("token_id");

		/*if (value == false) {
			resp.sendRedirect("/login.html");
		} else {
			PersistenceManager pm = MemberManager.dogetManager();
			Query qry = pm.newQuery(UserLoginToken.class);
			
			List<UserLoginToken> TokList = (List<UserLoginToken>) qry.execute();
			String userID = TokList.get(0).getUserAccount();
			Long key = TokList.get(0).getKey();
			HttpSession session = req.getSession();
			session.setAttribute("auto_user_id", userID);
			session.setAttribute("auto_login_key", key);
			resp.sendRedirect("/index.html");
		
		}*/
		resp.sendRedirect("/login.html");
	
			}
		}
	}
}
