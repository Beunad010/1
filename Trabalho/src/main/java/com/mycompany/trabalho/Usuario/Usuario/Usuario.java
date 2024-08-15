package com.mycompany.trabalho.Usuario.Usuario;

public class Usuario {
    private String nome;
    private String endereco;
    private String numeroConta;
    private String senha;
    private double saldo;

    public Usuario(String nome, String endereco, String numeroConta, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.saldo = 0.0;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void depositarDinheiro(double quantia) {
        this.saldo += quantia;
    }

    public boolean sacarDinheiro(double quantia) {
        if (saldo >= quantia) {
            this.saldo -= quantia;
            return true;
        } else {
            return false;
        }
    }
}