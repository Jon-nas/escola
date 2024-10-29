package br.com.example;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private String matricula;
    private int anoEntrada;

    public Aluno() {}

    public Aluno(int id, String nome, String email, String matricula, int anoEntrada) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.anoEntrada = anoEntrada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAnoEntrada() {
        return anoEntrada;
    }

    public void setAnoEntrada(int anoEntrada) {
        this.anoEntrada = anoEntrada;
    }
}
