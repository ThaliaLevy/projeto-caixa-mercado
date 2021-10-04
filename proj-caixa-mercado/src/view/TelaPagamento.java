
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Pagamento;
import control.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaPagamento extends JFrame {

	private JPanel contentPane;
	JTextField txtTotal;
	private JTextField txtDinheiro;
	private JTextField txtTroco;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPagamento frame = new TelaPagamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	String cont = "";

	public TelaPagamento() {
		String RegistroVendas = System.getProperty("user.dir") + "\\src\\control\\RegistroVendas.txt";
		verificarArquivo(RegistroVendas);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(41, 43, 46, 14);
		panel.add(lblTotal);

		JLabel lblNewLabel = new JLabel("Forma pagamento:");
		lblNewLabel.setBounds(41, 103, 111, 14);
		panel.add(lblNewLabel);

		JLabel lblDinheiro = new JLabel("Em dinheiro:");
		lblDinheiro.setBounds(41, 173, 111, 14);
		panel.add(lblDinheiro);

		JComboBox cbbFormaPagamento = new JComboBox();
		cbbFormaPagamento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (cont.equals("ativo")) {
					txtTroco.setText("");
				}
			}
		});
		cbbFormaPagamento.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione...", "Vale", "Cheque", "Crédito", "Débito", "Dinheiro" }));
		cbbFormaPagamento.setBounds(158, 100, 102, 20);
		panel.add(cbbFormaPagamento);

		JLabel lblTroco = new JLabel("Troco:");
		lblTroco.setBounds(41, 198, 46, 14);
		panel.add(lblTroco);

		JButton btnNewButton = new JButton("Registrar pagamento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Pagamento pa = new Pagamento();

				String total = txtTotal.getText();
				String formaPagamento = cbbFormaPagamento.getSelectedItem().toString();
				String dinheiro = txtDinheiro.getText();

				if (pa.verificarPreenchimento(total, formaPagamento, RegistroVendas) == "ok") {
					if (cbbFormaPagamento.getSelectedItem().equals("Dinheiro")) {
						if (dinheiro.equals("")) {
							JOptionPane.showMessageDialog(null, "Preencha o campo 'Em dinheiro'");
						} else {
							pa.registrar(total, formaPagamento, RegistroVendas);
							JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
							txtTroco.setText(pa.calcularTroco(total, dinheiro));
							txtTotal.setText("");
							txtDinheiro.setText("");
							cbbFormaPagamento.setSelectedItem("Selecione...");
							cont = "ativo";
						}
					} else {
						pa.registrar(total, formaPagamento, RegistroVendas);
						JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
						txtTotal.setText("");
						txtDinheiro.setText("");
						cbbFormaPagamento.setSelectedItem("Selecione...");
						cont = "ativo";
					}
				} else {
					JOptionPane.showMessageDialog(null, "Verifique se todos os campos foram preenchidos.");
				}
			}
		});
		btnNewButton.setBounds(62, 242, 198, 23);
		panel.add(btnNewButton);

		JButton btnVoltarParaCaixa = new JButton("Voltar para caixa");
		btnVoltarParaCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TelaCaixa tc = new TelaCaixa();

			}
		});
		btnVoltarParaCaixa.setBounds(62, 276, 198, 23);
		panel.add(btnVoltarParaCaixa);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (cont.equals("ativo")) {
					txtTroco.setText("");
				}
			}
		});
		txtTotal.setBounds(97, 40, 163, 20);
		panel.add(txtTotal);
		txtTotal.setColumns(10);

		txtDinheiro = new JTextField();
		txtDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Pagamento pa = new Pagamento();

				String total = txtTotal.getText();
				String dinheiro = txtDinheiro.getText();
				pa.calcularTroco(total, dinheiro);
			}
		});
		txtDinheiro.setBounds(130, 167, 130, 20);
		panel.add(txtDinheiro);
		txtDinheiro.setColumns(10);

		txtTroco = new JTextField();
		txtTroco.setEditable(false);
		txtTroco.setBounds(130, 198, 130, 20);
		panel.add(txtTroco);
		txtTroco.setColumns(10);

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
