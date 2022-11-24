package com.interbank.intertest.repository;

import com.interbank.intertest.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;


@Repository
public class ClienteRepositoryImpl implements ClienteRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Cliente findClienteByCodigo(String codigo) {
        try {
            Cliente cliente = jdbcTemplate.queryForObject("SELECT * FROM CLIENTES WHERE CODUNICO LIKE CONCAT ('%', ?, '%')",
                    BeanPropertyRowMapper.newInstance(Cliente.class), codigo);
            return cliente;
        }catch(IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public int updateClient(Cliente cliente) {
        return jdbcTemplate.update("UPDATE CLIENTES SET NOMBRES=?, APELLIDOS=?, TIPODOCUMENTO=?, NUMERODOCUMENTO=? WHERE CODUNICO LIKE CONCAT ('%', ?, '%')",
                new Object[]{cliente.getNombres(), cliente.getApellidos(), cliente.getTipoDocumento(), cliente.getNumeroDocumento(), cliente.getCodUnico()});
    }

    @Override
    public void createTable() throws IOException {

        try {
            String sql = "CREATE TABLE clientes(ID INT PRIMARY KEY, CODUNICO VARCHAR(255), NOMBRES VARCHAR(255), APELLIDOS VARCHAR(255), TIPODOCUMENTO VARCHAR(10), NUMERODOCUMENTO INT);";
            jdbcTemplate.execute(sql);
            System.out.println("Tabla creada");
            sql = "INSERT INTO clientes VALUES(1, '123', 'juan', 'perez', 'dni', 12345678);";
            jdbcTemplate.execute(sql);
            sql = "INSERT INTO clientes VALUES(2, 'N24', 'pedro', 'lopez', 'dni', 87452104);";
            jdbcTemplate.execute(sql);
            System.out.println("Filas insertadas");
        }catch (IncorrectResultSizeDataAccessException e){
            e.printStackTrace();
        }


    }
}
