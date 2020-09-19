package com.sanix.RegistrationProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User registerNewAccount(UserDto userDto){
        throw UserAlreadyExistException{
            if (emailExist(userDto.getEmail())){
                throw new UserAlreadyExistException(
                        "There is an account with that email address: "
                        +userDto.getEmail()
                );
            }
            User user=new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setRoles(Arrays.asList("ROLE_USER"));
            return repository.save(user);
        }
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email) != null;
    }
}
