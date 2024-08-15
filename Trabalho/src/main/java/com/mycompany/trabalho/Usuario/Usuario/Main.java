
package com.mycompany.trabalho.Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static Banco banco = new Banco();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::criarTelaInicial);
    }

    private static void criarTelaInicial() {
        JFrame frame = new JFrame("Banco");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnLogin = new JButton("Login");

        btnRegistrar.addActionListener(e -> criarTelaRegistro());
        btnLogin.addActionListener(e -> criarTelaLogin());

        frame.add(btnRegistrar);
        frame.add(btnLogin);

        frame.setVisible(true);
    }

    private static void criarTelaRegistro() {
        JFrame frame = new JFrame("Registrar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField txtEndereco = new JTextField();

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        JTextField txtNumeroConta = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();

        JButton btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String endereco = txtEndereco.getText();
            String numeroConta = txtNumeroConta.getText();
            String senha = new String(txtSenha.getPassword());

            Usuario novoUsuario = new Usuario(nome, endereco, numeroConta, senha);
            banco.adicionarUsuario(novoUsuario);
            JOptionPane.showMessageDialog(frame, "Usuário registrado com sucesso!");
            frame.dispose();
        });

        frame.add(lblNome);
        frame.add(txtNome);
        frame.add(lblEndereco);
        frame.add(txtEndereco);
        frame.add(lblNumeroConta);
        frame.add(txtNumeroConta);
        frame.add(lblSenha);
        frame.add(txtSenha);
        frame.add(new JLabel());
        frame.add(btnRegistrar);

        frame.setVisible(true);
    }

    private static void criarTelaLogin() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 2));

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        JTextField txtNumeroConta = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();

        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(e -> {
            String numeroConta = txtNumeroConta.getText();
            String senha = new String(txtSenha.getPassword());

            Usuario usuario = banco.login(numeroConta, senha);
            if (usuario != null) {
                criarTelaConta(usuario);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Número da conta ou senha incorretos.");
            }
        });

        frame.add(lblNumeroConta);
        frame.add(txtNumeroConta);
        frame.add(lblSenha);
        frame.add(txtSenha);
        frame.add(new JLabel());
        frame.add(btnLogin);

        frame.setVisible(true);
    }

    private static void criarTelaConta(Usuario usuario) {
        JFrame frame = new JFrame("Conta: " + usuario.getNumeroConta());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1));

        JButton btnConsultarSaldo = new JButton("Consultar Saldo");
        JButton btnDepositar = new JButton("Depositar");
        JButton btnSacar = new JButton("Sacar");

        btnConsultarSaldo.addActionListener(e -> {
            double saldo = usuario.consultarSaldo();
            JOptionPane.showMessageDialog(frame, "Seu saldo é: R$" + saldo);
        });

        btnDepositar.addActionListener(e -> {
            String valorStr = JOptionPane.showInputDialog("Digite o valor para depósito:");
            if (valorStr != null) {
                double valor = Double.parseDouble(valorStr);
                usuario.depositarDinheiro(valor);
                JOptionPane.showMessageDialog(frame, "Depósito realizado com sucesso!");
            }
        });

        btnSacar.addActionListener(e -> {
            String valorStr = JOptionPane.showInputDialog("Digite o valor para saque:");
            if (valorStr != null) {
                double valor = Double.parseDouble(valorStr);
                if (usuario.sacarDinheiro(valor)) {
                    JOptionPane.showMessageDialog(frame, "Saque realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Saldo insuficiente para saque.");
                }
            }
        });

        frame.add(btnConsultarSaldo);
        frame.add(btnDepositar);
        frame.add(btnSacar);

        frame.setVisible(true);
    }
}