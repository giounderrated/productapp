package com.gcode.productapp.users.services;

import java.util.List;

import com.gcode.productapp.users.domain.UserNotAllDetails;
import org.springframework.security.core.userdetails.UserDetails;

import com.gcode.productapp.users.domain.User;
import com.gcode.productapp.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    public static UserServiceImpl create(final UserRepository repository){
        return new UserServiceImpl(repository);
    }

    private UserServiceImpl(final UserRepository repository){
        this.repository = repository;
    }

    @Override
    public List<UserNotAllDetails> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public boolean userExistsWithUsername(String username) {
        return repository.userExistsWithUsername(username);
    }

	@Override
	public boolean userExistsWithEmail(String email) {
		return repository.userExistsWithEmail(email);
	}

	@Override
	public boolean userExistsWithId(long id) {
		return repository.userExistsWithId(id);
	}

	@Override
    public User getUserByUsername(String username) {
        boolean exists = repository.userExistsWithUsername(username);
        if(!exists){
            throw new IllegalArgumentException(String.format("username %s does not exists", username));
        }
        return repository.getUserByUsername(username);
    }

	@Override
	public User getUserByEmail(String email) {
		boolean exists = repository.userExistsWithEmail(email);
		if(!exists){
			throw new IllegalArgumentException(String.format("email %s does not exists",email));
		}
		return repository.getUserByEmail(email);
	}

	@Override
	public User getUserById(long id) {
		boolean exists = repository.userExistsWithId(id);
		if(!exists){
			throw new IllegalArgumentException(String.format("User with id %s does not exists",id));
		}
		return repository.getUserById(id);
	}

	@Override
    public boolean insertUser(User user) {
        boolean exists = repository.userExistsWithUsername(user.getUsername());
        if(exists){
            throw new IllegalArgumentException(String.format("username %s already exists", user.getUsername()));
        }
        return repository.insertUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return repository.deleteUser(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
		return getUserByEmail(username);

    }
    
}
