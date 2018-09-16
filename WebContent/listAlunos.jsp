<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" 
  import="java.util.List, bancoDAO.OperationDAO, model.Aluno"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Exibicao de Alunos</title>
</head>
<body>
    <TABLE BORDER=1>
<TR>
<TD>ID</TD>
<TD>NOME</TD>
<TD>ENDERECO</TD>
<TD>E-MAIL</TD>
<TD>Remover</TD>
</TR>
<%
String  pesq=request.getParameter("id");
if (pesq==null) pesq="";

OperationDAO alunosDAO = new OperationDAO();
try {
	List<Aluno> alunos= alunosDAO.getAllAlunos();
	for (Aluno aluno : alunos) {
		out.println(
		"<TR>"+
		"<TD>"+aluno.getId()+"</TD>"+
		"<TD><a href=\"/Prova_3_bimestre/AlunoController?action=edit&id="+aluno.getId()+"\">"+aluno.getNome()+"</a></TD>"+
				"<TD>"+aluno.getEndereco()+"</TD>"+
				"<TD>"+aluno.getEmail()+"</TD>"+
				
						"<TD><a href=\"/Prova_3_bimestre/AlunoController?action=delete&id="+aluno.getId()+"\">Remover</a></TD>"
		+"</TR>");
	}
} catch ( Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

%>

</TABLE>
    <p><a href="AlunoController?action=insert">Inserir Aluno</a></p>
</body>
</html>