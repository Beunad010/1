package com.mycompany.trabalho.Usuario.Usuario;

import java.util.ArrayList;

public class Banco {
    private ArrayList<Usuario> usuarios;

    public Banco() {
        usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String numeroConta) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNumeroConta().equals(numeroConta)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario login(String numeroConta, String senha) {
        Usuario usuario = buscarUsuario(numeroConta);
        if (usuario != null && usuario.autenticar(senha)) {
            return usuario;
        }
        return null;
    }
}