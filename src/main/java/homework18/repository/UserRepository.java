package homework18.repository;

import homework18.model.User;;

public interface UserRepository  {
    User findUserByEmail(String email);
    void save(User user);
}
