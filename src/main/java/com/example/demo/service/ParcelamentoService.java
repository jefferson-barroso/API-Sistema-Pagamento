package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NegocioException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Parcelamento;
import com.example.demo.repository.ParcelamentoRepository;



@Service
public class ParcelamentoService {
	
	@Autowired
    private  ParcelamentoRepository parcelamentoRepository;
	@Autowired
    private  CadastroClienteService cadastroClienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento) {
        if (novoParcelamento.getId() != null) {
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
        }

        Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());

        novoParcelamento.setCliente(cliente);
        novoParcelamento.setDataCriacao(LocalDateTime.now());

        return parcelamentoRepository.save(novoParcelamento);
    }

}