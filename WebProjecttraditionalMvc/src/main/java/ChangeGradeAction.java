import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeGradeAction implements Action{
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    httpServletRequest.getRequestDispatcher("views/ChangeGrade.jsp").forward(httpServletRequest,httpServletResponse);
    }
}
