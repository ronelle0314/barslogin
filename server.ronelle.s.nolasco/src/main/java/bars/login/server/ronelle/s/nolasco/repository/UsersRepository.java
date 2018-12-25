package bars.login.server.ronelle.s.nolasco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bars.login.server.ronelle.s.nolasco.model.User;

public interface UsersRepository extends JpaRepository<User, Integer>{

	List<User> findByUsername(String username);

}
