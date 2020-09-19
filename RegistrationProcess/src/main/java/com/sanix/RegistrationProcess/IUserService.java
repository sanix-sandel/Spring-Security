package com.sanix.RegistrationProcess;

import org.springframework.security.core.userdetails.User;

public interface IUserService {

    User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;
}
