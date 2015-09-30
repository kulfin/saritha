package fi.hut.soberit.agilefant.flyway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration;

public class V1_11__HashPasswordsWithBCrypt implements JdbcMigration {

	private static final Logger LOG = LoggerFactory.getLogger(V1_11__HashPasswordsWithBCrypt.class);	
	
	@Override
	public void migrate(Connection connection) throws Exception {

		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
			
			PreparedStatement readPasswords = connection.prepareStatement("SELECT id, password FROM users");		
			ResultSet resultSet = readPasswords.executeQuery();

			PreparedStatement insertPassword = connection.prepareStatement("UPDATE users SET passwd=? WHERE id=?");
			
			int count = 0;
			while(resultSet.next()) {
				int id = 0;
				try {
					id = resultSet.getInt("id");
					String password = resultSet.getString("password");

					insertPassword.setString(1, password==null ? null : encoder.encode(password));
					insertPassword.setInt(2, id);
					
					insertPassword.execute();
					
					if(count++ % 100 == 0) {
						connection.commit();
						LOG.info("Converted password for user id " + id);
					}					
				} catch(Exception e) {
					LOG.error("Unable to convert password for user id " + id);					
				}
			}
		} catch(Exception e) {
			LOG.error("Unable to convert passwords");
			throw e;
		}
	}
}
