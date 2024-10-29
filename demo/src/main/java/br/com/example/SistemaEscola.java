package br.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SistemaEscola {
    private final AlunoDAO dao = new AlunoDAO();
    private static final BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    private void exibir(Aluno aluno) {
        System.out.println("Aluno: " + aluno.getNome() +
                "\nMatricula: " + aluno.getMatricula() +
                "\tEntrada: " + aluno.getAnoEntrada() +
                "\n==============================");
    }

    public void exibirTodos() {
        List<Aluno> alunos = dao.listar();
        alunos.forEach(this::exibir);
    }

    public void inserirAluno() throws IOException {
        Aluno aluno = new Aluno();
        System.out.println("Nome:");
        aluno.setNome(entrada.readLine());
        System.out.println("Email:");
        aluno.setEmail(entrada.readLine());
        System.out.println("Matricula:");
        aluno.setMatricula(entrada.readLine());
        System.out.println("Ano de Entrada:");
        aluno.setAnoEntrada(Integer.parseInt(entrada.readLine()));
        dao.inserir(aluno);
    }

    public void excluirAluno() throws IOException {
        System.out.println("Matricula do Aluno:");
        String matricula = entrada.readLine();
        dao.excluir(matricula);
    }

    public static void main(String[] args) throws IOException {
        SistemaEscola sistema = new SistemaEscola();
        while (true) {
            System.out.println("1-Listar\t2-Inserir\t3-Excluir\t0-Sair");
            int opcao = Integer.parseInt(entrada.readLine());
            if (opcao == 0) break;
            switch (opcao) {
                case 1:
                    sistema.exibirTodos();
                    break;
                case 2:
                    sistema.inserirAluno();
                    break;
                case 3:
                    sistema.excluirAluno();
                    break;
            }
        }
    }
}
