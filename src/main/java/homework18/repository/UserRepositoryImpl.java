package homework18.repository;

import homework18.model.Role;
import homework18.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_EMAIL = "select * from db7.user where email = ?";
    private static final String SQL_INSERT_USER = "insert into db7.user (email, name, hash, role) values (?, ?, ?, ?)";

    public RowMapper<User> userRowMapper = (row, rowNum) ->
            User.builder()
                    .id(row.getLong("id"))
                    .email(row.getString("email"))
                    .name(row.getString("name"))
                    .hash(row.getString("hash"))
                    .role(Role.valueOf(row.getString("role")))
                    .build();

    @Override
    public User findUserByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getEmail(), user.getName(), user.getHash(), user.getRole().toString());
    }
}
