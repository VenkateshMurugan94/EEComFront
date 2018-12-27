package Com.niit.EComFront.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Com.niit.EComBack.DAO.UserDAO;
import Com.niit.EComBack.model.UserDetail;


@Controller
public class LoginController {
	
	@Autowired
	UserDAO ud;
	
	@RequestMapping({"/register"})
	public String registerpage(Model m)
	{
		m.addAttribute("registerpage",true);
		m.addAttribute("UserDetail",new UserDetail());
		m.addAttribute("haserror", false);
		m.addAttribute("title","biofresh-register");
		return "index";
	}
	
	@RequestMapping(value="/CreateUser",method=RequestMethod.POST)
	public String CreateUser(@Valid @ModelAttribute("UserDetail")UserDetail userDetail,BindingResult br,Model m)
	{
		if(br.hasErrors())
		{
			
			m.addAttribute("UserDetail",userDetail);
			m.addAttribute("registerpage",true);
			m.addAttribute("haserror",true);
			m.addAttribute("error", "please check ur data");
			m.addAttribute("title","biofresh-register");
			return "index";
		}
		else 
		{
			try
			{
				ud.createUser(userDetail);
				m.addAttribute("title","biofresh-register");
				return "redirect:/register";		
			}
			catch(Exception e)
			{
				
				m.addAttribute("UserDetail",userDetail);
				m.addAttribute("registerpage",true);
				m.addAttribute("haserror", true);
				m.addAttribute("error", "Already Data Present");
				m.addAttribute("title","biofresh-register");
				return "index";
			}
		}
		
		
	}

}
