package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class Funcionario {

	public boolean verificarPreenchimento(String auxNome, String auxCpf, String auxData, String auxLogin, String auxTel,
			String auxSenha, String PessoasCadastradas) {
		if (!auxNome.equals("")) {
			if (!auxLogin.equals("")) {
				if (!auxSenha.equals("")) {
					return true;
				} else {
				}
			} else {
			}
		}
		return false;
	}

	public void salvar(String auxNome, String auxCpf, String auxData, String auxLogin, String auxTel, String auxSenha,
			String PessoasCadastradas) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(PessoasCadastradas));
			BufferedWriter bw = new BufferedWriter(new FileWriter(PessoasCadastradas, true));

			boolean cont = false;
			while (br.ready()) {
				String vetor[] = br.readLine().split("#");
				if (vetor[0].equals(auxLogin)) {
					JOptionPane.showMessageDialog(null,
							"Login já cadastrado! Entre com a senha ou crie um novo login.");
					cont = true;
					break;
				}
			}
			if (cont == false) {
					bw.write(auxLogin + "#" + auxNome + "#" + auxCpf + "#" + auxData + "#" + auxTel + "#"
							+ auxSenha);
					bw.newLine();
					JOptionPane.showMessageDialog(null, "Login cadastrado com sucesso!");
				}
			bw.close();
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean localizarCadastro(String PessoasCadastradas, String auxLogin, String auxSenha) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(PessoasCadastradas));
			while (br.ready()) {
				String vetor[] = br.readLine().split("#");
				if (auxLogin.equals(vetor[0])) {
					if (auxSenha.equals(vetor[5])) {
						br.close();
						return true;
					}
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean vericarLogin(String auxLogin, String cadastroPessoas) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cadastroPessoas));

			while (br.ready()) {

				String vetor[] = br.readLine().split("#");
				if (auxLogin.equals(vetor[0])) {
					br.close();
					return true;
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
