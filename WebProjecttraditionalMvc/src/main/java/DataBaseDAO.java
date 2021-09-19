import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public  class DataBaseDAO {
    private static DataBaseDAO dataBaseDAO;
    private static ConnectionPool connectionPool;
    private DataBaseDAO(){}
    public synchronized static DataBaseDAO getDataBaseDAO(){
        if(dataBaseDAO==null) {
            dataBaseDAO = new DataBaseDAO();
            connectionPool=new ConnectionPool();
            connectionPool.init(20);
        }
        return dataBaseDAO;
    }
    public  int AddStudent(User user){
        int status=0;
        Connection con=null;
        try{
             con= connectionPool.getConnection();
            String SqlStatement="insert into  student(FName,Pass,Email,SAID) values(?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(SqlStatement);
            pstmt.setString(1,user.getFName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3,user.getEmail() );
            pstmt.setInt(4,user.getFID());
            status= pstmt.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
        finally {
            connectionPool.ReturnConnection(con);
        }
        return  status;
    }
    public  int AddAdmin(User user){
        int status=0;
        Connection con=null;
        try{
             con= connectionPool.getConnection();
            String SqlStatement="insert into  admin(FName,Pass,Email,FAID) values(?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(SqlStatement);
            pstmt.setString(1,user.getFName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3,user.getEmail() );
            pstmt.setInt(4,user.getFID());
            status= pstmt.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
        finally {
            connectionPool.ReturnConnection(con);
        }
        return  status;
    }
    public  int AddInstructor(User user){
        int status=0;
        Connection con=null;
        try{
             con= connectionPool.getConnection();
            String SqlStatement="insert into  instructor(FName,Pass,Email,IAID) values(?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(SqlStatement);
            pstmt.setString(1,user.getFName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3,user.getEmail() );
            pstmt.setInt(4,user.getFID());
            status= pstmt.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
        finally {
            connectionPool.ReturnConnection(con);
        }
        return  status;
    }
    public  int ChangeGrade(String Grade ,String CID ,int SID ){
        int status=0;
        Connection con=null;
        try{
             con= connectionPool.getConnection();
            String SqlStatement="Update enrollments set SCORE=?where CID=? AND SID=?;";
            PreparedStatement pstmt = con.prepareStatement(SqlStatement);
            pstmt.setDouble(1, Double.parseDouble(Grade));
            pstmt.setInt(2, Integer.parseInt(CID));
            pstmt.setInt(3, SID);
            status= pstmt.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
        finally {
            connectionPool.ReturnConnection(con);
        }
        return  status;
    }
    public  Map<Integer,StudentGrade> getStudentsAndGrade(String CID) {
        Map<Integer,StudentGrade> map=new TreeMap<>();
        int SID;
        Connection con=null;
        try{
             con= connectionPool.getConnection();
            Statement statement=con.createStatement();
            String SqlStatement="select enrollments.SID,FName,SCORE from enrollments ,courses,student where enrollments.CID = "+"'"+CID+"'"+"  and enrollments.SID=student.SID;";
            ResultSet resultSet=statement.executeQuery(SqlStatement);
            while(resultSet.next()){
                StudentGrade studentGrade=new StudentGrade();
                SID= Integer.parseInt(resultSet.getString(1));
                studentGrade.setFName(resultSet.getString(2));
                studentGrade.setGrade(resultSet.getDouble(3));
                map.put(SID,studentGrade);
            }
        }catch(Exception e){e.printStackTrace();}
        finally {
            connectionPool.ReturnConnection(con);
        }
        return map;
    }
    public  Map<String,String> getAllCoursesForStudent(String Id) {
        Map<String,String> map=new TreeMap<>();
        String Cname,Score;
        Connection con=null;
        try{
             con= connectionPool.getConnection();
            Statement statement=con.createStatement();
            String SqlStatement="select CName,SCORE from enrollments ,courses where SID = "+"'"+Id+"'"+"  and enrollments.CID=courses.CID;";
            ResultSet resultSet=statement.executeQuery(SqlStatement);
            while(resultSet.next()){
                Cname=resultSet.getString(1);
                Score=resultSet.getString(2);
                map.put(Cname,Score);
            }
        }catch(Exception e){e.printStackTrace();}
        finally {
            connectionPool.ReturnConnection(con);
        }
        return map;
    }
    public  Map<String,String> getAllCoursesForInstructor(String Id) {
        Map map=new TreeMap();
        String Cname,CID;
        Connection con=null;
        try{
             con= connectionPool.getConnection();
            Statement statement=con.createStatement();
            String SqlStatement="select CID,CName from instructor ,courses where IID = "+"'"+Id+"'"+"  and instructor.IID=courses.CIID;";
            ResultSet resultSet=statement.executeQuery(SqlStatement);
            while(resultSet.next()){
                CID=String.valueOf(resultSet.getString(1));
                Cname=resultSet.getString(2);
                map.put(CID,Cname);
            }
        }catch(Exception e){e.printStackTrace();}
        finally {
            connectionPool.ReturnConnection(con);
        }
        return map;
    }
    public  User Login(String Email, String Password,String Type) {
       User user=new User();
        Connection con=null;
        try {
             con = connectionPool.getConnection();
            Statement statement = con.createStatement();
            System.out.println("Email = "+Email);
            String SqlStatement="";
            if(Type.equals("Student")){
                SqlStatement= "select * from student where Email = "+"'"+Email+"'"+"  AND  Pass = "+"'"+Password+"'";
            }else if(Type.equals("Admin")){
                 SqlStatement = "select * from admin where Email = "+"'"+Email+"'"+"  AND  Pass = "+"'"+Password+"'";
            }else if(Type.equals("Instructor")){
                 SqlStatement = "select * from instructor where Email = "+"'"+Email+"'"+"  AND  Pass = "+"'"+Password+"'";
            }
            if(!SqlStatement.equals("")){
                ResultSet resultSet=statement.executeQuery(SqlStatement);
                while (resultSet.next()){
                    user.setID(Integer.parseInt(resultSet.getString(1)));
                    System.out.println("resultSet.getString(1)"+resultSet.getString(1));
                    user.setFName(resultSet.getString(2));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            connectionPool.ReturnConnection(con);
        }
        return user;
    }
}


