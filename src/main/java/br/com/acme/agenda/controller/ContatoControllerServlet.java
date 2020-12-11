package br.com.acme.agenda.controller;

import br.com.acme.agenda.model.Contato;
import br.com.acme.agenda.service.ContatoService;
import br.com.acme.agenda.service.ContatoServiceImpl;
import br.com.acme.agenda.utils.Constantes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/contatoControllerServlet")
public class ContatoControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Contato contato;
    private ContatoService service;
    private List<Contato> contatos;

    public ContatoControllerServlet() {
        this.service = new ContatoServiceImpl();
        this.contatos = new ArrayList<Contato>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idContato = request.getParameter("id");
        String actionBotao = request.getParameter("actionBotao"); // Parâmetro para ser usado no JSP para a ação do botão

        String temp = actionBotao == null ? "null" : actionBotao; // Variável temporária para a mudança nos botões

        switch (temp) {
            case "":
                // Método usado para ativar ou desativar o contato
                if (idContato != null && this.service.buscarPorIdContato(Long.parseLong(idContato)).isAtivo()) {
                    this.service.ativarDesativarContato(Long.parseLong(idContato));
                    request.setAttribute("ativardesativar", "Contato desativado com sucesso!!!");
                } else if (idContato != null && !this.service.buscarPorIdContato(Long.parseLong(idContato)).isAtivo()) {
                    this.service.ativarDesativarContato(Long.parseLong(idContato));
                    request.setAttribute("ativardesativar", "Contato ativado com sucesso!!!");
                }
                break;
            case "editar":
                // Método usado para editar os dados do contato TODO (VER A QUESTÃO PARA A MELHORA DO MÉTODO
                RequestDispatcher rdEditar = request.getRequestDispatcher(Constantes.CADASTRAR_CONTATOS);
                this.contato = this.service.buscarPorIdContato(Long.parseLong(idContato));
                request.setAttribute("contato", this.contato);
                request.setAttribute("tipo", "editar");
                rdEditar.forward(request, response);
                break;
            case "visualizar":
                // Método para visualizar os dados do contato
                RequestDispatcher rdVisualizar = request.getRequestDispatcher(Constantes.CADASTRAR_CONTATOS);
                this.contato = this.service.buscarPorIdContato(Long.parseLong(idContato));
                request.setAttribute("contato", this.contato);
                request.setAttribute("tipo", "visualizar"); // Parâmetro que será usado no JSP
                rdVisualizar.forward(request, response);
                break;
            case "excluir":
                // Método para excluir o contato
                this.service.remover(Long.parseLong(idContato));
                break;

            default:

        }

        // Método para listar todos os contatos cadastrados
        this.contatos = this.service.listarContatos();
        RequestDispatcher rd = request.getRequestDispatcher(Constantes.LISTAR_CONTATOS);
        request.setAttribute("contatos", this.contatos);
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // INSTANCIEI O CONTATO AQUI
        this.contato = new Contato();

        // RECUPEREI OS DADOS DO REQUEST / FORMUL�RIO
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");

        // SET OS ATRIBUTOS NA INSTANCIA DE CONTATO
        this.contato.setNome(nome);
        this.contato.setEmail(email);
        this.contato.setTelefone(telefone);
        this.contato.isAtivo();

        // Validação do email
        if (!validEmail(email)) {
            // CHAMO A CAMADA DE SERVICE PARA SALVAR O CONTATO
            this.service.salvar(this.contato);
            // REDIRECIONANDO PARA A LISTAGEM DE CONTATOS
            request.setAttribute("sucesso", "Contato " + nome + " cadastrado com sucesso");

        } else {
            request.setAttribute("contatoExiste", "Contato com e-mail " + email + " j� existe");
        }

        doGet(request, response);
    }

    private boolean validEmail(String email) {
        if (this.service.buscaPorEmail(email) != null) {
            return true;
        } else {
            return false;
        }
    }
}
