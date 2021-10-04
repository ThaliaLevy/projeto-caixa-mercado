
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Pagamento;
import control.Produto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TelaCaixa extends JFrame {

	private JPanel contentPane;
	private JTextField textDesc;
	private JTextField textValor;
	private JTextField textCod;
	private JTextField textQtdd;
	private JTextField textSubtotal;
	private JTextField textUddMedida;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCaixa frame = new TelaCaixa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCaixa() {
		String BDProdutos = System.getProperty("user.dir") + "\\src\\control\\BDProdutos.txt";
		String listaCompras = System.getProperty("user.dir") + "\\src\\control\\listaCompras.txt";
		verificarArquivo(BDProdutos);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textDesc = new JTextField();
		textDesc.setEditable(false);
		textDesc.setBounds(30, 33, 261, 47);
		panel.add(textDesc);
		textDesc.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("R$");
		lblNewLabel_6.setBounds(145, 100, 26, 14);
		panel.add(lblNewLabel_6);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(324, 97, 261, 221);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel = new JLabel("Descrição do produto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(30, 11, 145, 23);
		panel.add(lblNewLabel);

		textValor = new JTextField();
		textValor.setHorizontalAlignment(SwingConstants.RIGHT);
		textValor.setEditable(false);
		textValor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textValor.setBounds(165, 97, 126, 20);
		panel.add(textValor);
		textValor.setColumns(10);

		textCod = new JTextField();
		textCod.setBounds(30, 133, 76, 34);
		panel.add(textCod);
		textCod.setColumns(10);

		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Produto p = new Produto();

				String codLocalizado = textCod.getText();
				p.localizarProduto(BDProdutos, codLocalizado);

				textDesc.setText(p.getAuxDesc());
				textUddMedida.setText(p.getAuxUddMedida());
				textValor.setText(p.getAuxValor());
			}

		});
		btnLocalizar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLocalizar.setBounds(116, 133, 86, 34);
		panel.add(btnLocalizar);

		textQtdd = new JTextField();
		textQtdd.setBounds(250, 133, 41, 34);
		panel.add(textQtdd);
		textQtdd.setColumns(10);

		JButton btnIncluir = new JButton("Incluir item na lista de compras");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produto p = new Produto();

				String auxCod = textCod.getText();
				String auxDesc = textDesc.getText();
				String auxUddMedida = textUddMedida.getText();
				String auxValor = textValor.getText();
				String auxQtdd = textQtdd.getText();

				if (!auxQtdd.equals("")) {

					p.incluir(auxCod, auxQtdd, listaCompras, BDProdutos, auxDesc, auxUddMedida, auxValor);
					textSubtotal.setText(p.calcularSubtotal(auxValor, listaCompras));
					textArea.setText(p.listar(listaCompras)); // poderia ter utilizado o comando append para pular de
																// linha e acrescentar um novo item
					textCod.setText("");
					textDesc.setText("");
					textUddMedida.setText("");
					textValor.setText("");
					textQtdd.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Preencha a quantidade de produtos.");
				}
			}
		});
		btnIncluir.setBounds(30, 191, 261, 23);
		panel.add(btnIncluir);

		textSubtotal = new JTextField();
		textSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		textSubtotal.setText("0");
		textSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textSubtotal.setEditable(false);
		textSubtotal.setBounds(324, 33, 261, 47);
		panel.add(textSubtotal);
		textSubtotal.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Valor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(165, 83, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Código do produto");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_2.setBounds(30, 119, 86, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("X");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(223, 143, 17, 14);
		panel.add(lblNewLabel_3);

		JButton btnCancelarCompra = new JButton("Cancelar compras");
		btnCancelarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Produto p = new Produto();
				p.limparTela(listaCompras);
				textArea.setText("");
				textSubtotal.setText("");
			}
		});
		btnCancelarCompra.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCancelarCompra.setBounds(30, 234, 125, 23);
		panel.add(btnCancelarCompra);

		JButton btnLimparTela = new JButton("Limpar tela");
		btnLimparTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Produto p = new Produto();
				p.limparTela(listaCompras);
				textArea.setText("");
				textSubtotal.setText("");
			}
		});
		btnLimparTela.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLimparTela.setBounds(30, 268, 125, 23);
		panel.add(btnLimparTela);

		JButton btnFinalizar = new JButton("Finalizar compra");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String total = textSubtotal.getText();
				if (!total.equals("0")) {
					Pagamento pa = new Pagamento();
					TelaPagamento pg = new TelaPagamento();
					pg.txtTotal.setText(total);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum valor de compra registrado.");
				}
			}
		});
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnFinalizar.setBounds(165, 234, 126, 23);
		panel.add(btnFinalizar);

		JButton btnCadastrarProduto = new JButton("Cadastrar produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastro tcad = new TelaCadastro();
			}
		});
		btnCadastrarProduto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCadastrarProduto.setBounds(166, 268, 125, 23);
		panel.add(btnCadastrarProduto);

		JLabel lblNewLabel_4 = new JLabel("Subtotal");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(324, 15, 46, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Unidade medida");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_5.setBounds(30, 83, 76, 14);
		panel.add(lblNewLabel_5);

		textUddMedida = new JTextField();
		textUddMedida.setEditable(false);
		textUddMedida.setBounds(30, 97, 82, 20);
		panel.add(textUddMedida);
		textUddMedida.setColumns(10);

		JLabel lblQtdd = new JLabel("Quantidade");
		lblQtdd.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblQtdd.setBounds(245, 119, 58, 14);
		panel.add(lblQtdd);

		setVisible(true); // tornar classe visível para ser instanciada. poderia ter usado o comando onde
							// fosse instanciar também.
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
