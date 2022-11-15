package com.grupo8.app.vistas;

import com.grupo8.app.dto.ComandaDTO;
import com.grupo8.app.dto.PedidoRequest;
import com.grupo8.app.dto.ProductoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaAgregarPedidoComanda extends JFrame implements MouseListener,KeyListener{

	private final JTextField textFieldCantProd;
	private final JList <ComandaDTO> listComandas;
	private final JButton btnAgregar;
	private final JButton btnVolver;
	private final JList<ProductoDTO> listProductos;

	
	public VistaAgregarPedidoComanda() {
		setTitle("Agregar pedido a comanda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 504);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		btnAgregar = new JButton("Agregar pedido a la comanda");
		btnAgregar.setActionCommand("AGREGAR");
		btnAgregar.setBounds(342, 421, 201, 23);
		panel.add(btnAgregar);
		
		JLabel lbl = new JLabel("Seleccione productos");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl.setBounds(305, 11, 135, 14);
		panel.add(lbl);
		
		JLabel lblNewLabel = new JLabel("Seleccione comanda ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 122, 14);
		panel.add(lblNewLabel);
		
		textFieldCantProd = new JTextField();
		textFieldCantProd.setBounds(182, 364, 86, 20);
		panel.add(textFieldCantProd);
		textFieldCantProd.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de items");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(21, 364, 135, 23);
		lblNewLabel_1.setBounds(10, 363, 162, 23);
		panel.add(lblNewLabel_1);
		
		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("VOLVER");
		btnVolver.setBounds(10, 421, 89, 23);
		panel.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 258, 299);
		panel.add(scrollPane);
		
		listComandas = new JList<>();
		scrollPane.setRowHeaderView(listComandas);
		listComandas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(312, 36, 231, 299);
		panel.add(scrollPane_1);
		
		listProductos = new JList<>();
		scrollPane_1.setViewportView(listProductos);
		listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProductos.addMouseListener(this);
		listComandas.addMouseListener(this);
	
		btnAgregar.setEnabled(false);
	}

	public void setActionListener(ActionListener lis){
		btnAgregar.addActionListener(lis);
		btnVolver.addActionListener(lis);
	}
	public void mostrar() {
		btnAgregar.setEnabled(false);
		this.setVisible(true);
	}

	public void setListComandas(ComandaDTO[] comandas){
		listComandas.setListData(comandas);
	}

	public void setListProductos(ProductoDTO[] productos){
		listProductos.setListData(productos);
		
	}

	public ProductoDTO getProductoSelec() {
		return this.listProductos.getSelectedValue();
	}
	
	public ComandaDTO getComandaDTO() {
		return this.listComandas.getSelectedValue();
	}
	
	public int getCantProd() {
		return Integer.parseInt(textFieldCantProd.getText());
	}

	public void esconder() {
        this.setVisible(false);
    }

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public PedidoRequest getPedido() {
		PedidoRequest pedido = new PedidoRequest();
		pedido.setCantidad(this.getCantProd());
		pedido.setIdComanda(this.getComandaDTO().getId());
		pedido.setIdProducto(this.getProductoSelec().getId());
		return pedido;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (listProductos.getSelectedIndex() != -1 && listComandas.getSelectedIndex() != -1) {
			btnAgregar.setEnabled(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(this.textFieldCantProd.getText().length()!=0) {
			btnAgregar.setEnabled(true);
		}
		
	}
}
