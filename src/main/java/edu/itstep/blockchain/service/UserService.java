package edu.itstep.blockchain.service;

import edu.itstep.blockchain.domain.User;

public interface UserService {
	public User saveUpdateUser(User person);
    public User findUserById(Integer id);
}
