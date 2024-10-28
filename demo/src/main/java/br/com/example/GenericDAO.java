package br.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@SuppressWarnings("unused")
public abstract class GenericDAO<E, K> {

    protected Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/escola", "escola", "escola");
    }

    protected Statement getStatement() throws Exception {
        return getConnection().createStatement();
    }

    protected void closeStatement(Statement st) throws Exception {
        st.getConnection().close();
    }

    public abstract void incluir(E entidade);

    public abstract void excluir(K chave);

    public abstract void alterar(E entidade);

    public abstract E obter(K chave);

    public abstract List<E> obterTodos();
}