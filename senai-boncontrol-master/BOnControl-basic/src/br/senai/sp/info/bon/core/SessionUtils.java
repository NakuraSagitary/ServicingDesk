package br.senai.sp.info.bon.core;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionUtils {

	@Autowired
	private HttpSession session;
	
	
}
