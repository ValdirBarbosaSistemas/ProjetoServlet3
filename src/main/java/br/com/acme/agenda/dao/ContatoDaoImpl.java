package br.com.acme.agenda.dao;

import br.com.acme.agenda.model.Contato;
import br.com.acme.agenda.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ContatoDaoImpl implements ContatoDao {

        // Implementação dos métodos feitos no ContatoDao
	@Override
	public void salvar(Contato contato) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(contato);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public List<Contato> listarContatos() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Query consulta = entityManager.createQuery("SELECT c FROM Contato c");
		List<Contato> listaDeContatosDoBancoDeDados = consulta.getResultList();
		return listaDeContatosDoBancoDeDados;
	}

	@Override
	public void remover(Long idContato) {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Contato contato = entityManager.find(Contato.class, idContato);
		entityManager.remove(contato);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public Contato buscarPorIdContato(Long idContato) {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Contato contato = entityManager.find(Contato.class, idContato);
		entityManager.getTransaction().commit();
		entityManager.close();
		return contato;
	}

	@Override
	public void editarContato(Long idContato, Contato contato) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Contato contatoQueTavaNoBanco = entityManager.find(Contato.class, idContato);
		contatoQueTavaNoBanco.setAtivo(contato.isAtivo());
		contatoQueTavaNoBanco.setEmail(contato.getEmail());
		contatoQueTavaNoBanco.setTelefone(contato.getTelefone());
		contatoQueTavaNoBanco.setNome(contato.getNome());

		entityManager.merge(contatoQueTavaNoBanco);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public Contato buscaContatoPorEmail(String email) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			return entityManager.createNamedQuery("Contato.buscaContatoPorEmail", Contato.class)
					.setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public void ativarDesativarContato(Long id) {

                // Implementação de Ativar/Desativar o contato
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Contato contatoQueTavaNoBanco = entityManager.find(Contato.class, id);
		if (contatoQueTavaNoBanco != null && contatoQueTavaNoBanco.isAtivo()) {
			contatoQueTavaNoBanco.setAtivo(false);
		} else if (contatoQueTavaNoBanco != null && !contatoQueTavaNoBanco.isAtivo()) {
			contatoQueTavaNoBanco.setAtivo(true);
		}
		entityManager.merge(contatoQueTavaNoBanco);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}