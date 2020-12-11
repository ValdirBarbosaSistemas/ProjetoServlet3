package br.com.acme.agenda.service;

import java.util.List;

import br.com.acme.agenda.model.Contato;

public interface ContatoService {

	public void salvar(Contato contato);

	public List<Contato> listarContatos();

	public void remover(Long idContato);
	
	public void editarContato(Long idContato, Contato contato);

	public Contato buscarPorIdContato(Long idContato);
	
	public Contato buscaPorEmail(String email);
	
	public void ativarDesativarContato(Long id);

}
