package com.intertechlgbt.markup;

import org.apache.commons.dbcp.BasicDataSource;
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
@Service
public class MarkupDAO {

    private JdbcTemplate jdbcTemplate;
	private String user;
	private String password;
	private String url;
	private String table;
	
    public MarkupDAO() {
    	//assuming server run using java -cp ../lib/hsqldb.jar org.hsqldb.Server -database.0 mydb -dbname.0 xdb
		this.user = "sa";
		this.password = "";
		this.url = "jdbc:hsqldb:hsql://localhost/xdb";
		this.table = "UserPronounPreferences";
        BasicDataSource ds1 = new BasicDataSource();
        ds1.setDriverClassName("org.hsqldb.jdbcDriver");
        ds1.setUsername(user);
        ds1.setPassword(password);
        ds1.setUrl(url);
        this.jdbcTemplate = new JdbcTemplate(ds1);
    }

    public UserPronounPreferences loadUserPronounPreferences(final String userId) {
        List<UserPronounPreferences> resultList = this.jdbcTemplate.query("select they,them,their,themselves,theirs from UserPronounPreferences where userId=?",
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
        this.jdbcTemplate.update("delete from UserPronounPreferences where userId=?",userPronounPreferences.getUserId());
        this.jdbcTemplate.update("insert into UserPronounPreferences (userId,they,them,their,themselves,theirs) values (?,?,?,?,?,?)",
                userPronounPreferences.getUserId(), userPronounPreferences.getPronouns().getThey(),userPronounPreferences.getPronouns().getThem(),
                userPronounPreferences.getPronouns().getTheir(), userPronounPreferences.getPronouns().getThemselves(), userPronounPreferences.getPronouns().getTheirs());

    }

	private void dropTable() {
	    String dropTableSql = "Drop TABLE " + table + ";";
	    jdbcTemplate.execute(dropTableSql);		
	}
	
	private void createTable() {
	    String createTableSql = "CREATE TABLE UserPronounPreferences(userId varchar(255) PRIMARY KEY, they varchar(255),them varchar(255),their varchar(255),themselves varchar(255),theirs varchar(255));";
	    jdbcTemplate.execute(createTableSql);
	}
}
