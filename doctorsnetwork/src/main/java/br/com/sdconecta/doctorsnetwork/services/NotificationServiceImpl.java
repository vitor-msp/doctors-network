package br.com.sdconecta.doctorsnetwork.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.sdconecta.doctorsnetwork.domain.User;
//import lombok.extern.slf4j.Slf4j;

// @Slf4j
// usar diretamente a annotation não funcionou, portanto realizei manualmente a instanciação do log
@Service
public class NotificationServiceImpl implements INotificationService {

	private static Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

	public void notify(User user, String message) {
		
		String body = "Notification - to: " + user.getEmail() + ", message: " + message;
		
		log.info(body);
	};
}
