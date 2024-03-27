package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NegocioException;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.CadastroClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CadastroClienteService cadastroClienteService;
	

	public ClienteController(ClienteRepository clienteRepository, CadastroClienteService cadastroClienteService) {
		this.clienteRepository = clienteRepository;
		this.cadastroClienteService = cadastroClienteService;
	}



	@GetMapping
    public List<Cliente> listar() {
    	return clienteRepository.findAll();
    	
    }
	
	@GetMapping("/{clienteId}")
			//aconselhavel a usar o RReponse para poder manipular cabeçalho, resposta
			// de eventuais erros, usar os códigos https coretamnetee etc.
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		

		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
	return ResponseEntity.notFound().build();
	
	//obs -> build retorna uma resonseEntity, nesse caso, o notfound
	}
    
	
	//isso facilita para melhor redirecionamento do código http, caso seja criado
	@ResponseStatus(HttpStatus.CREATED)
	//no post, lembrar que a requisição é no corpo
	//lembrar que na requisição JSON, deve ter mesmos atributos pro preenchimento	
	@PostMapping
	public Cliente cliente (@Valid @RequestBody Cliente cliente) {
		
		//serializado em JSON, traduzido para Java
		return cadastroClienteService.salvar(cliente);
		
	}
	
	//O PUT, atualiza uma info atraves da URI
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId); //<- evita de criar um novo cliente a cada atualização
		cliente = cadastroClienteService.salvar(cliente);		
		return ResponseEntity.ok(cliente);
	}
	
	//delete deleta
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir( @PathVariable Long clienteId) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
			}
		cadastroClienteService.excluir(clienteId);
		
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();
	}
	
	
}
