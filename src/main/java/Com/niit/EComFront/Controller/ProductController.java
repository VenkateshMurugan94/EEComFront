 package Com.niit.EComFront.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import Com.niit.EComBack.DAO.CategoryDAO;
import Com.niit.EComBack.DAO.ProductDAO;
import Com.niit.EComBack.model.Category;
import Com.niit.EComBack.model.Product;

@Controller
public class ProductController {
	
	@Autowired
	ProductDAO pd; 
	
	@Autowired
	CategoryDAO cd;
	
	void addimage(MultipartFile f, int id) {
		try {
			String path = "G:\\workspace\\EComFront\\src\\main\\webapp\\resources\\productImages\\";
			path = path + String.valueOf(id) + ".jpg";
			if (!f.isEmpty()) {
				byte[] imagebytes = f.getBytes();
				File x = new File(path);
				if (x.exists()) {
					x.delete();
					BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(x));
					bs.write(imagebytes);
					bs.close();
				} else {
					BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(x));
					bs.write(imagebytes);
					bs.close();
				}
			}
			Thread.sleep(5000);
		} catch (Exception e) {

		}	
}
	
	@RequestMapping({"/admin/product"})
	public String productpage(Model m)
	{
		m.addAttribute("editmode",false);
		m.addAttribute("productpage",true);
		m.addAttribute("product",new Product());
		m.addAttribute("prodlist", pd.ViewAllProduct());
		m.addAttribute("catlist",cd.ViewAllCategory());
		m.addAttribute("haserror",false);
		m.addAttribute("error", "");
		m.addAttribute("title","biofresh-product");
		return "index";
	}
	
	
	@RequestMapping(value="/admin/CreateProduct",method=RequestMethod.POST)
	public String CreateProduct(@Valid @ModelAttribute("product")Product product,BindingResult br,Model m)
	{
		if(br.hasErrors())
		{
			m.addAttribute("editmode",false);
			System.out.println(br.toString());
			m.addAttribute("productpage",true);
			m.addAttribute("product",product);
			m.addAttribute("prodlist", pd.ViewAllProduct());
			m.addAttribute("catlist",cd.ViewAllCategory());
			m.addAttribute("haserror",true);
			m.addAttribute("error", "please check ur data");
			m.addAttribute("title","biofresh-product");
			return "index";
		}
		else 
		{
			try
			{
				pd.CreateProduct(product);
				addimage(product.getPimage(), product.getProductId());
				m.addAttribute("title","biofresh-product");
				return "redirect:/admin/product";		
			}
			catch(Exception e)
			{
				m.addAttribute("editmode",false);
				m.addAttribute("productpage",true);
				m.addAttribute("product",product);
				m.addAttribute("prodlist", pd.ViewAllProduct());
				m.addAttribute("catlist",cd.ViewAllCategory());
				m.addAttribute("haserror", true);
				m.addAttribute("error", "Already Data Present");
				m.addAttribute("title","biofresh-product");
				return "index";
			}
		}
		
	}
	
	@RequestMapping("/admin/deleteproduct")
	public String deleteproduct(@RequestParam("productName")String productName,Model m)
	{
		Product c=pd.ViewOneProduct(productName);
		pd.DeleteProduct(c);
		m.addAttribute("title","biofresh-product");
		return "redirect:/admin/product";
	}
	
	@RequestMapping("/admin/editproduct")
	public String editproduct(@RequestParam("productName")String productId,Model m)
	{
		m.addAttribute("editmode",true);
		m.addAttribute("product",pd.ViewOneProduct(productId));
		m.addAttribute("productpage",true);
		m.addAttribute("haserror", false);
		m.addAttribute("catlist",cd.ViewAllCategory());
		m.addAttribute("prodlist", pd.ViewAllProduct());
		m.addAttribute("title","biofresh-product");
		return "index";
	}
	
	@RequestMapping(value="/admin/UpdateProduct",method=RequestMethod.POST)
	public String UpdateProduct(@Valid @ModelAttribute("product")Product product,BindingResult br,Model m)
	{
		if(br.hasErrors())
		{
			m.addAttribute("editmode",false);
			System.out.println("erroer");
			m.addAttribute("product",product);
			m.addAttribute("prodlist", pd.ViewAllProduct());
			m.addAttribute("productpage",true);
			m.addAttribute("haserror",true);
			m.addAttribute("catlist",cd.ViewAllCategory());
			m.addAttribute("error", "please check ur data");
			m.addAttribute("title","biofresh-product");
			return "index";
		}
		else 
		{
			try
			{
				pd.UpdateProduct(product);
				addimage(product.getPimage(), product.getProductId());
				m.addAttribute("title","biofresh-product");
				return "redirect:/admin/product";		
			}
			catch(Exception e)
			{
				m.addAttribute("editmode",false);
				m.addAttribute("product",product);
				m.addAttribute("prodlist", pd.ViewAllProduct());
				m.addAttribute("productpage",true);
				m.addAttribute("haserror", true);
				m.addAttribute("catlist",cd.ViewAllCategory());
				m.addAttribute("error", "Already Data Present");
				m.addAttribute("title","biofresh-product");
				return "index";
			}
		}
	}
	
	
	@RequestMapping("/viewallproduct")
	 public String viewallproduct(Model m) 
	 {
		
	  m.addAttribute("prodlist", pd.ViewAllProduct());
	  m.addAttribute("viewallproductPage",true);
	  m.addAttribute("title","biofresh-product");
	  return "index";
	 }
	
	@RequestMapping("/viewoneproduct")
	public String viewoneproductpage(@RequestParam("productname")String productname,Model m) 
	{
		m.addAttribute("product",pd.ViewOneProduct(productname));
		m.addAttribute("ViewOneProductPage", true);
		m.addAttribute("title","biofresh-product");
		return "index";
	}
 
}
