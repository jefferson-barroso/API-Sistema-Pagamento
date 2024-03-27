package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NegocioException;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	//implementando com argumentos
	@Autowired
	private final ClienteRepository clienteRepository;
		
	public CadastroClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	

	 public Cliente buscar(Long clienteId) {
	        return clienteRepository.findById(clienteId)
	                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	    }

	 
	@Transactional //faz direto na persistencia do banco, se der erro, volta
	public Cliente salvar (Cliente cliente) {
		
		//regra de negocio para evitar email duplicado
		//apos colocar um novo metodo de procura no repository
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				//filtrar pra atualizar o email caso ja exista
				.filter(c -> !c.equals(cliente))
				.isPresent();
		
		if(emailEmUso) {
			//criamos uma exception personalizada
			throw new NegocioException("Já existe cadastro nessa área");
		}
		
		
		return clienteRepository.save(cliente);
}
	@Transactional
	public void  excluir (Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}


	

}
