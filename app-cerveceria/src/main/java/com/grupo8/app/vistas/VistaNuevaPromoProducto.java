package com.grupo8.app.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.dto.PromoFijaRequest;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class VistaNuevaPromoProducto extends JFrame implements MouseListener {

	private JPanel General;
	private ActionListener actionListener;
	private JButton btnVolver;
	private JTextField textFieldNombrePromocion;
	private JList<ProductoDTO> listaProductos;
	private JCheckBox chckbxLunes;
	private JCheckBox chckbxMartes;
	private JCheckBox chckbxMiercoles;
	private JCheckBox chckbxJueves;
	private JCheckBox chckbxViernes;
	private JCheckBox chckbxSabado;
	private JCheckBox chckbxDomingo;
	private JRadioButton rdbtnDescuentoPorCant;
	private JRadioButton rdbtnDosPorUno;
	private JTextField textFieldCantidadMinima;
	private JTextField textFieldPrecioUnitario;
	private JLabel lblPrecioUnitario;
	private JLabel lblCantMinima;
	private JButton btnAceptar;
	private ButtonGroup radioGroup;
	
	public VistaNuevaPromoProducto() {
		setTitle("Nuevo Promocion por Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 665);
		General = new JPanel();
		General.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(General);
		General.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 428, 587);
		General.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 1, 428, 64);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Nombre de la promo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);
		
		textFieldNombrePromocion = new JTextField();
		panel_1.add(textFieldNombrePromocion);
		textFieldNombrePromocion.setColumns(10);
		
		
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(0, 57, 630, 236);
		panel.add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Producto");
		lblNewLabel_5.setBounds(80, 11, 78, 25);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_11.add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 47, 429, 189);
		panel_11.add(scrollPane);
		
		listaProductos = new JList<ProductoDTO>();
		scrollPane.setViewportView(listaProductos);
		listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaProductos.addMouseListener(this);
		
	
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(0, 293, 428, 146);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblrDelProducto = new JLabel("Dias de promocion");
		lblrDelProducto.setBounds(10, 11, 166, 25);
		lblrDelProducto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1_1.add(lblrDelProducto);
		
	 	chckbxMartes = new JCheckBox("MARTES");
		chckbxMartes.setBounds(182, 56, 97, 23);
		panel_1_1.add(chckbxMartes);
		
		chckbxViernes = new JCheckBox("VIERNES");
		chckbxViernes.setBounds(293, 37, 97, 23);
		panel_1_1.add(chckbxViernes);
		
	 	chckbxSabado = new JCheckBox("SABADO");
		chckbxSabado.setBounds(293, 63, 97, 23);
		panel_1_1.add(chckbxSabado);
		
	 	chckbxLunes = new JCheckBox("LUNES");
		chckbxLunes.setBounds(182, 30, 97, 23);
		panel_1_1.add(chckbxLunes);
		
		chckbxMiercoles = new JCheckBox("MIERCOLES");
		chckbxMiercoles.setBounds(182, 79, 97, 23);
		panel_1_1.add(chckbxMiercoles);
		
		chckbxJueves = new JCheckBox("JUEVES");
		chckbxJueves.setBounds(182, 105, 97, 23);
		panel_1_1.add(chckbxJueves);
		
		chckbxDomingo = new JCheckBox("DOMINGO");
		chckbxDomingo.setBounds(293, 89, 97, 23);
		panel_1_1.add(chckbxDomingo);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(0, 439, 428, 146);
		panel.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		rdbtnDescuentoPorCant = new JRadioButton("Descuento por cantidad");
		rdbtnDescuentoPorCant.setBounds(6, 7, 428, 48);
		rdbtnDescuentoPorCant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1_2.add(rdbtnDescuentoPorCant);
		
		rdbtnDosPorUno = new JRadioButton("2x1");
		rdbtnDosPorUno.setBounds(6, 58, 428, 48);
		rdbtnDosPorUno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1_2.add(rdbtnDosPorUno);
		
		
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(5, 592, 620, 29);
		General.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnVolver);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnAceptar);
		
		textFieldCantidadMinima = new JTextField();
		textFieldCantidadMinima.setColumns(10);
		textFieldCantidadMinima.setBounds(443, 469, 86, 20);
		General.add(textFieldCantidadMinima);
		
		lblCantMinima = new JLabel("Cantidad minima");
		lblCantMinima.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCantMinima.setBounds(443, 439, 156, 25);
		General.add(lblCantMinima);
		
		lblPrecioUnitario = new JLabel("Precio unitario");
		lblPrecioUnitario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrecioUnitario.setBounds(443, 506, 128, 25);
		General.add(lblPrecioUnitario);
		
		textFieldPrecioUnitario = new JTextField();
		textFieldPrecioUnitario.setColumns(10);
		textFieldPrecioUnitario.setBounds(443, 542, 86, 20);
		General.add(textFieldPrecioUnitario);
		
		this.textFieldPrecioUnitario.setVisible(false);
		this.textFieldCantidadMinima.setVisible(false);
		this.lblCantMinima.setVisible(false);
		this.lblPrecioUnitario.setVisible(false);

		radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnDescuentoPorCant);
		radioGroup.add(rdbtnDosPorUno);
		rdbtnDescuentoPorCant.addMouseListener(this);
		rdbtnDosPorUno.addMouseListener(this);
	}

	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
        this.btnVolver.addActionListener(actionListener);
        this.btnAceptar.addActionListener(actionListener);
        this.actionListener = actionListener;
	}

	public void limpiaCampos() {
		this.textFieldNombrePromocion.setText("");
		this.rdbtnDosPorUno.setSelected(false);
		this.rdbtnDescuentoPorCant.setSelected(false);
		this.chckbxLunes.setSelected(false);
		this.chckbxMartes.setSelected(false);
		this.chckbxMiercoles.setSelected(false);
		this.chckbxJueves.setSelected(false);
		this.chckbxViernes.setSelected(false);
		this.chckbxSabado.setSelected(false);
		this.chckbxDomingo.setSelected(false);
			
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void mostrar() {
		this.setVisible(true);
		
	}

	public void esconder() {
		this.setVisible(false);
		
	}
	
	public PromoFijaRequest getFormulario() {
		PromoFijaRequest request = new PromoFijaRequest();
		request.setIdProducto(this.listaProductos.getSelectedValue().getId());
		request.setDosPorUno(this.rdbtnDosPorUno.isSelected());
		request.setDtoPorCantidad(this.rdbtnDescuentoPorCant.isSelected());

		if(this.rdbtnDescuentoPorCant.isSelected()) {
			request.setDtoPorCantPrecioU(Double.parseDouble(this.textFieldPrecioUnitario.getText()));
			request.setDtoPorCantMin(Integer.parseInt(this.textFieldCantidadMinima.getText()));
		}

		request.setActiva(true);
		request.setDiasPromo(getDiasSelecccionados());

		return request;
	}

	public List<DayOfWeek> getDiasSelecccionados() {
		List<DayOfWeek> dias = new ArrayList<DayOfWeek>();
		if(this.chckbxLunes.isSelected()) {
			dias.add(DayOfWeek.MONDAY);
		}
		if(this.chckbxMartes.isSelected()) {
			dias.add(DayOfWeek.TUESDAY);
		}
		if(this.chckbxMiercoles.isSelected()) {
			dias.add(DayOfWeek.WEDNESDAY);
		}
		if(this.chckbxJueves.isSelected()) {
			dias.add(DayOfWeek.THURSDAY);
		}
		if(this.chckbxViernes.isSelected()) {
			dias.add(DayOfWeek.FRIDAY);
		}
		if(this.chckbxSabado.isSelected()) {
			dias.add(DayOfWeek.SATURDAY);
		}
		if(this.chckbxDomingo.isSelected()) {
			dias.add(DayOfWeek.SUNDAY);
		}

		return dias;
	}

	public void setListaProductos(ProductoDTO[] productos) {
		listaProductos.setListData(productos);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (rdbtnDescuentoPorCant.isSelected()) {
			this.textFieldPrecioUnitario.setVisible(true);
			this.textFieldCantidadMinima.setVisible(true);
			this.lblCantMinima.setVisible(true);
			this.lblPrecioUnitario.setVisible(true);
		}
		else {
			this.textFieldPrecioUnitario.setVisible(false);
			this.textFieldCantidadMinima.setVisible(false);
			this.lblCantMinima.setVisible(false);
			this.lblPrecioUnitario.setVisible(false);
		}
		if (listaProductos.getSelectedValue() != null) {
			this.btnAceptar.setEnabled(true);
		} else {
			this.btnAceptar.setEnabled(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
