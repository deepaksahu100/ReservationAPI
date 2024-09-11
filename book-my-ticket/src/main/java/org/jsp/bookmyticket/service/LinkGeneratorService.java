package org.jsp.bookmyticket.service;

import org.jsp.bookmyticket.dao.AdminDao;
import org.jsp.bookmyticket.dao.UserDao;
import org.jsp.bookmyticket.model.Admin;
import org.jsp.bookmyticket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.jsp.bookmyticket.util.ApplicationConstants.ADMIN_VERIFY_LINK;
import static org.jsp.bookmyticket.util.ApplicationConstants.USER_VERIFY_LINK;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class LinkGeneratorService {
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserDao userDao;
	
	public String getActivationLink(Admin admin, HttpServletRequest request) {
		admin.setToken(RandomString.make(45));
		adminDao.saveAdmin(admin);
		String siteUrl = request.getRequestURL().toString();
		return siteUrl.replace(request.getServletPath(), ADMIN_VERIFY_LINK + admin.getToken());
	}
	
	public String getUserActivationLink(User user, HttpServletRequest request) {
		user.setToken(RandomString.make(45));
		userDao.saveUser(user);
		String siteUrl = request.getRequestURL().toString();
		return siteUrl.replace(request.getServletPath(), USER_VERIFY_LINK + user.getToken());
	}
}
