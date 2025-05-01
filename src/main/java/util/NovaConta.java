package util;

import model.ContaBancaria;

import java.util.ArrayList;

public class NovaConta {

    private String nome;
    private String cpf;
    private String senha;
    private String senha2;

    public NovaConta(String nome, String senha, String senha2, String cpf) {
        this.nome = nome;
        this.senha = senha;
        this.senha2 = senha2;
        this.cpf = cpf;
    }

   public boolean cpfExiste (ArrayList<ContaBancaria> contas){
        for (ContaBancaria c : contas){
            if(c.getCliente().getCpf().equals(this.cpf)){
                return true;
            }
        }
       return false;
   }

    public boolean verificarSenha() {
        return senha.equals(senha2);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
