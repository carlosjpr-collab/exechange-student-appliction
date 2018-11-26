package fr.insa.soa.ExchangeSemester.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.model.User;

@Service
public class UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public boolean saveUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		if (userRepository.findByLogin(user.getLogin()) == null) {
			// login not found in the db, can save
			byte[] byteChaine = user.getPassword().getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(byteChaine);

			String myHash = DatatypeConverter.printHexBinary(hash).toLowerCase();

			user.setPassword(myHash);
			userRepository.save(user);
			return true;
		}

		else {
			// login already used, cannot save
			return false;
		}

	}
}
