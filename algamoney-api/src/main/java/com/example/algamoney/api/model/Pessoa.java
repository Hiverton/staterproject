package com.example.algamoney.api.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pessoa_Seq")
	@SequenceGenerator(name="pessoa_Seq",sequenceName="SQ_PESSOA_01", allocationSize=1)
	private Long codigo;
	
	@NotNull
	@Size(min=3, max=60)
	private String nome;
	
	@NotNull
	@Size(min=1, max=1)
	private String ativo;
	
	@Embedded
	private Endereco endereco;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@JsonIgnore
	@Transient
	public boolean isInativo() {
		
		if ("1".equals(this.ativo))
			return false;
		return true;
	}

}
