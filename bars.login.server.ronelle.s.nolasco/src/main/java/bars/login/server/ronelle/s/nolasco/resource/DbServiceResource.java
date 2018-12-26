package bars.login.server.ronelle.s.nolasco.resource;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bars.login.server.ronelle.s.nolasco.model.User;
import bars.login.server.ronelle.s.nolasco.repository.UsersRepository;

@RestController
@RequestMapping("/")
public class DbServiceResource {
	
	private UsersRepository userRepository;
	
	
	public DbServiceResource(UsersRepository userRepository) {
		
		this.userRepository = userRepository;
	}


	@GetMapping("/{username}/{password}")
	public List<User> getUserByNameAndPass(@PathVariable("username") final String userName, @PathVariable("password") final String password){
		
		List<User> findName = userRepository.findByUsernameAndPassword(userName, password);
		
		return findName;
		
	}
	
	@GetMapping("/{username}")
	public User geteUsername(@PathVariable("username") final String userName){
		
		User findName = userRepository.findByUsername(userName);
		
		return findName;
		
	}
	
	@GetMapping("/{username}/{password}/{usertype}")
	public void saveUserByNameAndPass(@PathVariable("username") final String userName, @PathVariable("password") final String password, @PathVariable("usertype") final String userType){
		
		User user = new User(userName, password, userType);
		userRepository.save(user);
		
	}
	
	@DeleteMapping("/delete/{username}")  
	public void deleteUserByName(@PathVariable("username") final String userName){
		
		if(userRepository.findByUsername(userName).getUsername().length() > 0) {
			userRepository.deleteById(userRepository.findByUsername(userName).getId());
		}
		
	}
	
	@GetMapping("/all")
	public List<User> getAll(){
		
		//userRepository.findByUserName(username, password, userType);
		
		return userRepository.findAll();
		
	}
}
