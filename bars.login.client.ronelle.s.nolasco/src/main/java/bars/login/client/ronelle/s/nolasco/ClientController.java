package bars.login.client.ronelle.s.nolasco;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bars.login.server.ronelle.s.nolasco.model.User;

@Controller
@RequestMapping("/")
public class ClientController {
	
	@Autowired
	private ProxyServer clientProxy;
	
	@RequestMapping("/login")
	public String index() {
		return "login";
	}
	@RequestMapping("/insertPage")
	public String insertPage() {
		return "insertPage";
	}
	
	@GetMapping("/all")
	public List<User> allUser(){
		
		return clientProxy.getAll();
	}
	
	@PostMapping("/loginNow")
	public ModelAndView getUserPassword(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		List<User> user = clientProxy.getUserByNameAndPass(userName, password);
		
		if(clientProxy.getUserByNameAndPass(userName, password).size() > 0) {
			
			if(user.get(0).getRole().equals("member")) {
				List<User> listOfUser = clientProxy.getAll();
				model.addObject("userList", listOfUser);
				model.setViewName("success");
				return model;
			}else if(user.get(0).getRole().equals("admin")) {
				List<User> listOfUser = clientProxy.getAll();
				model.addObject("userList", listOfUser);
				model.setViewName("adminSuccess");
				return model;
			}
			return null;
			
		}else {
			model.setViewName("fail");
			return model;
		}
		
	}
	
	@PostMapping("/insertMember")
	public ModelAndView insertUserPassword(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		
		User user = clientProxy.geteUsername(userName);
		
		if(user == null) {
			clientProxy.saveUserByNameAndPass(userName, password, userType);
			model.setViewName("success");
			return model;
		}else {
			model.setViewName("fail");
			return model;
		}
		
		
	}
	
	
}
