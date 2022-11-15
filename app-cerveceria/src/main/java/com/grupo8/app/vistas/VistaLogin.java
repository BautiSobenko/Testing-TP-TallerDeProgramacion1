package com.grupo8.app.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Component;

public class VistaLogin extends JFrame implements KeyListener, ILogin {

	private JPanel contentPane;
	private JTextField usernameField;
	private JButton btnEntrar;
	private ActionListener actionListener;
	private String username;
	private String contrasena;
	private JPasswordField contrasenaField;

	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelCentro.add(lblUserName);

		JPanel panel_1 = new JPanel();
		panelCentro.add(panel_1);

		usernameField = new JTextField();
		usernameField.addKeyListener(this);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(usernameField);
		usernameField.setColumns(20);

		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelCentro.add(lblContrasena);

		JPanel panel = new JPanel();
		panelCentro.add(panel);

		contrasenaField = new JPasswordField();
		contrasenaField.addKeyListener(this);
		panel.setLayout(new BorderLayout(0, 0));
		contrasenaField.setColumns(20);
		panel.add(contrasenaField);

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(2, 1, 0, 0));

		btnEntrar = new JButton("Entrar");
		btnEntrar.setActionCommand("LOGIN");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEntrar.setEnabled(false);
		panelSur.add(btnEntrar);

		this.setVisible(true);
		
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		this.username = this.usernameField.getText();
		this.contrasena = new String(this.contrasenaField.getPassword());
		this.btnEntrar.setEnabled(username.length() > 0 && contrasena.length() > 0);
	}

	public void keyTyped(KeyEvent e) {
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getContrasena() {
		return this.contrasena;
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnEntrar.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	public void usuarioNoEncontrado() {
		JOptionPane.showMessageDialog(this, "Usuario no encontrado");
	}

	public void contrasenaIncorrecta() {
		JOptionPane.showMessageDialog(this, "Contrasena incorrecta");
	}

	@Override
	public void entrar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrar() {
		this.setVisible(true);
	}

	@Override
	public void esconder() {
		this.setVisible(false);
	}

	@Override
	public void error(String error, String titulo) {
		JOptionPane.showMessageDialog(null, error, titulo, JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void limpiaCampos() {
		usernameField.setText("");
		contrasenaField.setText("");
		username = null;
		contrasena = null;
	}
}
