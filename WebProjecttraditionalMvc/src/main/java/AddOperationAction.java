import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddOperationAction implements Action {
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        User user = new User();
        user.setFName(httpServletRequest.getParameter("FName"));
        user.setEmail(httpServletRequest.getParameter("email"));
        user.setPassword(String.valueOf(httpServletRequest.getParameter("psw")));
        user.setFID(Integer.parseInt((String) session.getAttribute("UID")));
        DataBaseDAO dataBaseDAO=DataBaseDAO.getDataBaseDAO();
        String action = httpServletRequest.getServletPath().substring(1);
        if (action.equals("AddStudent.do")) {
            int status = dataBaseDAO.AddStudent(user);
        } else if (action.equals("AddiInstructor.do")) {
            int status = dataBaseDAO.AddInstructor(user);
        } else if (action.equals("AddAdmin.do")) {
            int status = dataBaseDAO.AddAdmin(user);
        }
    }
}
