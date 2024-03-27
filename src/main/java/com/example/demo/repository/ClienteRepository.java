package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;
//define que esta interface, faz parte do componente spring; 
// isso facilita a injeção de dependencia.
@Repository
									//classe a gerenciada, identificador				
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//sempre começar por findBy + atributo para procurar
	public List<Cliente> findByNome(String nome);
	Optional<Cliente> findByEmail(String email);
}
