package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Parcelamento {
	
		
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	
	    @ManyToOne
//	    @JoinColumn(name = "cliente_id")
	    private Cliente cliente;

	    
	    private String descricao;
	    
	    
	    private BigDecimal valorTotal;
	    
	    private Integer quantidadeParcelas;
	    
	    private LocalDateTime dataCriacao;
	    
	    public Parcelamento() {
	    }
		public Parcelamento(Long id, Cliente cliente, String descricao, BigDecimal valorTotal,
				Integer quantidadeParcelas, LocalDateTime dataCriacao) {
			super();
			this.id = id;
			this.cliente = cliente;
			this.descricao = descricao;
			this.valorTotal = valorTotal;
			this.quantidadeParcelas = quantidadeParcelas;
			this.dataCriacao = dataCriacao;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public BigDecimal getValorTotal() {
			return valorTotal;
		}
		public void setValorTotal(BigDecimal valorTotal) {
			this.valorTotal = valorTotal;
		}
		public Integer getQuantidadeParcelas() {
			return quantidadeParcelas;
		}
		public void setQuantidadeParcelas(Integer quantidadeParcelas) {
			this.quantidadeParcelas = quantidadeParcelas;
		}
		public LocalDateTime getDataCriacao() {
			return dataCriacao;
		}
		public void setDataCriacao(LocalDateTime dataCriacao) {
			this.dataCriacao = dataCriacao;
		}
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Parcelamento other = (Parcelamento) obj;
			return Objects.equals(id, other.id);
		}
	    
	    

	}


