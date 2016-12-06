import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Server {

	public static void main(String[] args) {
		 try {
			Class.forName("org.h2.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:formation");

//	        Server server = Server.createTcpServer().start();//1.TcpServer
			Server server = Server.createWebServer().start();//2.WebServer

	        System.out.println("Server started and connection is open.");
	        System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:formation");

	        Thread.sleep(5*60*1000);

	        System.out.println("Stopping server and closing the connection");
	        server.stop();
	        conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}
}
