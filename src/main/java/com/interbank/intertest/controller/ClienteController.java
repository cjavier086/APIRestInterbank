package com.interbank.intertest.controller;


import com.interbank.intertest.model.Cliente;
import com.interbank.intertest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/interbank")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/clientes/{codUnico}")
    public ResponseEntity<Cliente> findClienteByCodigo(@PathVariable("codUnico") String codigo){
        Cliente cliente = clienteRepository.findClienteByCodigo(codigo);
        if(cliente  != null){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity<String> loadCliente() throws IOException{
        try {
            clienteRepository.createTable();
            return new ResponseEntity<>("Data cargada", HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/clientes/{codUnico}")
    public ResponseEntity<String> updateClient(@PathVariable("codUnico") String codigo){
        Cliente cliente = clienteRepository.findClienteByCodigo(codigo);
        if(cliente != null){
            cliente.setNombres("Jose Luis");
            cliente.setApellidos("Suarez");
            cliente.setTipoDocumento("CNE");
            cliente.setNumeroDocumento(854126984);

            clienteRepository.updateClient(cliente);
            return new ResponseEntity<>("Cliente actualizado", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No se pudo encontrar al cliente", HttpStatus.NOT_FOUND);
        }
    }
}
