package kr.ac.shinhan.csp;

import java.io.IOException;
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
public class Entry extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		
		String token = null;
		Cookie[] cookies = req.getCookies();
		Query qry2 = MemberManager.dogetManager()
				.newQuery(UserLoginToken.class);

		List<UserLoginToken> ulog = (List<UserLoginToken>) qry2.execute();
		qry2.setFilter("userid == idParam");
		qry2.declareParameters("String idParam");

		UserLoginToken ulog2 = ulog.get(0);
		String tokid = ulog2.getUserid();

		if (tokid == null) {
			resp.sendRedirect("/login.html");
		} else {
			PersistenceManager pm = MemberManager.dogetManager();
			Query qry = pm.newQuery(UserLoginToken.class);
			
			token = UUID.randomUUID().toString();
			ulog2.setToken(token);
			resp.sendRedirect("/index.html");

		}

	}
}
