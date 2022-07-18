package br.com.sdconecta.doctorsnetwork.services;

import br.com.sdconecta.doctorsnetwork.domain.User;

public interface INotificationService {

	void notify(User user, String message);
}
