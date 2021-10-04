
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textDescricao;
	private JTextField textCodigo;
	private JTextField textValor;
	String m = "";

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastro() {
		String BDProdutos = System.getProperty("user.dir") + "\\src\\control\\BDProdutos.txt";
		verificarArquivo(BDProdutos);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CADASTRO DE PRODUTOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(129, 21, 232, 46);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Informação do produto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(41, 81, 125, 14);
		panel.add(lblNewLabel_1);

		JLabel lblCodigo = new JLabel("Código: ");
		lblCodigo.setBounds(41, 121, 46, 14);
		panel.add(lblCodigo);

		JLabel lblDescricao = new JLabel("Descrição: ");
		lblDescricao.setBounds(41, 159, 77, 14);
		panel.add(lblDescricao);

		JLabel lblUnddMedida = new JLabel("Unidade medida: ");
		lblUnddMedida.setBounds(41, 199, 125, 14);
		panel.add(lblUnddMedida);

		JLabel lblValor = new JLabel("Valor unitário: ");
		lblValor.setBounds(41, 242, 91, 14);
		panel.add(lblValor);

		textDescricao = new JTextField();
		textDescricao.setBounds(157, 156, 203, 20);
		panel.add(textDescricao);
		textDescricao.setColumns(10);

		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		textCodigo.setBounds(157, 118, 203, 20);
		panel.add(textCodigo);

		textValor = new JTextField();
		textValor.setColumns(10);
		textValor.setBounds(157, 239, 202, 20);
		panel.add(textValor);

		JLabel lblErro = new JLabel("");
		lblErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblErro.setBounds(154, 270, 232, 14);
		panel.add(lblErro);

		JComboBox cbbUddMedida = new JComboBox();
		cbbUddMedida.setModel(new DefaultComboBoxModel(new String[] { "Selecione...", "KG", "L" }));
		cbbUddMedida.setToolTipText("");
		cbbUddMedida.setBounds(157, 196, 202, 20);
		panel.add(cbbUddMedida);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String descLocalizado = textDescricao.getText();
				String valorLocalizado = textValor.getText();
				String uddMedidaLocalizado = cbbUddMedida.getSelectedItem().toString();

				Produto p = new Produto();

				boolean aux = p.verificarPreenchimento(descLocalizado, valorLocalizado, uddMedidaLocalizado);
				if (aux == true) {
						p.salvar(BDProdutos, descLocalizado, valorLocalizado, uddMedidaLocalizado);
						textDescricao.setText("");
						textValor.setText("");
						cbbUddMedida.setSelectedItem("Selecione...");
						lblErro.setText("");
				} else {
						lblErro.setText("Verifique se todos os espaços foram preenchidos.");
				}
			}
		});
		btnSalvar.setBounds(43, 296, 89, 23);
		panel.add(btnSalvar);

		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produto p = new Produto();

				if (p.verificarControlador(m) == "") {
					textCodigo.setText("");
					textDescricao.setText("");
					textValor.setText("");
					cbbUddMedida.setSelectedItem("Selecione...");
				}
				boolean aux = p.localizar(BDProdutos);

				if (aux == true) {
					textValor.setText(p.getAuxValor());
					textCodigo.setText(p.getNrLocalizado());
					textDescricao.setText(p.getAuxDesc());
					cbbUddMedida.setSelectedItem(p.getAuxUddMedida());
					m = "ativo";
				} else {
					JOptionPane.showMessageDialog(null, "Não localizado!");
					m = "";
				}
			}
		});
		btnLocalizar.setBounds(142, 296, 89, 23);
		panel.add(btnLocalizar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produto p = new Produto();

				boolean aux = p.localizar(BDProdutos);
				if (aux == true) {
					String opMenu = p.menu();
					p.modificar(opMenu, BDProdutos);
				}
			}
		});
		btnEditar.setBounds(241, 296, 89, 23);
		panel.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Produto p = new Produto();

				if (p.verificarControlador(m) == "") {
					textCodigo.setText("");
					textDescricao.setText("");
					textValor.setText("");
					cbbUddMedida.setSelectedItem("Selecione...");
				}
				p.excluir(BDProdutos);
			}
		});
		btnExcluir.setBounds(340, 296, 89, 23);
		panel.add(btnExcluir);
		
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
