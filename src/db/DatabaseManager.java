/**
 * @brief This class is used to communicate with the Database
 */

package db;

import java.io.File;
import java.sql.*;

import utils.MacroDef;

public class DatabaseManager {
	private static Connection conn;
	
	/**
	 * @brief  Setup the Connection，when this function is called, if a conn already be setup, it will return the same connection
	 *  If not, It will setup a connection and return it.
	 * @return sql connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection("jdbc:h2:"+ MacroDef.ROOT_DIRECTORY + MacroDef.DB_NAME, "sa", "");
        }
        return conn;
	}
	
	/**
	 * @brief check if the DB file exist
	 * 
	 */
	public static boolean checkDBFileExists() {
	    String dbPath = MacroDef.ROOT_DIRECTORY + MacroDef.DB_DIR;
	    File dbFile = new File(dbPath);
	    return dbFile.exists();
	}
	
	/**
	 * 
	 */
	public static void initializeDB() {

		try {
			boolean dbExists = checkDBFileExists();
			Connection conn = getConnection();

			if (!dbExists) {
				System.out.println("The DB dosen't exist, beginning to initialize");

				Statement stmt = conn.createStatement();


				//  create the members table
				stmt.execute("CREATE TABLE members (" +
						"id INT AUTO_INCREMENT PRIMARY KEY, " +
						"first_name VARCHAR(63), " +
						"last_name VARCHAR(63), " +
						"phone VARCHAR(63), " +
						"email VARCHAR(63), " +
						"role VARCHAR(100), " +
						"username VARCHAR(100), " +
						"pwd_hash VARCHAR(100))");
				
				//Create projects table
				stmt.execute("CREATE TABLE projects (" +
					    "id INT AUTO_INCREMENT PRIMARY KEY, " +
					    "name VARCHAR(255) NOT NULL, " +
					    "description VARCHAR(500), " +
					    "create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
					    "start_date DATE, " +
					    "deadline DATE, " +
					    "priority  INT DEFAULT 3, " +       // 1=highest 5=lowest
					    "status VARCHAR(50) DEFAULT 'NEW', " +
					    "update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
					")");
				
				//Create tasks table
				stmt.execute("CREATE TABLE tasks (" +
					    "id INT AUTO_INCREMENT PRIMARY KEY, " +
					    "title VARCHAR(255) NOT NULL, " +
					    "description VARCHAR(500), " +
					    "create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
					    "due_date DATE, " +
					    "completed_time TIMESTAMP, " +
					    "priority  INT DEFAULT 3, " +
					    "status VARCHAR(50) DEFAULT 'PENDING', " +
					    "update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
					")");
				
				
				//Create project_task table
				stmt.execute("CREATE TABLE project_task (" +
					    "project_id INT NOT NULL, " +
					    "task_id    INT NOT NULL, " +
					    "PRIMARY KEY (project_id, task_id), " +
					    "FOREIGN KEY (project_id) REFERENCES projects(id), " +
					    "FOREIGN KEY (task_id)    REFERENCES tasks(id)" +
					")");
				
				//Create member_task table
				
				stmt.execute("CREATE TABLE member_task (" +
					    "member_id INT NOT NULL, " +
					    "task_id   INT NOT NULL, " +
					    "PRIMARY KEY (member_id, task_id), " +
					    "FOREIGN KEY (member_id) REFERENCES members(id), " +
					    "FOREIGN KEY (task_id)   REFERENCES tasks(id)" +
					")");
				
				

				// 3. Optional: write in the table.
				stmt.execute("INSERT INTO projects(name, status) VALUES('test project', 'not start')");
				stmt.execute("INSERT INTO members(first_name, last_name, phone, email, role, username, pwd_hash) "
						+ "VALUES('Jack', 'Wang', '000-000-0000', 'test@test.com','admin', 'jack2025', '1e4b70574b8ec9633ef4a1fc1113fbf5c04b6ec810798554bbc6bfe85beabb4c')");
				System.out.println("Inserted Jack into members table.");
				System.out.println("DB initialized successfully ✅");
				stmt.close();
			} else {
				System.out.println("db exists in " + MacroDef.ROOT_DIRECTORY + MacroDef.DB_NAME + ", skip the initialize");
			}

		} catch (Exception e) {
			System.err.println("Error during initialize");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param username: A string of username
	 * 
	 * @brief Parameterized Query， use the H2 database command SELECT pwd_hash FROM members WHERE username = ?
	 * 
	 * @return The pwd_hash
	 */
	
	public static String getStoredPwdHash(String username) {
		String storedHash = "";
		String statementString = "SELECT pwd_hash FROM members WHERE username = ?";

	    try {
	        Connection conn = DatabaseManager.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(statementString);
	        stmt.setString(1, username);

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            storedHash = rs.getString("pwd_hash");
	        }

	        rs.close();
	        stmt.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return storedHash;
	}
	
	public static String getStoredFirstName(String username) {
	    String storedName = "";
	    String statementString = "SELECT first_name FROM members WHERE username = ?";

	    try {
	        Connection conn = DatabaseManager.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(statementString);
	        stmt.setString(1, username);

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            storedName = rs.getString("first_name");
	        }

	        rs.close();
	        stmt.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return storedName;
	}
	
	public static String getStoredLastName(String username) {
	    String storedName = "";
	    String statementString = "SELECT last_name FROM members WHERE username = ?";

	    try {
	        Connection conn = DatabaseManager.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(statementString);
	        stmt.setString(1, username);

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            storedName = rs.getString("last_name");
	        }

	        rs.close();
	        stmt.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return storedName;
	}

	/**
	 * @brief Shutdown the Connection, usually called once when the whole program shutdown
	 */
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (Exception ignored) {}
    }
	

}
