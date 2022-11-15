package com.grupo8.app.vistas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.grupo8.app.dto.OperarioDTO;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaEliminarOperario extends JFrame implements MouseListener{

	private JPanel contentPane;
	private JButton btnEliminar;
	private JList<OperarioDTO> listaOperarios;
	private JButton btnAtras ;
	private ActionListener actionListener;

	public VistaEliminarOperario() {
		setTitle("Eliminar Operario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 41, 402, 268);
		contentPane.add(scrollPane);
		
		 listaOperarios = new JList<>();
		scrollPane.setViewportView(listaOperarios);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEliminar.setBounds(276, 341, 96, 34);
		contentPane.add(btnEliminar);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(61, 341, 96, 34);
		contentPane.add(btnAtras);
		
		JLabel lblNewLabel = new JLabel("Seleccione operario a eliminar");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 0, 232, 34);
		contentPane.add(lblNewLabel);
		
		btnEliminar.setEnabled(false);
		this.listaOperarios.addMouseListener(this);
	}
	
	public void mostrar() {
		this.setVisible(true);
		btnEliminar.setEnabled(false);
	
		
	}

	public void esconder() {
		this.setVisible(false);
	}
	
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
public void setActionListener(ActionListener actionListener) {
		
        this.btnAtras.addActionListener(actionListener);
        this.btnEliminar.addActionListener(actionListener);
        this.actionListener = actionListener;
	}


public void setListaOperarios(OperarioDTO[] operarios) {
	this.listaOperarios.setListData(operarios);
}


public  OperarioDTO getOperario() {
	return this.listaOperarios.getSelectedValue();
}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	this.btnEliminar.setEnabled(this.listaOperarios.getSelectedValue()!=null);
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

}
