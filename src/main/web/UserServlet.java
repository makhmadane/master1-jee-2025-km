package src.main.web;


import src.main.dao.UserRepository;
import src.main.entity.User;

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
        RequestDispatcher dispatcher ;
        switch (action){
            case "delete":
                 int id = Integer.parseInt(req.getParameter("id"));
                 userRepository.delete(id);
                 resp.sendRedirect("?action=list");
                break;
            case "add":
                dispatcher = req.getRequestDispatcher("views/add.jsp");
                dispatcher.forward(req,resp);
                break;
            case "save":

                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                User user = userRepository.getById(idEdit);
                req.setAttribute("user",user);
                dispatcher = req.getRequestDispatcher("views/edit.jsp");
                dispatcher.forward(req,resp);
                break;
            case "update":
                break;
            default:
                req.setAttribute("listUser", userRepository.getAll());
                dispatcher = req.getRequestDispatcher("views/list.jsp"); //
                dispatcher.forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        User user;
        RequestDispatcher dispatcher;
        switch (action) {
            case "save":
                user = User.builder()
                        .prenom(req.getParameter("prenom"))
                        .nom(req.getParameter("nom"))
                        .age(Integer.parseInt(req.getParameter("age")))
                        .build();
                userRepository.insert(user);
                resp.sendRedirect("?action=list");
                break;
            case "update":
                 user = User.builder()
                        .id(Integer.parseInt(req.getParameter("id")))
                        .prenom(req.getParameter("prenom"))
                        .nom(req.getParameter("nom"))
                        .age(Integer.parseInt(req.getParameter("age")))
                        .build();
                 userRepository.update(user);
                resp.sendRedirect("?action=list");
                break;
        }

    }


}
