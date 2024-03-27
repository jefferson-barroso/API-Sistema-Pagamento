package com.example.demo.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class ParcelamentoDTO {
    private Long id;
    private String nomeCliente;
    private String descricao;
    private BigDecimal valorTotal;
    private Integer parcelas;
    private LocalDateTime dataCriacao;
    
    
    
    
    public ParcelamentoDTO() {}
    
	public ParcelamentoDTO(Long id, String nomeCliente, String descricao, BigDecimal valorTotal, Integer parcelas,
			LocalDateTime dataCriacao) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.parcelas = parcelas;
		this.dataCriacao = dataCriacao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
	public Integer getParcelas() {
		return parcelas;
	}
	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
