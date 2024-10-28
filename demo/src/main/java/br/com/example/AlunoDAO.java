package br.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AlunoDAO extends GenericDAO<Aluno, String> {

    @Override
    public List<Aluno> obterTodos() {
        List<Aluno> lista = new ArrayList<>();
        try {
            ResultSet r1 = getStatement().executeQuery("SELECT * FROM ALUNO");
            while (r1.next()) {
                lista.add(new Aluno(r1.getString("MATRICULA"),
                        r1.getString("NOME"), r1.getInt("ENTRADA")));
            }
            closeStatement(r1.getStatement());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Aluno obter(String chave) {
        Aluno aluno = null;
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT * FROM ALUNO WHERE MATRICULA = ?");
            ps.setString(1, chave);
            ResultSet r1 = ps.executeQuery();
            if (r1.next()) {
                aluno = new Aluno(r1.getString("MATRICULA"),
                        r1.getString("NOME"), r1.getInt("ENTRADA"));
            }
            closeStatement(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aluno;
    }

    @Override
    public void incluir(Aluno entidade) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "INSERT INTO ALUNO (MATRICULA, NOME, ENTRADA) VALUES (?, ?, ?)");
            ps.setString(1, entidade.matricula);
            ps.setString(2, entidade.nome);
            ps.setInt(3, entidade.ano);
            ps.executeUpdate();
            closeStatement(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(String chave) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "DELETE FROM ALUNO WHERE MATRICULA = ?");
            ps.setString(1, chave);
            ps.executeUpdate();
            closeStatement(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(Aluno entidade) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "UPDATE ALUNO SET NOME = ?, ENTRADA = ? WHERE MATRICULA = ?");
            ps.setString(1, entidade.nome);
            ps.setInt(2, entidade.ano);
            ps.setString(3, entidade.matricula);
            ps.executeUpdate();
            closeStatement(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AlunoDAO dao = new AlunoDAO();
        List<Aluno> alunos = dao.obterTodos();
        alunos.forEach(aluno -> {
            System.out.println(aluno.nome);
        });
    }
}