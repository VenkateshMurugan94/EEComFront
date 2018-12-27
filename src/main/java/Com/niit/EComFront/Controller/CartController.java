package Com.niit.EComFront.Controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Com.niit.EComBack.DAO.CartDAO;
import Com.niit.EComBack.DAO.ProductDAO;
import Com.niit.EComBack.model.Cart;
import Com.niit.EComBack.model.Product;

@Controller
public class CartController {
	
	@Autowired
	CartDAO cartdao;

	@Autowired
	ProductDAO productdao;

	@RequestMapping("addToCart")
	String addToCart(@RequestParam("productName") String productName, HttpSession session, Model m) {
		if (session.getAttribute("usercartid") != null) {
			Product p = productdao.ViewOneProduct(productName);
			ArrayList<Cart> cartlist = (ArrayList<Cart>) cartdao.ViewAllCart(Integer.parseInt(session.getAttribute("usercartid").toString()));
			Iterator<Cart> cartiterator = cartlist.listIterator();
			while (cartiterator.hasNext()) {
				Cart cart2 = (Cart) cartiterator.next();
				if (cart2.getProductName().equals(productName)) {
					int qty = cart2.getProductQuantity() + 1;
					if (qty > p.getProductQuantity()) {
						m.addAttribute("cartpage", true);
						m.addAttribute("title", "BioFresh-Cart");
						m.addAttribute("cartlist", cartdao.ViewAllCart(cart2.getCartId()));
						m.addAttribute("error1", true);
						m.addAttribute("error", false);
						m.addAttribute("stock", p.getProductQuantity());
						m.addAttribute("pid", cart2.getProductId());
						return "index";

					} else {
						if (qty > 3) {
							m.addAttribute("cartpage", true);
							m.addAttribute("title", "BioFresh-Cart");
							m.addAttribute("cartlist", cartdao.ViewAllCart(cart2.getCartId()));
							m.addAttribute("error", true);
							m.addAttribute("error1", false);
							m.addAttribute("pid", cart2.getProductId());
							return "index";
						}
						cart2.setProductQuantity(qty);
						cart2.setSubTotal(qty * p.getProductPrice());
						cartdao.UpdateCart(cart2);
						m.addAttribute("cartpage", true);
						m.addAttribute("title", "BioFresh-Cart");
						m.addAttribute("cartlist", cartdao.ViewAllCart(cart2.getCartId()));
						m.addAttribute("error", false);
						m.addAttribute("error1", false);
						return "index";
					}

				}
			}
			Cart c = new Cart();
			c.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
			c.setProductId(p.getProductId());
			c.setProductQuantity(1);
			c.setProductName(p.getProductName());
			c.setProductPrice(p.getProductPrice());
			c.setSubTotal(p.getProductPrice());
			cartdao.CreateCart(c);
			m.addAttribute("cartpage", true);
			m.addAttribute("title", "BioFresh-Cart");
			m.addAttribute("cartlist", cartdao.ViewAllCart(c.getCartId()));
			m.addAttribute("error", false);
			m.addAttribute("error1", false);
			return "index";

		} else {
			session.setAttribute("productName", productName);
			return "redirect:/Login";
		}

	}

	@RequestMapping("/user/viewCart")
	String viewCart(Model m, HttpSession session) {
		int cartid = Integer.parseInt(session.getAttribute("usercartid").toString());
		m.addAttribute("cartpage", true);
		m.addAttribute("title", "BioFresh-Cart");
		m.addAttribute("cartlist", cartdao.ViewAllCart(cartid));
		m.addAttribute("error", false);
		m.addAttribute("error1", false);
		return "index";
	}

	@RequestMapping("/user/deleteitem")
	public String deleteCart(@RequestParam("itemid") int id) {
		cartdao.DeleteCart(cartdao.ViewOneCart(id));
		return "redirect:/user/viewCart";
	}

	@RequestMapping("/user/incqty")
	public String incqty(@RequestParam("itemid") int id, Model m) {
		Cart c = cartdao.ViewOneCart(id);
		int qty = c.getProductQuantity() + 1;
		int pqty = productdao.ViewOneProduct(c.getProductName()).getProductQuantity();
		if (qty > pqty) {
			m.addAttribute("cartpage", true);
			m.addAttribute("title", "BioFresh-Cart");
			m.addAttribute("cartlist", cartdao.ViewAllCart(c.getCartId()));
			m.addAttribute("error1", true);
			m.addAttribute("error", false);
			m.addAttribute("stock", pqty);
			m.addAttribute("productId", c.getProductId());

			return "index";

		} else {
			if (qty > 3) {
				m.addAttribute("cartpage", true);
				m.addAttribute("title", "BioFresh-Cart");
				m.addAttribute("cartlist", cartdao.ViewAllCart(c.getCartId()));
				m.addAttribute("error", true);
				m.addAttribute("error1", false);
				m.addAttribute("pid", c.getProductId());
				return "index";
			} else {
				c.setProductQuantity(qty);
				c.setSubTotal(c.getProductPrice() * qty);
				cartdao.UpdateCart(c);
				return "redirect:/user/viewCart";
			}
		}

	}

	@RequestMapping("/user/decqty")
	public String decqty(@RequestParam("itemid") int id, Model m) {
		Cart c = cartdao.ViewOneCart(id);
		int qty = c.getProductQuantity() - 1;
		if (qty == 0) {
			m.addAttribute("cartpage", true);
			m.addAttribute("title", "BioFresh-Cart");
			m.addAttribute("cartlist", cartdao.ViewAllCart(c.getCartId()));
			m.addAttribute("error", true);
			m.addAttribute("pid", c.getProductId());
			m.addAttribute("error1", false);
			return "index";
		} else {
			c.setProductQuantity(qty);
			c.setSubTotal(c.getProductPrice() * qty);
			cartdao.UpdateCart(c);
			return "redirect:/user/viewCart";
		}
	}

}
