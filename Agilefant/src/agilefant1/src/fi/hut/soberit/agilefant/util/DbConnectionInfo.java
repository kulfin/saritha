package fi.hut.soberit.agilefant.util;

import java.net.URI;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.typesafe.config.Config;

public class DbConnectionInfo {
    public final String url;
    public final String hostname;
    public final String databaseName;
    public final String password;
    public final String username;

    public static DbConnectionInfo create(Config config, DataSource dataSource) {
        final String username = config.getString("agilefant.database.username");
        final String password = config.getString("agilefant.database.password");

        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        return jdbc.execute(new ConnectionCallback<DbConnectionInfo>() {
            @Override
            public DbConnectionInfo doInConnection(Connection con) throws SQLException, DataAccessException {
                DatabaseMetaData metaData = con.getMetaData();

                String databaseName = con.getCatalog();
                String url = metaData.getURL();

                String cleanURI = url.substring(5);
                URI uri = URI.create(cleanURI);
                String hostname = uri.getHost();

                return new DbConnectionInfo(url, hostname, databaseName, password, username);
            }
        });
    }

    public DbConnectionInfo(String url, String hostname, String databaseName, String password, String username) {
        this.url = url;
        this.hostname = hostname;
        this.databaseName = databaseName;
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "DbConnectionInfo [url=" + url + ", hostname=" + hostname + ", databaseName=" + databaseName + ", password=" + password + ", username="
                + username + "]";
    }

}
