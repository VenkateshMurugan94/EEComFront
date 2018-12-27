package Com.niit.EComFront.Controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Com.niit.EComBack.DAO.UserDAO;
import Com.niit.EComBack.model.UserDetail;

@Controller
public class HomeController {
	
	@Autowired
	JavaMailSender jms;
	
	@Autowired
	UserDAO ud;
	
	@RequestMapping({"/","/index"})
	public String carouselpage(Model m)
	{
		m.addAttribute("carouselpage",true);
		m.addAttribute("title","biofresh-home");
		return "index";
	}
	
	@RequestMapping({"/aboutus"})
	public String aboutusPage(Model m)
	{
		m.addAttribute("aboutuspage",true);
		m.addAttribute("title","biofresh-aboutus");
		return "index";
	}
	
	@RequestMapping({"/contactus"})
	public String contactuspage(Model m)
	{
		m.addAttribute("contactuspage",true);
		m.addAttribute("title","biofresh-contactus");
		return "index";
	}
	
	@RequestMapping({"/Login"})
	public String loginpage(Model m)
	{
		m.addAttribute("loginpage",true);
		m.addAttribute("error", false);
		m.addAttribute("message","");
		m.addAttribute("title","biofresh-login");
		return "index";
	}
	
	@RequestMapping({"/flogin"}) 
	public String floginpage(Model m)
	{
		m.addAttribute("loginpage",true);
		m.addAttribute("error", true);
		m.addAttribute("message","User Name & Password incorrect");
		m.addAttribute("title","biofresh-login");
		return "index";
	}
	
	@RequestMapping("/loginsuccess")
	public String isLoggedin(HttpSession session) 
	{
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority authority : authorities) 
		{
			
			if (authority.getAuthority().equals("ROLE_USER")) 
			{
				
				UserDetail customer = ud.userDetail(email);
				session.setAttribute("useremail",customer.getEmailId());
				session.setAttribute("usercartid",customer.getCartId());
				session.setAttribute("username",customer.getUserName());
				session.setAttribute("userlogin",true);
				if(session.getAttribute("proid")!=null)
				{
					return "redirect:/addToCart?pid="+Integer.parseInt(session.getAttribute("proid").toString());
				}
				else
				{
					return "redirect:/";
				}
			} 
			else 
			{
				System.out.println("error");
				session.setAttribute("username","Administrator");
				session.setAttribute("userlogin", false);
				return "redirect:/";
			}
		}
		return "redirect:/";
		
	}
	
	
	@RequestMapping(value = "sendmail")
	public String sendmail(HttpServletRequest request) {
		try {
			String recipientAddress = "biofresh94@gmail.com";
			String uname = request.getParameter("name");
			String uemail = request.getParameter("e-mail");
			String uphno = request.getParameter("phone");
			String umessage = request.getParameter("text");
			String finalmessage = "Hi Admin, " + umessage + " Contact me in:" + uphno + "\n\n\n regards\n\n" + uname;
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(recipientAddress);
			email.setSubject("New Request from"+uname);
			email.setText(finalmessage);
			jms.send(email);
			
			
			String finalmessage1 = "Thank you \n\n Regards\nAdmin\nBioFresh";
			SimpleMailMessage email1 = new SimpleMailMessage();
			email1.setTo(uemail);
			email1.setSubject(uemail);
			email1.setText(finalmessage1);
			jms.send(email1);
			
		} 
		catch (Exception e) {

		}
		return "redirect:/contactus";
	}
	
}
