package fr.insa.soa.ExchangeSemester.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.model.User;


@Service
public class UserService{

	@Autowired
	private UserRepository userRepository;

	public User findUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}

}
