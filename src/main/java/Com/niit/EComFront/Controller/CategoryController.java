package Com.niit.EComFront.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Com.niit.EComBack.DAO.CategoryDAO;
import Com.niit.EComBack.model.Category;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryDAO cd;
	
	@RequestMapping("/admin/category")
	public String categorypage(Model m)
	{
		m.addAttribute("editmode",false);
		m.addAttribute("category", new Category());
		m.addAttribute("categorypage",true);
		m.addAttribute("haserror", false);
		m.addAttribute("catlist",cd.ViewAllCategory());
		m.addAttribute("title","biofresh-category");
		return "index";
	}

	
	@RequestMapping(value="/admin/CreateCategory",method=RequestMethod.POST)
	public String CreateCategory(@Valid @ModelAttribute("category")Category category,BindingResult br,Model m)
	{
		if(br.hasErrors())
		{
			m.addAttribute("editmode",false);
			System.out.println("erroer");
			m.addAttribute("category",category);
			m.addAttribute("catlist", cd.ViewAllCategory());
			m.addAttribute("categorypage",true);
			m.addAttribute("haserror",true);
			m.addAttribute("error", "please check ur data");
			m.addAttribute("title","biofresh-category");
			return "index";
		}
		else 
		{
			try
			{
				cd.CreateCategory(category);
				m.addAttribute("title","biofresh-category");
				return "redirect:/admin/category";		
			}
			catch(Exception e)
			{
				m.addAttribute("editmode",false);
				m.addAttribute("category",category);
				m.addAttribute("catlist", cd.ViewAllCategory());
				m.addAttribute("categorypage",true);
				m.addAttribute("haserror", true);
				m.addAttribute("error", "Already Data Present");
				m.addAttribute("title","biofresh-category");
				return "index";
			}
		}
		
		
	}
	
	@RequestMapping("/admin/deletecategory")
	public String deletecategory(@RequestParam("catname")String catname,Model m)
	{
		Category c=cd.ViewOneCatogory(catname);
		cd.DeleteCategory(c);
		m.addAttribute("title","biofresh-category");
		return "redirect:/admin/category";
	}
	
	@RequestMapping("/admin/editcategory")
	public String editcategory(@RequestParam("catname")String catname,Model m)
	{
		m.addAttribute("editmode",true);
		m.addAttribute("category",cd.ViewOneCatogory(catname));
		m.addAttribute("categorypage",true);
		m.addAttribute("haserror", false);
		m.addAttribute("catlist",cd.ViewAllCategory());
		m.addAttribute("title","biofresh-category");
		return "index";
	}
	
	@RequestMapping(value="/admin/UpdateCategory",method=RequestMethod.POST)
	public String UpdateCategory(@Valid @ModelAttribute("category")Category category,BindingResult br,Model m)
	{
		if(br.hasErrors())
		{
			m.addAttribute("editmode",false);
			System.out.println("erroer");
			m.addAttribute("category",category);
			m.addAttribute("catlist", cd.ViewAllCategory());
			m.addAttribute("categorypage",true);
			m.addAttribute("haserror",true);
			m.addAttribute("error", "please check ur data");
			m.addAttribute("title","biofresh-category");
			return "index";
		}
		else 
		{
			try
			{
				cd.UpdateCategory(category);
				m.addAttribute("title","biofresh-category");
				return "redirect:/admin/category";		
			}
			catch(Exception e)
			{
				m.addAttribute("editmode",false);
				m.addAttribute("category",category);
				m.addAttribute("catlist", cd.ViewAllCategory());
				m.addAttribute("categorypage",true);
				m.addAttribute("haserror", true);
				m.addAttribute("error", "Already Data Present");
				m.addAttribute("title","biofresh-category");
				return "index";
			}
		}
		
		
	}

}
