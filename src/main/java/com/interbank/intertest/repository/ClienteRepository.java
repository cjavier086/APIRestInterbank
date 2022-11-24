package com.interbank.intertest.repository;

import com.interbank.intertest.model.Cliente;

import java.io.IOException;

public interface ClienteRepository {
    Cliente findClienteByCodigo(String codUnico);

    int updateClient(Cliente cliente);

    void createTable() throws IOException;

}
