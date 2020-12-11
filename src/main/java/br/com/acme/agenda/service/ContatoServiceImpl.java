package br.com.acme.agenda.service;

import java.util.List;

import br.com.acme.agenda.dao.ContatoDao;
import br.com.acme.agenda.dao.ContatoDaoImpl;
import br.com.acme.agenda.model.Contato;

public class ContatoServiceImpl implements ContatoService {

	private ContatoDao contatoDao;

        // Implementação dos métodos feitos no ContatoService
	public ContatoServiceImpl() {
		contatoDao = new ContatoDaoImpl();
	}

	@Override
	public void salvar(Contato contato) {
		this.contatoDao.salvar(contato);
	}

	@Override
	public List<Contato> listarContatos() {
		return this.contatoDao.listarContatos();
	}

	@Override
	public void remover(Long idContato) {
		this.contatoDao.remover(idContato);
	}

	@Override
	public void editarContato(Long idContato, Contato contato) {
		this.contatoDao.editarContato(idContato, contato);
	}

	@Override
	public Contato buscarPorIdContato(Long idContato) {
		return this.contatoDao.buscarPorIdContato(idContato);
	}

	@Override
	public Contato buscaPorEmail(String email) {
		return this.contatoDao.buscaContatoPorEmail(email);
	}

	@Override
	public void ativarDesativarContato(Long id) {
		this.contatoDao.ativarDesativarContato(id);

	}

}
