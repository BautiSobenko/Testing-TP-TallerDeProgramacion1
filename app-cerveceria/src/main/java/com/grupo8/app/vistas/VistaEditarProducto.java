package com.grupo8.app.vistas;

import com.grupo8.app.dto.ProductoDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

public class VistaEditarProducto extends JFrame implements MouseListener {

    private JPanel General;
    private ActionListener actionListener;
    private JButton btnVolver;
    private JList<ProductoDTO> listaAEditar;
    private JButton btnEditar;

    public VistaEditarProducto() {
        setTitle("Editar producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 646, 665);
        General = new JPanel();
        General.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(General);
        General.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 599, 587);
        General.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Seleccione el producto a editar");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(55, 26, 307, 77);
        panel.add(lblNewLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 114, 606, 434);
        panel.add(scrollPane);
        
        listaAEditar = new JList<ProductoDTO>();
        scrollPane.setViewportView(listaAEditar);
        listaAEditar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JPanel panel_15 = new JPanel();
        panel_15.setBounds(5, 592, 620, 29);
        General.add(panel_15);
        panel_15.setLayout(new GridLayout(1, 0, 0, 0));
        btnVolver = new JButton("Volver");
        btnVolver.setActionCommand("VOLVER");
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_15.add(btnVolver);

        btnEditar = new JButton("Editar");
        btnEditar.setActionCommand("EDITAR");
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_15.add(btnEditar);
        
        listaAEditar.addMouseListener(this);
        btnEditar.setEnabled(false);
        

    }


    public void mostrar() {
        this.setVisible(true);
        btnEditar.setEnabled(false);

    }


    public void esconder() {
        this.setVisible(false);
    }

    public String obtenerIdProducto() {
        return listaAEditar.getSelectedValue().getId();
    }

    public void setListaAEditar(ProductoDTO[] productos) {
        listaAEditar.setListData(productos);
    }


    public void setActionListener(ActionListener actionListener) {
        
        this.btnEditar.addActionListener(actionListener);
    	this.btnVolver.addActionListener(actionListener);
        this.actionListener = actionListener;
        
    }


    public void success(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
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
		 if(this.listaAEditar.getSelectedValue() != null)
            this.btnEditar.setEnabled(true);
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
