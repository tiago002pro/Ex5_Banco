package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.ClientePF;
import com.example.demo.model.ClientePJ;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public String cadastraCliente(Character pessoa, Map<String, String> json) {
        if (pessoa == 'f') {
            ClientePF clientePF = new ClientePF(
                    "pf",
                    json.get("nome"),
                    json.get("endereco"),
                    json.get("cpf"),
                    json.get("rg")
            );
            this.repository.save(clientePF);
            return "Cadastrado com sucesso!";

        } else if (pessoa == 'j') {
            ClientePJ clientePJ = new ClientePJ(
                    "pj",
                    json.get("nome"),
                    json.get("endereco"),
                    json.get("cnpj")
            );
            this.repository.save(clientePJ);
            return "Cadastrado com sucesso!";

        } else {
            return "Não foi possível localizar o tipo de cliente para cadastro. Verifique a URL e tente novamente";
        }
    }

    public List<Cliente> getClientes() {
        return this.repository.findAll();
    }

    public Cliente getCliente(Long id) {
        return this.repository.findById(id).get();
    }

    public List<? extends Cliente> pegaListaClientes(Character pessoa) {
        if(pessoa == 'f') {
            return this.repository.findAllPF();
        }
        if (pessoa == 'j') {
            return this.repository.findAllPJ();
        }
        return this.repository.findAll();
    }

}
