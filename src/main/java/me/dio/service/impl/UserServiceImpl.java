package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.model.repository.UserRepository;
import me.dio.service.UserService;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User create(User usertoCreate) {
		if(userRepository.existsByAccountNumber(usertoCreate.getAccount().getNumber())) {
			throw new IllegalArgumentException("This account number already exists");
		}

		return userRepository.save(usertoCreate);
	}

}
