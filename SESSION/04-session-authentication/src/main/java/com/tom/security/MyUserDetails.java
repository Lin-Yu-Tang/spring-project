package com.tom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tom.bean.User;
import com.tom.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {
	
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
            new UsernameNotFoundException("User not found with username or email : " + username)
            );
            
    return UserPrincipal.create(user);
  }
  @Transactional
  public UserDetails loadUserById(Long id) {
      User user = userRepository.findById(id).orElseThrow(
              () -> new UsernameNotFoundException("User not found")
                );

 

      return UserPrincipal.create(user);
  }

}
