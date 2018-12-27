package Com.niit.EComFront.Controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Com.niit.EComBack.DAO.AddressDAO;
import Com.niit.EComBack.DAO.CartDAO;
import Com.niit.EComBack.DAO.OrderDAO;
import Com.niit.EComBack.DAO.ProductDAO;
import Com.niit.EComBack.model.Cart;
import Com.niit.EComBack.model.MyOrder;
import Com.niit.EComBack.model.Product;

@Controller
public class OrderController {
	
	@Autowired
	CartDAO cartdao;
	
	@Autowired
	OrderDAO orderdao;
	
	@Autowired
	AddressDAO addressdao;
	
	@Autowired
	ProductDAO productdao;
	
	@RequestMapping("/user/placeorder")
	String placeOrder(@RequestParam("addid")int id,HttpSession session)
	{
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartdao
				.ViewAllCart(Integer.parseInt(session.getAttribute("usercartid").toString()));
		Iterator<Cart> cartiterator = cartlist.listIterator();
		while (cartiterator.hasNext())
		{
			Cart cart2 = (Cart) cartiterator.next();
			Long uuid=UUID.randomUUID().getMostSignificantBits();
			String oid="OD"+uuid.toString().replace('-', '2');
			MyOrder order=new MyOrder();
			Product p=productdao.ViewOneProduct(cart2.getProductName());
			int qty=p.getProductQuantity()-cart2.getProductQuantity();
			System.out.println(qty);
			p.setProductQuantity(qty);
			productdao.UpdateProduct(p);
			order.setOrderid(oid);
			order.setCartid(Integer.parseInt(session.getAttribute("usercartid").toString()));
			order.setAddid(id);
			order.setOdate(new Date());
			order.setPid(cart2.getProductId());
			order.setProduct_Name(cart2.getProductName());
			order.setQuantity(cart2.getProductQuantity());
			order.setPrice(cart2.getSubTotal());
			orderdao.Placeorder(order);
			cartdao.DeleteCart(cart2);
		}
		return "redirect:/user/viewOrders";
	}
	
	@RequestMapping("/user/viewOrders")
	String viewOrders(Model m,HttpSession session)
	{
		m.addAttribute("orderlist",orderdao.ViewAllOrder(Integer.parseInt(session.getAttribute("usercartid").toString())));
		m.addAttribute("orderpage",true);
		return"index";
		
	}
	
	@RequestMapping("/user/viewbill")
	String viewBills(Model m,HttpSession session,@RequestParam("orderid")String id)
	{
		MyOrder o=orderdao.ViewOneOrder(id);
		m.addAttribute("order",o);
		m.addAttribute("address",addressdao.ViewOneAddress(o.getAddid()));
		m.addAttribute("billpage",true);
		return"index";
		
	}

}
