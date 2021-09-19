import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class SaveChangeGradeAction implements Action{

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session=httpServletRequest.getSession(false);
        String Grade=httpServletRequest.getParameter("Grade");
        int SID= Integer.parseInt(httpServletRequest.getParameter("SID"));
        String CID= (String) session.getAttribute("CID");
        if(CID==null){
            httpServletRequest.setAttribute("errorMessage", "Please login first.");
            httpServletRequest.getRequestDispatcher("views/Login.jsp").forward(httpServletRequest,httpServletResponse);
        }else {
            DataBaseDAO dataBaseDAO=DataBaseDAO.getDataBaseDAO();
            dataBaseDAO.ChangeGrade(Grade,CID,SID);
            Map<Integer,StudentGrade> map= dataBaseDAO.getStudentsAndGrade(CID);
            httpServletRequest.setAttribute("Student",map);
            httpServletRequest.setAttribute("Message","saved Successfully");
            httpServletRequest.getRequestDispatcher("views/StudentInfo.jsp").forward(httpServletRequest,httpServletResponse);
        }
    }
}
