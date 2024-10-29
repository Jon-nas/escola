package br.com.example.exercicio;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

        // Inserir um novo aluno
        Aluno novoAluno = new Aluno(1, "Jaime Silva", "jaime.silva@example.com");
        alunoDAO.inserir(novoAluno);

        // Listar todos os alunos
        List<Aluno> alunos = alunoDAO.listar();
        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Email: " + aluno.getEmail());
        }

        // Atualizar um aluno
        Aluno alunoAtualizado = new Aluno(1, "Jaime Silva", "jaime.silva@novoemail.com");
        alunoDAO.atualizar(alunoAtualizado);

        // Deletar um aluno
        alunoDAO.deletar(1);
    }
}