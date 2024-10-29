package br.com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends DAO<Aluno> {
    @Override
    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setAnoEntrada(rs.getInt("anoEntrada"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return alunos;
    }

    @Override
    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO Aluno(nome, email, matricula, anoEntrada) VALUES(?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getEmail());
            pstmt.setString(3, aluno.getMatricula());
            pstmt.setInt(4, aluno.getAnoEntrada());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try (Connection conn = this.connect()) {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE Aluno SET nome = ?, email = ?, matricula = ?, anoEntrada = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getEmail());
            pstmt.setString(3, aluno.getMatricula());
            pstmt.setInt(4, aluno.getAnoEntrada());
            pstmt.setInt(5, aluno.getId());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try (Connection conn = this.connect()) {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM Aluno WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try (Connection conn = this.connect()) {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void excluir(String chave) {
        Connection conn = null;
        try {
            conn = this.connect();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Aluno WHERE matricula = ?");
            ps.setString(1, chave);
            ps.executeUpdate();
            conn.commit();
            ps.close();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    conn.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }
}