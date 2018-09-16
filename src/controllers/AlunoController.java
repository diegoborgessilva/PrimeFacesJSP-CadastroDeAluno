package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bancoDAO.OperationDAO;
import model.Aluno;

public class AlunoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Aluno.jsp";
    private static String LIST_ALUNO = "/listAlunos.jsp";
    private OperationDAO dao;

    public AlunoController() throws SQLException {
        super();
        dao = new OperationDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
    IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int Id = Integer.parseInt(request.getParameter("id"));
            dao.deleteAluno(Id);
            forward = LIST_ALUNO;
            request.setAttribute("alunos", dao.getAllAlunos());  
            //response.sendRedirect("/Prova_3_bimestre/listAlunos.jsp");
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Aluno aluno = dao.getAlunoPORId(id);
            request.setAttribute("alunos", aluno);
        } else if (action.equalsIgnoreCase("listAluno")){
            forward = LIST_ALUNO;
            request.setAttribute("alunos", dao.getAllAlunos());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Aluno aluno = new Aluno();
       aluno.setNome(request.getParameter("nome")); 
       aluno.setEndereco(request.getParameter("endereco")); 
       
        aluno.setEmail(request.getParameter("email"));
        String id = request.getParameter("id");
        if(id == null || id.isEmpty())
        {
            dao.inserirAluno(aluno);
        }
        else
        {
        	System.out.println("update -------------------------"+Integer.parseInt(id));
            aluno.setId(Integer.parseInt(id));
            dao.updateAluno(aluno);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_ALUNO);
        request.setAttribute("Alunos", dao.getAllAlunos());
        view.forward(request, response);
    }
}