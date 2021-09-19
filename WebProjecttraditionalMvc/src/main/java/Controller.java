import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(urlPatterns = "*.do")
public class Controller extends HttpServlet {
    Map<String,Action> actions;
    @Override
    public void init() throws ServletException {
        actions=new TreeMap<>();
        actions.put("Login.do",new LoginAction());
        actions.put("ViewProcess.do",new ProcessLoginAction());
        actions.put("CourseInfo.do",new InstructorAction());
        actions.put("SaveChangeGrade.do",new SaveChangeGradeAction());
        actions.put("Logout.do",new LogoutAction());
        actions.put("ViewAddStudent.do",new ViewOperationAdminAction());
        actions.put("ViewAddInstructor.do",new ViewOperationAdminAction());
        actions.put("ViewAddAdmin.do",new ViewOperationAdminAction());
        actions.put("AddStudent.do",new AddOperationAction());
        actions.put("AddiInstructor.do",new AddOperationAction());
        actions.put("AddAdmin.do",new AddOperationAction());
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String action=httpServletRequest.getServletPath().substring(1);
        Action action1= actions.get(action);
        action1.execute(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String action=httpServletRequest.getServletPath().substring(1);
        Action action1= actions.get(action);
        action1.execute(httpServletRequest,httpServletResponse);
    }
}
