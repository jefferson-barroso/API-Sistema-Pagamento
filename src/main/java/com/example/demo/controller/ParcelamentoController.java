package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ParcelamentoDTO;
import com.example.demo.exception.NegocioException;
import com.example.demo.model.Parcelamento;
import com.example.demo.repository.ParcelamentoRepository;
import com.example.demo.service.ParcelamentoService;

@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {
	
	@Autowired
    private  ParcelamentoRepository parcelamentoRepository;
	@Autowired
    private ParcelamentoService parcelamentoService;
	
	@Autowired
	private ModelMapper modelMapper;
    

    @GetMapping
    public List<Parcelamento> listar() {
        return parcelamentoRepository.findAll();
    }
    
    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoDTO> buscar(@PathVariable Long parcelamentoId) {
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamento -> modelMapper.map(parcelamento, ParcelamentoDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar( @RequestBody Parcelamento parcelamento) {
        return parcelamentoService.cadastrar(parcelamento);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
