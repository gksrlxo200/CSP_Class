package kr.ac.shinhan.csp;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class Logout extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		req.setCharacterEncoding("EUC-KR");
		resp.setContentType("text/plain;charset=EUC-KR");

		Long key = null;
		HttpSession s = req.getSession(false);

		if (s == null) {
			resp.getWriter().println("");
		}
		
		
		else 
		{
			key = (Long) s.getAttribute("check_key");
			if (key != null) 
			{

				Cookie[] cookieList = req.getCookies();

				for (Cookie c : cookieList) 
				{
					if (c.getName().equals("token_id")) 
					{
						c.setValue(null);
						c.setMaxAge(0);
						resp.addCookie(c);
						
					}
				}

				PersistenceManager pm = MemberManager.dogetManager();
				UserLoginToken usertok = pm.getObjectById(UserLoginToken.class, key);
				pm.deletePersistent(usertok);

				/*session.setMaxInactiveInterval(0);
				session.invalidate();*/

			}

		}

		resp.sendRedirect("/login.html");
	}
}
