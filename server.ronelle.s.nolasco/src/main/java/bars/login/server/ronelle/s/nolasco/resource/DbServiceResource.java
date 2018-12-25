package bars.login.server.ronelle.s.nolasco.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bars.login.server.ronelle.s.nolasco.model.User;
import bars.login.server.ronelle.s.nolasco.model.Users;
import bars.login.server.ronelle.s.nolasco.repository.UsersRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {
	
	private UsersRepository UsersRepository;
	
	
	public DbServiceResource(UsersRepository usersRepository) {
		
		UsersRepository = usersRepository;
	}
	@GetMapping("/{username}")
	public List<String> getUser(@PathVariable("username") final String username)
	{
		return UsersRepository.findByUsername(username)
				.stream()
				.map(User::getPassword)
				.collect(Collectors.toList());
		
	}
	@PostMapping("/add")
	public List<String> add(@RequestBody final Users users)

	{
		return null;
	}
}
