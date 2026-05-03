package br.edu.fatecguarulhos.caixaeletronico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CaixaEletronico caixaEletronico;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
        try {
            // Set the Look and Feel to the system's native one
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Log or handle the exception if the theme cannot be loaded
            e.printStackTrace();
        }*/
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
		caixaEletronico = new CaixaEletronico();
		setTitle("Caixa Eletronico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		JLabel lblMCliente = new JLabel("Módulo do Cliente:");
		lblMCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMCliente.setBounds(10, 21, 142, 34);
		contentPane.add(lblMCliente);
		
		JButton btnEfeSaque = new JButton("Efetuar Saque");
		btnEfeSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPainelSaque();
			}
		});
		
		btnEfeSaque.setBackground(new Color(201, 231, 227));
		btnEfeSaque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEfeSaque.setBounds(20, 69, 324, 34);
		contentPane.add(btnEfeSaque);
		
		JLabel lblMAdm = new JLabel("Módulo do Administrador:");
		lblMAdm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMAdm.setBounds(10, 114, 177, 34);
		contentPane.add(lblMAdm);
		
		//Botão responsável pelo relatorio de cedulas
		JButton btnRelaCedu = new JButton("Relatório de Cedulas");
		btnRelaCedu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPainelRelatorio();
			}
		});
		btnRelaCedu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRelaCedu.setBackground(new Color(201, 231, 227));
		btnRelaCedu.setBounds(20, 159, 324, 34);
		contentPane.add(btnRelaCedu);
		
		//Botão responsável pelo valor disponivel no caixa
		JButton btnValor = new JButton("Valor total disponível");
		btnValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnValor.setBackground(new Color(201, 231, 227));
		btnValor.setBounds(20, 204, 324, 34);
		contentPane.add(btnValor);
		btnValor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarValorDispo();
			}
		});
		
		//Botão responsável pela reposição das cedulas no caixa
		JButton btnRepo = new JButton("Reposição de Cédulas");
		btnRepo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPainelReposicao();
			}
		});
		btnRepo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRepo.setBackground(new Color(201, 231, 227));
		btnRepo.setBounds(20, 249, 324, 34);
		contentPane.add(btnRepo);
		
		
		JButton btnCota = new JButton("Cóta Mínima");
		btnCota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 mostrarPainelCotaMinima();
			}
		});
		btnCota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCota.setBackground(new Color(201, 231, 227));
		btnCota.setBounds(20, 294, 324, 34);
		contentPane.add(btnCota);
		
		JLabel lblMAmbos = new JLabel("Módulo de Ambos:");
		lblMAmbos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMAmbos.setBounds(10, 410, 142, 34);
		contentPane.add(lblMAmbos);
			
		//Botão responsável por exibir o extrato e encerrar a aplicação 
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.setBackground(new Color(201, 231, 227));
		btnSair.setBounds(10, 455, 324, 34);
		contentPane.add(btnSair);
		
		//Integração com o botão gerarExtratoFinal() da Classe CaixaEletronico
		btnSair.addActionListener(e -> {
			String extrato = caixaEletronico.gerarExtratoFinal();
			JOptionPane.showMessageDialog(this,  extrato, "Extrato Final", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		});

	}

	//Metodo para mostrar o Pop-up do relatorio de cedulas 
	private void mostrarPainelRelatorio() {
	String mensagem = caixaEletronico.pegaRelatorioCedulas();
	JOptionPane.showMessageDialog(this, mensagem);
	}
	
	//Metodo para mostrar o Pop-up do valor disponivel
	private void mostrarValorDispo() {
	String mensagem = caixaEletronico.pegaValorTotalDisponivel();
	JOptionPane.showMessageDialog(this, mensagem);
	}
	
	
	private void mostrarPainelSaque() {
		try {
			caixaEletronico.verificarCotaMinima();
			IconFontSwing.register(FontAwesome.getIconFont());
			Icon icon = IconFontSwing.buildIcon(FontAwesome.MONEY, 30);
			String valor = (String) JOptionPane.showInputDialog(this,"Digite o valor que deseja sacar:","Saque",JOptionPane.INFORMATION_MESSAGE,icon, null, "");
			if(valor == null)
				throw new Exception("null");
			if(valor.isEmpty())
				throw new Exception("Nenhum valor inserido");
			Integer valorConvertido = Integer.parseInt(valor);
			sacarValor(valorConvertido);
		}
		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Valor inserido não é um número inteiro ou é um valor muito alto", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		catch (RuntimeException re) {
			JOptionPane.showMessageDialog(this, re.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {
			if(!e.getMessage().contains("null"))
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	private void sacarValor(Integer valor) {
		JOptionPane.showMessageDialog(this,caixaEletronico.sacar(valor),"Seu dinheiro:", JOptionPane.PLAIN_MESSAGE);
	}
	
	//Metodo para mostrar o Pop-up da reposição de cedulas
		private void mostrarPainelReposicao() {
			try {
			IconFontSwing.register(FontAwesome.getIconFont());
			Icon icon = IconFontSwing.buildIcon(FontAwesome.MONEY, 30);
			String cedula = (String) JOptionPane.showInputDialog(this,"Digite a cedula que deseja repor:","Repor",JOptionPane.INFORMATION_MESSAGE,icon, null, "");
			if (cedula == null)
				throw new Exception("null");
			if (cedula.isEmpty())
				throw new Exception("Nenhum valor inserido");
			IconFontSwing.register(FontAwesome.getIconFont());
			Icon icon2 = IconFontSwing.buildIcon(FontAwesome.MONEY, 30);
			String quantidade = (String) JOptionPane.showInputDialog(this,"Digite a quantidade de cedulas que deseja repor:","Repor",JOptionPane.INFORMATION_MESSAGE,icon, null, "");
			if (quantidade == null)
				throw new Exception("null");
			if (quantidade.isEmpty())
				throw new Exception("Nenhum valor inserido");
			
				Integer cedulaDigitada = Integer.parseInt(cedula);
				Integer quantidadeDigitada = Integer.parseInt(quantidade);
				String mensagem = caixaEletronico.reposicaoCedulas(cedulaDigitada,quantidadeDigitada);
				JOptionPane.showMessageDialog(this, mensagem);
			}
			// exceção caso os números digitados não sejam interiros
			catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Valor inserido não é um número inteiro", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	private void mostrarPainelCotaMinima() {
		IconFontSwing.register(FontAwesome.getIconFont());
		Icon icon = IconFontSwing.buildIcon(FontAwesome.MONEY, 30);
		String cotaMinima = (String) JOptionPane.showInputDialog(this,"Digite o novo valor para cota mínima:","Repor",JOptionPane.INFORMATION_MESSAGE,icon, null, "");
		// verificar se foi apertado o "cancel"
		if(cotaMinima != null) {
			try {
				Integer cotaMinimaDigitada = Integer.parseInt(cotaMinima);
				String mensagem = caixaEletronico.armazenaCotaMinima(cotaMinimaDigitada);
				JOptionPane.showMessageDialog(this, mensagem);
				caixaEletronico.verificarCotaMinima();
				} catch (NumberFormatException ne) {
					JOptionPane.showMessageDialog(this, ne.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        } catch (RuntimeException re) {
		        	JOptionPane.showMessageDialog(this, re.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception e) {
		        	JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
			}
		}
}
