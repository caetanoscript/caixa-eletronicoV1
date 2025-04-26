package model;

import java.util.ArrayList;

public class GerenciarContas {


    public static ContaBancaria encontarContaCpf(ArrayList<ContaBancaria> conta, String cpf){

        for(ContaBancaria C: conta){
            if (C.getCliente().getCpf().equals(cpf));
            return C;
        }
        return null;
    }


}
