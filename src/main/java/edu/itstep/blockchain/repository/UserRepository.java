package edu.itstep.blockchain.repository;

import edu.itstep.blockchain.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
       User findById(int id);
}
