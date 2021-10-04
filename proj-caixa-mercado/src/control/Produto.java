
package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Produto {
	private String auxValor, auxUddMedida, auxDesc, nrLocalizado, auxTexto, auxCod;

	public boolean verificarPreenchimento(String descLocalizado, String valorLocalizado, String uddMedidaLocalizado) {
		if (!descLocalizado.equals("")) {
			if (!valorLocalizado.equals("")) {
				if (!uddMedidaLocalizado.equals("Selecione...")) {
					if (descLocalizado.length() <= 25) {
						if (Integer.parseInt(valorLocalizado) >= 0) {
							return true;
						} else {
							JOptionPane.showMessageDialog(null, "Valor não pode ser menor que 0,0 reais.");
							return false;
						}
					} else {
						JOptionPane.showMessageDialog(null, "Descrição não pode conter mais de 25 caracteres.");
						return false;
					}
				} else {
				}
			} else {
			}
		} else {
		}
		return false;
	}

	public void salvar(String BDProdutos, String descLocalizado, String valorLocalizado, String uddMedidaLocalizado) {
		try {
			int i = 99;
			BufferedReader br = new BufferedReader(new FileReader(BDProdutos));
			BufferedWriter bw = new BufferedWriter(new FileWriter(BDProdutos, true));
			while (br.ready()) {
				String linha = br.readLine();
				String vetor[] = linha.split("#");
				i = Integer.parseInt(vetor[0]);
			}
			++i;
			bw.write(i + "#" + descLocalizado + "#" + valorLocalizado + "#" + uddMedidaLocalizado);
			bw.newLine();

			br.close();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String verificarControlador(String m) {
		if (m.equals("ativo")) {
			return m = "";
		} else {
			return m = "";
		}
	}

	public boolean localizar(String BDProdutos) {
		try {
			setNrLocalizado(JOptionPane.showInputDialog(null, "Digite o código do cadastro a ser localizado: "));
			BufferedReader br = new BufferedReader(new FileReader(BDProdutos));
			while (br.ready()) {
				String linha = br.readLine();
				String vetor[] = linha.split("#");
				if (vetor[0].equalsIgnoreCase(getNrLocalizado())) {
					setAuxDesc(vetor[1]);
					setAuxValor(vetor[2]);
					setAuxUddMedida(vetor[3]);

					JOptionPane.showMessageDialog(null, "Localizado!");

					br.close();
					return true;
				}
			}
			br.close();
			return false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public void excluir(String BDProdutos) {
		try {
			String cont = "";
			String i = BDProdutos.replace("BDProdutos.txt", "temp.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(BDProdutos));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			String nrLocalizado = JOptionPane.showInputDialog(null, "Digite o código do cadastro a ser excluído: ");

			while (br.ready()) {
				String linha = br.readLine();
				String vetor[] = linha.split("#");
				if (vetor[0].equalsIgnoreCase(nrLocalizado)) {
					JOptionPane.showMessageDialog(null, "Excluído!");
					cont = "ok";
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			if (cont.equals("")) {
				JOptionPane.showMessageDialog(null, "Verifique o código digitado e tente novamente.");
			}
			br.close();
			bw.close();

			BufferedReader braux = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(BDProdutos));

			while (braux.ready()) {
				bwaux.write(braux.readLine());
				bwaux.newLine();
			}
			bwaux.close();
			braux.close();
			j.delete();

		} catch (Exception f) {
			System.out.println(f);
		}
	}

	public String menu() {
		String op = JOptionPane.showInputDialog(null,
				"Digite a opção desejada: \n1 - Editar Descrição; \n2 - Editar Valor Unitário; \n3 - Editar Unidade Medida. ");
		switch (op) {
		case "1": {
			setAuxDesc(JOptionPane.showInputDialog(null, "Digite a nova descrição: "));
			return getAuxDesc();
		}
		case "2": {
			setAuxValor(JOptionPane.showInputDialog(null, "Digite o novo valor unitário: "));
			return getAuxValor();
		}
		case "3": {
			setAuxUddMedida(
					JOptionPane.showInputDialog(null, "Digite a nova unidade de medida: (KG/L - em maiúsculo)"));
			return getAuxUddMedida();
		}
		}
		return null;
	}

	public void modificar(String opMenu, String BDProdutos) {
		if (opMenu.equals(getAuxDesc())) {
			try {
				String i = BDProdutos.replace("BDProdutos.txt", "temp.txt");
				File j = new File(i);
				j.createNewFile();

				BufferedReader br = new BufferedReader(new FileReader(BDProdutos));
				BufferedWriter bw = new BufferedWriter(new FileWriter(i));

				String[] vetor;

				while (br.ready()) {
					br.ready();
					String linha = br.readLine();
					vetor = linha.split("#");

					if (vetor[0].equalsIgnoreCase(getNrLocalizado())) {
						bw.write(vetor[0] + "#" + getAuxDesc() + "#" + vetor[2] + "#" + vetor[3]);
						bw.newLine();
						JOptionPane.showMessageDialog(null, "Descrição do produto atualizada com sucesso!");
					} else {
						bw.write(linha);
						bw.newLine();
					}
				}
				br.close();
				bw.close();

				BufferedReader braux = new BufferedReader(new FileReader(i));
				BufferedWriter bwaux = new BufferedWriter(new FileWriter(BDProdutos));

				while (braux.ready()) {
					bwaux.write(braux.readLine());
					bwaux.newLine();
				}
				bwaux.close();
				braux.close();
				j.delete();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro no programa.");
			}
		} else {
			if (opMenu.equals(getAuxUddMedida())) {
				try {
					String i = BDProdutos.replace("BDProdutos.txt", "temp.txt");
					File j = new File(i);
					j.createNewFile();

					BufferedReader br = new BufferedReader(new FileReader(BDProdutos));
					BufferedWriter bw = new BufferedWriter(new FileWriter(i));

					String[] vetor;

					while (br.ready()) {
						br.ready();
						String linha = br.readLine();
						vetor = linha.split("#");

						if (vetor[0].equalsIgnoreCase(getNrLocalizado())) {
							if (getAuxUddMedida().equals("KG") || getAuxUddMedida().equals("L")) {
								bw.write(vetor[0] + "#" + vetor[1] + "#" + vetor[2] + "#" + getAuxUddMedida());
								bw.newLine();
								JOptionPane.showMessageDialog(null, "Unidade de Medida atualizada com sucesso!");
							} else {
								bw.write(vetor[0] + "#" + vetor[1] + "#" + vetor[2] + "#" + vetor[3]);
								bw.newLine();
								JOptionPane.showMessageDialog(null, "Unidade de Medida não foi atualizada.");
							}
						} else {
							bw.write(linha);
							bw.newLine();
						}
					}
					br.close();
					bw.close();

					BufferedReader braux = new BufferedReader(new FileReader(i));
					BufferedWriter bwaux = new BufferedWriter(new FileWriter(BDProdutos));

					while (braux.ready()) {
						bwaux.write(braux.readLine());
						bwaux.newLine();
					}
					bwaux.close();
					braux.close();
					j.delete();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro no programa.");
				}
			} else {
				if (opMenu.equals(getAuxValor())) {
					try {
						String i = BDProdutos.replace("BDProdutos.txt", "temp.txt");
						File j = new File(i);
						j.createNewFile();

						BufferedReader br = new BufferedReader(new FileReader(BDProdutos));
						BufferedWriter bw = new BufferedWriter(new FileWriter(i));

						String[] vetor;

						while (br.ready()) {
							br.ready();
							String linha = br.readLine();
							vetor = linha.split("#");

							if (vetor[0].equalsIgnoreCase(getNrLocalizado())) {
								bw.write(vetor[0] + "#" + vetor[1] + "#" + getAuxValor() + "#" + vetor[3]);
								bw.newLine();
								JOptionPane.showMessageDialog(null, "Valor unitário atualizado com sucesso!");
							} else {
								bw.write(linha);
								bw.newLine();
							}
						}
						br.close();
						bw.close();

						BufferedReader braux = new BufferedReader(new FileReader(i));
						BufferedWriter bwaux = new BufferedWriter(new FileWriter(BDProdutos));

						while (braux.ready()) {
							bwaux.write(braux.readLine());
							bwaux.newLine();
						}
						bwaux.close();
						braux.close();
						j.delete();

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro no programa.");
					}
				}
			}
		}
	}

	public void localizarProduto(String BDProdutos, String codLocalizado) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(BDProdutos));

			while (br.ready()) {
				String vetor[] = br.readLine().split("#");
				if (vetor[0].equals(codLocalizado)) {
					setAuxDesc(vetor[1]);
					setAuxValor(vetor[2]);
					setAuxUddMedida(vetor[3]);
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int contarProdutos(String listaCompras) {
		try {
			int i = 0;
			BufferedReader br = new BufferedReader(new FileReader(listaCompras));

			while (br.ready()) {
				String linha = br.readLine();
				String vetor[] = linha.split("#");
				i = Integer.parseInt(vetor[0]);
			}
			br.close();
			return ++i;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public void limparTela(String listaCompras) {
		try {
			String i = listaCompras.replace("listaCompras.txt", "temp.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(listaCompras));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			while (br.ready()) {
				String linha = br.readLine();
				if (!linha.isEmpty()) {
					bw.write("");
				}
			}
			br.close();
			bw.close();

			BufferedReader braux = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(listaCompras));

			while (braux.ready()) {
				bwaux.write(braux.readLine());
			}
			bwaux.close();
			braux.close();
			j.delete();

		} catch (Exception f) {
			System.out.println(f);
		}
	}

	int guardarValores;

	public String calcularSubtotal(String auxValor, String listaCompras) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(listaCompras));

			while (br.ready()) {
				String vetor[] = br.readLine().split("#");
				if (!vetor[0].isEmpty()) {
					guardarValores = guardarValores + Integer.parseInt(vetor[3]) * Integer.parseInt(vetor[4]);
				}
			}
			br.close();
			return guardarValores + "";
		} catch (Exception f) {
			System.out.println(f);
		}
		return null;

	}

	public void incluir(String auxCod, String auxQtdd, String listaCompras, String BDProdutos, String auxDesc,
			String auxUddMedida, String auxValor) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(BDProdutos));
			BufferedWriter bw = new BufferedWriter(new FileWriter(listaCompras, true));

			while (br.ready()) {
				String vetor[] = br.readLine().split("#");
				if (auxCod.equals(vetor[0])) {
					bw.write(contarProdutos(listaCompras) + "#" + auxDesc + "#" + auxUddMedida + "#" + auxValor + "#"
							+ auxQtdd);
					bw.newLine();
				}
			}
			bw.close();
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String listar(String listaCompras) {
		try {
			BufferedReader brr = new BufferedReader(new FileReader(listaCompras));
			String guardarInf = "";
			String linha = "";
			while (brr.ready()) {
				linha = brr.readLine().replace("#", "\t");
				guardarInf = guardarInf + "\n" + linha;
			}
			brr.close();
			return guardarInf;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public String getNrLocalizado() {
		return nrLocalizado;
	}

	public void setNrLocalizado(String nrLocalizado) {
		this.nrLocalizado = nrLocalizado;
	}

	public String getAuxValor() {
		return auxValor;
	}

	public void setAuxValor(String auxValor) {
		this.auxValor = auxValor;
	}

	public String getAuxUddMedida() {
		return auxUddMedida;
	}

	public void setAuxUddMedida(String auxUddMedida) {
		this.auxUddMedida = auxUddMedida;
	}

	public String getAuxTexto() {
		return auxTexto;
	}

	public void setAuxTexto(String auxTexto) {
		this.auxTexto = auxTexto;
	}

	public String getAuxDesc() {
		return auxDesc;
	}

	public void setAuxDesc(String auxDesc) {
		this.auxDesc = auxDesc;
	}

	public String getAuxCod() {
		return auxCod;
	}

	public void setAuxCod(String auxCod) {
		this.auxCod = auxCod;
	}
}
