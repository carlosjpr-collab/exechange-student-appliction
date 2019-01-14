package fr.insa.soa.ExchangeSemester.restServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.NotificationRepository;
import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.model.Notification;
import fr.insa.soa.ExchangeSemester.model.User;

@RestController
public class NotificationRESTService {

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "/service/notification")
	public List<Notification> getNotifications() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName(); // get the username
		User user = userRepository.findByLogin(login); // not found exception

		List<Notification> listNotif = notificationRepository.findByUser(user);
		System.out.println(listNotif.toString());
		return listNotif;
	}
}
