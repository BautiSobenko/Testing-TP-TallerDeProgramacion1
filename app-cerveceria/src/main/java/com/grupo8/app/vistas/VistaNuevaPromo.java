package com.grupo8.app.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaNuevaPromo extends JFrame {
    private JPanel contentPane;
    private JButton btnPromoProducto;

    private JButton btnVolver;
    private JButton btnPromoMedioPago;

    public VistaNuevaPromo() {
        setTitle("Crear promociones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 532, 464);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 506, 370);
        contentPane.add(panel);
        panel.setLayout(null);
        
                btnPromoProducto = new JButton("Promocion por producto");
                btnPromoProducto.setBounds(31, 100, 250, 45);
                panel.add(btnPromoProducto);
                btnPromoProducto.setActionCommand("PROMO_PRODUCTO");
                btnPromoProducto.setPreferredSize(new Dimension(250, 45));
                btnPromoProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));
                
                        btnPromoMedioPago = new JButton("Promocion por medio de pago");
                        btnPromoMedioPago.setBounds(31, 237, 250, 45);
                        panel.add(btnPromoMedioPago);
                        btnPromoMedioPago.setActionCommand("PROMO_MEDIO_PAGO");
                        btnPromoMedioPago.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                            }
                        });
                        btnPromoMedioPago.setPreferredSize(new Dimension(250, 45));
                        btnPromoMedioPago.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        
                        JLabel lblNewLabel = new JLabel("Elija el tipo de la nueva promocion");
                        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        lblNewLabel.setBounds(132, 11, 281, 45);
                        panel.add(lblNewLabel);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(5, 375, 506, 45);
        contentPane.add(panel_3);
        panel_3.setLayout(new BorderLayout(0, 0));

        btnVolver = new JButton("Volver");
        btnVolver.setActionCommand("VOLVER");
        btnVolver.setPreferredSize(new Dimension(250, 45));
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_3.add(btnVolver);
    }

    public void setActionListener(ActionListener actionListener) {

        this.btnVolver.addActionListener(actionListener);
        this.btnPromoProducto.addActionListener(actionListener);
        this.btnPromoMedioPago.addActionListener(actionListener);

    }
    public void mostrar() {
        this.setVisible(true);

    }

    public void esconder() {
        this.setVisible(false);

    }
}
