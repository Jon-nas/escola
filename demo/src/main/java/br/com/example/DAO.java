package br.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    protected Connection connect() {
        String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
        String user = "seu_usuario";
        String password = "sua_senha";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public abstract List<T> listar();
    public abstract void inserir(T t);
    public abstract void atualizar(T t);
    public abstract void deletar(int id);
}
