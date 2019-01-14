package fr.insa.soa.ExchangeSemester.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.soa.ExchangeSemester.model.Notification;
import fr.insa.soa.ExchangeSemester.model.User;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	List<Notification> findByUser(User user);
}
