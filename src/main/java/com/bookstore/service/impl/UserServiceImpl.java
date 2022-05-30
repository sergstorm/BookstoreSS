package com.bookstore.service.impl;

import com.bookstore.domain.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.service.UserService;

import java.util.Set;

/**
 * 
 * @author Manan
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public User findByUsername(String username) {
		return null;
	}

	@Override
	public User findByEmail(String userEmail) {
		return null;
	}

	@Override
	public void createUser(User user, Set<UserRole> userRoles) {

	}

	@Override
	public void save(User user) {

	}


}
