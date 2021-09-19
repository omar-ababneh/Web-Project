import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class InstructorAction implements Action{
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session=httpServletRequest.getSession(false);
        String UID=(String) session.getAttribute("UID");
        if(UID!=null) {
            String CID = httpServletRequest.getParameter("CID");
            DataBaseDAO dataBaseDAO = DataBaseDAO.getDataBaseDAO();
            Map<Integer, StudentGrade> map = dataBaseDAO.getStudentsAndGrade(CID);
            session.setAttribute("CID", CID);
            httpServletRequest.setAttribute("Student", map);
            httpServletRequest.getRequestDispatcher("views/StudentInfo.jsp").forward(httpServletRequest, httpServletResponse);
        }
        else {
            httpServletRequest.setAttribute("errorMessage", "Please login first.");
            httpServletRequest.getRequestDispatcher("views/Login.jsp").forward(httpServletRequest,httpServletResponse);
        }
    }
}
