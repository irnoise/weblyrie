package jp.ne.noise.weblyrie.dao;


public interface DataBaseConnectionService {
	/******** DRIVER_NAME ********
	 * H2 Database Engine : org.h2.Driver
	 * MySQL              : com.mysql.jdbc.Driver
	 *****************************/
	/******** JDBC_URL ********
	 * H2 Database Engine : jdbc:h2:file:{dir}/{DB Name}
	 * MySQL              : jdbc:mysql://localhost/{DB Name}
	 *****************************/

	public final String DRIVER_NAME = "org.h2.Driver";
	public final String JDBC_URL = "jdbc:h2:file:/C:/H2data/weblyrie";
	public final String DB_USER = "sa";
	public final String DB_PASS = "";

	public Object findAll();
	public boolean create(Object obj);
	public boolean update(Object obj);
	public boolean delete(String any, String target);
}
