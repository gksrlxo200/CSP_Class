package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

		String id = null;
		String token = null;
		boolean success = false;
		
		
		Query qry2 = MemberManager.dogetManager()
				.newQuery(UserLoginToken.class);
		List<UserLoginToken> ulog = (List<UserLoginToken>) qry2.execute();
		
		
		Cookie[] cookies = req.getCookies();
		//resp.sendRedirect("/login.html");
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		if (cookies != null) {
			for (Cookie c : cookies) 
			{
				if (c.getName().equals("token_id")) 
				{
					token = c.getValue();
				}					
			}					
			
			for (UserLoginToken ult : ulog)
			{
				if (ult.getToken().equals(token)) 
				{
					id = ult.getUserid();	
					token = UUID.randomUUID().toString();
					ult.setToken(token);
					Cookie cookieToken = new Cookie("token_id", token);
					resp.addCookie(cookieToken);
					success = true;	
				}
			}
		}
		else {
			resp.sendRedirect("/login.html");
		}
		if (success)
		{
			HttpSession session = req.getSession();
			session.setAttribute("s_id",id);
			resp.sendRedirect("/index.html");
		}
		resp.getWriter().println("</html>");
		resp.getWriter().println("</body>");
	}	
}
