package org.example.service;
import org.example.model.Role;
import org.example.model.User;
import java.util.List;

public interface UserService
{
    List<User> listAll();
    User create(String username, String password, Role role, String email);
    User login(String username, String password);

}
