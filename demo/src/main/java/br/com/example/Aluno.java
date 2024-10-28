package br.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    public String matricula;
    public String nome;
    public int ano;

    public Aluno() {
    }

    public Aluno(String matricula, String nome, int ano) {
        this.matricula = matricula;
        this.nome = nome;
        this.ano = ano;
    }

    public static void main(String[] args) {
        List<Aluno> lista = new ArrayList<>();

        try {
            // Carregar o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conectar ao banco de dados
            Connection c1 = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/loja", "loja", "loja");

            // Criar uma declaração
            Statement st = c1.createStatement();

            // Executar a consulta
            ResultSet r1 = st.executeQuery("SELECT * FROM ALUNO");

            // Processar os resultados
            while (r1.next()) {
                lista.add(new Aluno(r1.getString("MATRICULA"),
                        r1.getString("NOME"), r1.getInt("ENTRADA")));
            }

            // Fechar os recursos
            r1.close();
            st.close();
            c1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Imprimir os resultados
        for (Aluno aluno : lista) {
            System.out.println("Aluno: " + aluno.nome +
                    " (" + aluno.matricula + ") - " + aluno.ano);
        }
    }
}