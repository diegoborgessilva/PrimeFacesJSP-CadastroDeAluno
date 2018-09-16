<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List, bancoDAO.OperationDAO, model.Aluno"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new user</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		if (id == null) {
	%>
	<form method="POST" action='AlunoController' name="frmAddUsr">
		<span>NOME:</span><br> <input
			id="nome" name="nome" type="text" value="" /><br> <span>Endereco:</span><br>
		<input id="endereco" name="endereco" type="text" value="" /><br>
		<span>E-MAIL:</span><br> <input id="email" name="email"
			type="text" value="" /><br> <input type="submit" value="Submit" />
	</form>

	<%
		} else {
%>
<form method="POST" action='AlunoController' name="frmEditUsr">
<%
			OperationDAO alunosDAO = new OperationDAO();
			try {
				List<Aluno> alunos;
				Aluno alunoEdit = new Aluno();
				alunos = alunosDAO.getAllAlunos();
				for (Aluno aluno : alunos) {
					if (aluno.getIds().equals(id)) {
						alunoEdit = aluno;
						break;
					}
				}
				out.println("<span>CODIGO:</span><br>"
						+ "<input readonly name=\"id\" type=\"text\" value=\""
						+ alunoEdit.getId()
						+ "\"><br>"
						+ "<span>NOME:</span><br>"
						+ "<input name=\"nome\" type=\"text\" value=\""
						+ alunoEdit.getNome()
						+ "\"><br>"
						+ "<span>E-MAIL:</span><br>"
						+ "<input name=\"email\" type=\"email\" value=\""
						+ alunoEdit.getEmail()
						+ "\"><br>"
						+ "<span>Endereco:</span><br>"
						+ "<input name=\"endereco\" type=\"text\" value=\""
						+ alunoEdit.getEndereco() + "\"><br>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	%>
	<button type="submit">OK</button>
	</form>


	<%
		}
	%>
</body>
</html>