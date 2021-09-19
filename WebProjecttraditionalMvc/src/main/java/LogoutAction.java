import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutAction implements Action{
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session=httpServletRequest.getSession();
        session.invalidate();
        httpServletRequest.getRequestDispatcher("views/Login.jsp").include(httpServletRequest,httpServletResponse);
    }
}
