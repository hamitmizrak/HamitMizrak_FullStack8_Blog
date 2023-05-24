package com.hamitmizrak.security;

import com.hamitmizrak.data.entity.UserEntity;
import com.hamitmizrak.data.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// LOMBOK
//@SneakyThrows

// loadUserByUsername
// kullanıcı Bilgileri içinde (hesap kiliti mi ?
// Kullanıcı etkin mi ?,
// Kullanıcı kimlik bilgileri süresi  kontrol eder)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//UserEntity user = userRepository.getUserByUsername(username);
		UserEntity user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Could not find user"));
		/*if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}*/
		return new MyUserDetails(user);
	} // end method loadUserByUsername

} //end class
