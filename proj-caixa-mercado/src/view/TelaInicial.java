
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Funcionario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField textLogin;
	private JTextField textSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInicial() {
		String PessoasCadastradas = System.getProperty("user.dir") + "\\src\\control\\PessoasCadastradas.txt";
		verificarArquivo(PessoasCadastradas);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("LOGIN:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(41, 89, 62, 14);
		panel.add(lblLogin);

		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(41, 137, 62, 14);
		panel.add(lblSenha);

		textLogin = new JTextField();
		textLogin.setBounds(106, 88, 228, 20);
		panel.add(textLogin);
		textLogin.setColumns(10);

		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(106, 136, 228, 20);
		panel.add(textSenha);

		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				 String auxLogin = textLogin.getText(); 
				 String auxSenha = textSenha.getText();
				
				if (textLogin.getText().isEmpty() || textSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Verifique se todos os espaços foram preenchidos.");
				} else {
					Funcionario f = new Funcionario();
					if(f.localizarCadastro(PessoasCadastradas, auxLogin, auxSenha) == true) {
						TelaCaixa tc = new TelaCaixa();
					}else {
						JOptionPane.showMessageDialog(null, "Login ou senha não cadastrados ou incorretos.");
					}
				}
			}
		});

		btnAcessar.setBounds(106, 201, 89, 23);
		panel.add(btnAcessar);

		JButton btnCadastrar = new JButton("Cadastrar-se");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaCadastroPessoa tp = new TelaCadastroPessoa();

			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCadastrar.setBounds(239, 201, 95, 23);
		panel.add(btnCadastrar);

		JLabel lblTitulo = new JLabel("ACESSAR SISTEMA DE CAIXA ELETRÔNICO");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(65, 21, 313, 20);
		panel.add(lblTitulo);

		setVisible(true);
	}

	public static void verificarArquivo(String caminho) {
		try {
			File f = new File(caminho);
			if (!f.exists()) {
				String auxCaminho = caminho.substring(0, caminho.lastIndexOf("\\"));
				File fDir = new File(auxCaminho);
				fDir.mkdir();
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
