package br.senai.sp.info.bon.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.info.bon.core.SessionUtils;

@Component
public class AutenticacaoPorSessaoInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SessionUtils session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean necessitaAutenticacao = request.getRequestURI().contains("/app");
		if (necessitaAutenticacao) {

			
		}

		// Se não passou nos filtros acima, libera acesso
		return true;
	}

}
