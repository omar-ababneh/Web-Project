import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewOperationAdminAction implements Action{
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String action=httpServletRequest.getServletPath().substring(1);
        if(action.equals("ViewAddStudent.do")){
            httpServletRequest.getRequestDispatcher("views/ViewAddStudent.jsp").forward(httpServletRequest,httpServletResponse);
        }else if(action.equals("ViewAddInstructor.do")){
            httpServletRequest.getRequestDispatcher("views/ViewAddInstructor.jsp").forward(httpServletRequest,httpServletResponse);
        }else if(action.equals("ViewAddAdmin.do")){
            httpServletRequest.getRequestDispatcher("views/ViewAddAdmin.jsp").forward(httpServletRequest,httpServletResponse);
        }

    }
}
