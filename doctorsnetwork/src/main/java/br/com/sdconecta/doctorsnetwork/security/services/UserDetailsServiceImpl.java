package br.com.sdconecta.doctorsnetwork.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UsersRepository usersRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = usersRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return UserDetailsImpl.build(user);
  }
}