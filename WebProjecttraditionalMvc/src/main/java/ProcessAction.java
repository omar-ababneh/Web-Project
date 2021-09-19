import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ProcessAction implements Action{
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String Email= httpServletRequest.getParameter("email");
        String Password =httpServletRequest.getParameter("psw");
        String TypeUser=Service.ExtractTypeUser(Email);
        HttpSession session=httpServletRequest.getSession();
        User user=DAO.Login(Email,Password,TypeUser);
        session.setAttribute("UID",String.valueOf(user.getID()));
        session.setAttribute("TypeUser",TypeUser);
        if(user!=null){
            session.setAttribute("UEmail",user.getEmail());
            if(TypeUser.equals("Student")){
                Map<String,String> map= DAO.getAllCoursesForStudent(String.valueOf(user.getID()));
                httpServletRequest.setAttribute("Courses",map);
                httpServletRequest.setAttribute("FName",user.getFName());
                httpServletRequest.getRequestDispatcher("views/StudentCourses.jsp").forward(httpServletRequest,httpServletResponse);
            }
            else if(TypeUser.equals("Instructor")){
                Map <String,String> map= DAO.getAllCoursesForInstructor(String.valueOf(user.getID()));
                httpServletRequest.setAttribute("Courses",map);
                httpServletRequest.setAttribute("FName",user.getFName());
                httpServletRequest.getRequestDispatcher("views/InstructorCourses.jsp").forward(httpServletRequest,httpServletResponse);
            }
            else if(TypeUser.equals("Admin")){
                httpServletRequest.setAttribute("FName",user.getFName());
                httpServletRequest.getRequestDispatcher("views/AdminOperation.jsp").forward(httpServletRequest,httpServletResponse);

            }
            else {
                httpServletRequest.setAttribute("errorMessage", "Email or password invalid !!");
                httpServletRequest.getRequestDispatcher("views/Login.jsp").forward(httpServletRequest,httpServletResponse);
            }

        }
        else {
            httpServletRequest.setAttribute("errorMessage", "Email or password not found in data base");
            httpServletRequest.getRequestDispatcher("views/Login.jsp").forward(httpServletRequest,httpServletResponse);
        }

    }
}
