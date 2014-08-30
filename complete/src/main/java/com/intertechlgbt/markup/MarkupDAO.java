package com.intertechlgbt.markup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by scaladojo on 30/08/2014.
 */
public class MarkupDAO {

    private JdbcTemplate jdbcTemplate;

    public MarkupDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public UserPronounPreferences loadUserPronounPreferences(final String userId) {
        List<UserPronounPreferences> resultList = this.jdbcTemplate.query("select they,them,their,themselves,their from UserPronounPreferences where userId=?",
                new Object[] {userId}, new RowMapper<UserPronounPreferences>() {
                    @Override
                    public UserPronounPreferences mapRow(ResultSet rs, int i) throws SQLException {
                        return new UserPronounPreferences(userId,new Pronouns(rs.getString("they"),
                                rs.getString("them"),
                                rs.getString("their"),
                                rs.getString("themselves"),
                                rs.getString("theirs")));
                    }
                });
        if (resultList.size()>0) {
            return resultList.get(0);
        }
        else {
            return null;
        }
    }

    public void saveUserPronounPreferences(UserPronounPreferences userPronounPreferences) {
        this.jdbcTemplate.update("delete from UserPronounPreferences where userId=?");
        this.jdbcTemplate.update("insert into UserPronounPreferences (userId,they,them,their,themselves,theirs) values (?,?,?,?,?,?)",
                userPronounPreferences.getUserId(), userPronounPreferences.getPronouns().getThey(),userPronounPreferences.getPronouns().getThem(),
                userPronounPreferences.getPronouns().getTheir(), userPronounPreferences.getPronouns().getThemselves(), userPronounPreferences.getPronouns().getTheirs());

    }
}
