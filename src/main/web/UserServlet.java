package src.main.web;


import src.main.dao.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;
    @Override
    public void init() throws ServletException {
        this.userRepository =  new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action =  req.getParameter("action") == null ? "list" : req.getParameter("action") ;

        switch (action){
            case "delete":
                 int id = Integer.parseInt(req.getParameter("id"));
                 userRepository.delete(id);
                 resp.sendRedirect("?action=list");
                break;
            case "add":
                break;
            case "edit":
                break;
            case "update":
                break;
            default:
                req.setAttribute("listUser", userRepository.getAll());
                RequestDispatcher dispatcher = req.getRequestDispatcher("views/list.jsp"); //
                dispatcher.forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
