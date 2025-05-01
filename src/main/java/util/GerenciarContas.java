package util;

import model.ContaBancaria;

import java.util.ArrayList;

public class GerenciarContas {


    public static ContaBancaria encontarContaCpf(ArrayList<ContaBancaria> conta, String cpf) {
        for (ContaBancaria C : conta) {
            if (C.getCliente().getCpf().equals(cpf)) {
                return C;
            }

        }
        return null;
    }
    public static boolean cpfExiste(ArrayList<ContaBancaria> contas, String cpf) {
        for (ContaBancaria c : contas) {
            if (c.getCliente().getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}
