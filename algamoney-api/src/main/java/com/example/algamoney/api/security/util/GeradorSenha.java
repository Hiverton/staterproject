package com.example.algamoney.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(Messages.getString("GeradorSenha.0")); //$NON-NLS-1$
		System.out.println();
		
		String senhaencodada = encoder.encode(Messages.getString("GeradorSenha.1")); //$NON-NLS-1$
		System.out.println(senhaencodada);

	}
}
