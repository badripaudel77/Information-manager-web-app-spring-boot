package info.keeper.utils;

import info.keeper.models.AdminMessage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * To map data selected from the database to the object
 */
public class UserRowMapper implements RowMapper<AdminMessage> {

    @Override
    public AdminMessage mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminMessage user = new AdminMessage();
            user.setId(resultSet.getInt("admin_message_id"));
            user.setDate(resultSet.getTimestamp("posted_data"));
            user.setMessage(resultSet.getString("admin_message"));
            user.setPostedBy(resultSet.getString("posted_by"));
            return user;
    }
}
