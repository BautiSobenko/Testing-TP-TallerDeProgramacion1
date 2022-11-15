package com.grupo8.app.vistas;

import java.awt.event.ActionListener;

public interface ILogin {
	String getUsername();

	String getContrasena();

	void setActionListener(ActionListener actionListener);

	void entrar();

	void usuarioNoEncontrado();

	void contrasenaIncorrecta();

	void mostrar();

	void esconder();
	void error(String error, String titulo);
	
	void limpiaCampos();
}
