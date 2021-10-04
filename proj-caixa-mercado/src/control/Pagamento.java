
package control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Pagamento {

	public String verificarPreenchimento(String total, String formaPagamento, String RegistroVendas) {

		if (!total.equals("")) {
			if (!formaPagamento.equals("Selecione...")) {
				return "ok";
			} else {
			}
		} else {
		}
		return null;
	}

	public String registrar(String total, String formaPagamento, String RegistroVendas) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(RegistroVendas, true));
			bw.write(total + "#" + formaPagamento);
			bw.newLine();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String calcularTroco(String total, String dinheiro) {
		int auxTroco = Integer.parseInt(dinheiro) - Integer.parseInt(total);
		String troco = auxTroco + "";
		return troco;
	}
}
