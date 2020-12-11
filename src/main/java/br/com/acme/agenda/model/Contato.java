package br.com.acme.agenda.model;

import javax.persistence.*;

@Entity
@Table(name = "contatos")
@NamedQuery(name = "Contato.buscaContatoPorEmail", query = "select c from Contato c where c.email = :email")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_contato")
	private String nome;

	@Column(name = "email_contato")
	private String email;

	@Column(name = "telefone_contato")
	private String telefone;
	
	private boolean ativo = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
		
}
