package br.edu.fatecguarulhos.caixaeletronico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Caixa Eletronico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMCliente = new JLabel("Módulo do Cliente:");
		lblMCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMCliente.setBounds(10, 21, 142, 34);
		contentPane.add(lblMCliente);
		
		JButton btnEfeSaque = new JButton("Efetuar Saque");
		btnEfeSaque.setBackground(new Color(201, 231, 227));
		btnEfeSaque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEfeSaque.setBounds(20, 69, 324, 34);
		contentPane.add(btnEfeSaque);
		
		JLabel lblMAdm = new JLabel("Módulo do Administrador:");
		lblMAdm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMAdm.setBounds(10, 114, 177, 34);
		contentPane.add(lblMAdm);
		
		JButton btnRelaCedu = new JButton("Relatório de Cedulas");
		btnRelaCedu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRelaCedu.setBackground(new Color(201, 231, 227));
		btnRelaCedu.setBounds(20, 159, 324, 34);
		contentPane.add(btnRelaCedu);
		
		JButton btnValor = new JButton("Valor total disponível");
		btnValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnValor.setBackground(new Color(201, 231, 227));
		btnValor.setBounds(20, 204, 324, 34);
		contentPane.add(btnValor);
		
		JButton btnRepo = new JButton("Reposição de Cédulas");
		btnRepo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRepo.setBackground(new Color(201, 231, 227));
		btnRepo.setBounds(20, 249, 324, 34);
		contentPane.add(btnRepo);
		
		JButton btnCota = new JButton("Cóta Mínima");
		btnCota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCota.setBackground(new Color(201, 231, 227));
		btnCota.setBounds(20, 294, 324, 34);
		contentPane.add(btnCota);
		
		JLabel lblMAmbos = new JLabel("Módulo de Ambos:");
		lblMAmbos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMAmbos.setBounds(10, 410, 142, 34);
		contentPane.add(lblMAmbos);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.setBackground(new Color(201, 231, 227));
		btnSair.setBounds(10, 455, 324, 34);
		contentPane.add(btnSair);

	}
}
