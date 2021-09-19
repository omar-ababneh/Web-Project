import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConnectionPool {
    List<Connection> Pool=new ArrayList<>();
    public void init(int number){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connected With Driver");
          for (int i=0;i<number;i++){
              Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem","Omar","1234");
              Pool.add(connection);
          }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void close(){

            for(int i=0;i<Pool.size();i++){
                try {
                   Pool.get(i).close();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
    }
    public synchronized Connection getConnection() {

        Connection connection= Pool.remove(Pool.size()-1);
        return connection;
    }
    public synchronized void ReturnConnection(Connection connection){
       Pool.add(connection);
    }
}
