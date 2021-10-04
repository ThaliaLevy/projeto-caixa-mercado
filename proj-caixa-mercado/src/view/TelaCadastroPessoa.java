
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Funcionario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaCadastroPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textData;
	private JTextField textCpf;
	private JTextField textTel;
	private JTextField textLogin;
	private JTextField textSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPessoa frame = new TelaCadastroPessoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int controlador = 0;

	public TelaCadastroPessoa() {
		String PessoasCadastradas = System.getProperty("user.dir") + "\\src\\control\\PessoasCadastradas.txt";
		verificarArquivo(PessoasCadastradas);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setBounds(55, 111, 46, 14);
		panel.add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(55, 203, 46, 14);
		panel.add(lblCpf);

		JLabel lblNewLabel = new JLabel("TELEFONE:");
		lblNewLabel.setBounds(55, 246, 86, 14);
		panel.add(lblNewLabel);

		JLabel lblData = new JLabel("DATA NASCIMENTO:");
		lblData.setBounds(55, 158, 116, 14);
		panel.add(lblData);

		JLabel lblNewLabel_1 = new JLabel("LOGIN:");
		lblNewLabel_1.setBounds(55, 291, 46, 14);
		panel.add(lblNewLabel_1);

		textNome = new JTextField();
		textNome.setBounds(181, 108, 149, 20);
		panel.add(textNome);
		textNome.setColumns(10);

		JLabel lblErroCpf = new JLabel("");
		lblErroCpf.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblErroCpf.setBounds(189, 222, 139, 14);
		panel.add(lblErroCpf);

		JLabel lblErroData = new JLabel("");
		lblErroData.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblErroData.setBounds(189, 175, 126, 14);
		panel.add(lblErroData);

		textData = new JTextField();
		textData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String op = textData.getText();
				switch (op.length()) {
				case 8: {
					String vetor[] = op.split("");
					String formatarData = vetor[0] + vetor[1] + "/" + vetor[2] + vetor[3] + "/" + vetor[4] + vetor[5]
							+ vetor[6] + vetor[7];
					textData.setText(formatarData);
					lblErroData.setText("");
					controlador = controlador + 1;
					break;
				}
				default: {
					lblErroData.setText("Data incorreta ou incompleta.");
				}
				}
			}
		});
		textData.setBounds(181, 155, 149, 20);
		panel.add(textData);
		textData.setColumns(10);

		textCpf = new JTextField();
		textCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String op = textCpf.getText();
				switch (op.length()) {
				case 11: {
					String vetor[] = op.split("");
					String formatarCpf = vetor[0] + vetor[1] + vetor[2] + "." + vetor[3] + vetor[4] + vetor[5] + "."
							+ vetor[6] + vetor[7] + vetor[8] + "-" + vetor[9] + vetor[10];
					textCpf.setText(formatarCpf);
					lblErroCpf.setText("");
					controlador = controlador + 1;
					break;
				}
				default: {
					lblErroCpf.setText("CPF incorreto ou incompleto.");
				}
				}
			}
		});
		textCpf.setColumns(10);
		textCpf.setBounds(181, 200, 149, 20);
		panel.add(textCpf);

		JLabel lblErroTel = new JLabel("");
		lblErroTel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblErroTel.setBounds(191, 266, 139, 14);
		panel.add(lblErroTel);

		textTel = new JTextField();
		textTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String op = textTel.getText();
				switch (op.length()) {
				case 11: {
					String vetor[] = op.split("");
					String formatarTel = "(" + vetor[0] + vetor[1] + ") " + vetor[2] + vetor[3] + vetor[4] + vetor[5]
							+ vetor[6] + "-" + vetor[7] + vetor[8] + vetor[9] + vetor[10];
					textTel.setText(formatarTel);
					lblErroTel.setText("");
					controlador = controlador + 1;
					break;
				}
				default: {
					lblErroTel.setText("Telefone incorreto ou incompleto.");
				}
				}
			}
		});
		textTel.setColumns(10);
		textTel.setBounds(181, 243, 149, 20);
		panel.add(textTel);

		JLabel lblTitulo = new JLabel("CADASTRO PESSOA");
		lblTitulo.setFont(new Font("Sylfaen", Font.BOLD, 17));
		lblTitulo.setBounds(96, 25, 211, 47);
		panel.add(lblTitulo);

		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setBounds(55, 335, 46, 14);
		panel.add(lblSenha);

		textLogin = new JTextField();
		textLogin.setColumns(10);
		textLogin.setBounds(181, 288, 149, 20);
		panel.add(textLogin);

		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(181, 332, 149, 20);
		panel.add(textSenha);

		JLabel lblErro = new JLabel("");
		lblErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblErro.setBounds(96, 371, 219, 14);
		panel.add(lblErro);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario f = new Funcionario();
				String auxNome = textNome.getText();
				String auxData = textData.getText();
				String auxCpf = textCpf.getText();
				String auxTel = textTel.getText();
				String auxLogin = textLogin.getText();
				String auxSenha = textSenha.getText();

				boolean aux = f.verificarPreenchimento(auxNome, auxCpf, auxData, auxLogin, auxTel, auxSenha,
						PessoasCadastradas);

				if (aux == true) {
					if (controlador == 3) {
						f.salvar(auxNome, auxCpf, auxData, auxLogin, auxTel, auxSenha, PessoasCadastradas);
						TelaInicial tc = new TelaInicial();
						lblErro.setText("");
						controlador = 0;
					}
				} else {
					lblErro.setText("Verifique se todos os espaços foram preenchidos.");
				}
			}
		});
		btnSalvar.setBounds(143, 390, 89, 23);
		panel.add(btnSalvar);

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
