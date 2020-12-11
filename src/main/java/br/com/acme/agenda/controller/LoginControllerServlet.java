package br.com.acme.agenda.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		/*
		 * Verificar se o usuário é valido. Se for, direciona ele para a tela
		 * principal/dashboard Se não, direciona para a tela de falha no login.
		 */

		if (login.equals("valdir@gmail.com") && senha.equals("123")) {
                        // Usado o HttpSession para deixar o login ativo quando entra no sistema
                        HttpSession sessaoUsuario = request.getSession();
			sessaoUsuario.setAttribute("sessaoUsuario", login);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("login", login);
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("loginErro", "Cliente não existe na base de dados!"); // Parâmetro usado no JSP
			rd.forward(request, response);
		}

	}

}
