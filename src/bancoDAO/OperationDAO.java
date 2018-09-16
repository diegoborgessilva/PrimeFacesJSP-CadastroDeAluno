package bancoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import util.DataBase;

public class OperationDAO {

    private Connection connection;

    public OperationDAO() throws SQLException {
        connection = DataBase.getConnection();
    }

    public void inserirAluno(Aluno aluno) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into alunos(nome,endereco,email) values (?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getEndereco());          
            preparedStatement.setString(3, aluno.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAluno(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from alunos where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAluno(Aluno aluno) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update alunos set nome=?, endereco=?, email=?" +
                            "where id=?");           
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getEndereco());          
            preparedStatement.setString(3, aluno.getEmail());
            preparedStatement.setInt(4, aluno.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> getAllAlunos() {
    	List<Aluno> AlunoFinal = new ArrayList<Aluno>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from alunos");
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno .setNome(rs.getString("nome"));
                aluno.setEndereco(rs.getString("endereco"));               
                aluno.setEmail(rs.getString("email"));
                AlunoFinal.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AlunoFinal;
    }

    public Aluno getAlunoPORId(int id) {
    	 Aluno aluno = new Aluno();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from alunos where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                aluno.setId(rs.getInt("id"));
                aluno .setNome(rs.getString("nome"));
                aluno.setEndereco(rs.getString("endereco"));               
                aluno.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }
}