package view;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import controler.ControladoraPaciente;
import controler.ControladoraVacina;
import enu.EstagioPesquisa;
import enu.FaseVacina;
import model.vo.PacienteVo;
import model.vo.VacinaVO;

public class MenuVacina {
	
	private static final int OPCAO_MENU_CADASTRAR_VACINA = 1;
	private static final int OPCAO_MENU_CONSULTAR_TODAS_VACINAS = 2;
	private static final int OPCAO_MENU_EXCLUIR_VACINA = 3;
	private static final int OPCAO_MENU_VACINA_VOLTAR = 4;
	
	private static final int OPCAO_VACINA_SOMENTE_PESQUISADOR = 1;
	private static final int OPCAO_VACINA_VOLUNTARIOS = 2;
	private static final int OPCAO_VACINA_PUBLICO_GERAL = 3;
	private static final int OPCAO_VACINA_FIM = 99;
	
	private static final int OPCAO_ESTAGIO_INICIAL = 1;
	private static final int OPCAO_ESTAGIO_TESTES = 2;
	private static final int OPCAO_ESTAGIO_APLICACAO_MASSIVA = 3;
	private static final int OPCAO_ESTAGIO_FIM = 99;

	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void apresentarMenuVacina() throws FileNotFoundException {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_VACINA_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_VACINA: {
					this.cadastrarVacina();
					break;
				}
				case OPCAO_MENU_CONSULTAR_TODAS_VACINAS: {
					this.consultarTodasVacina();
					break;
				}
				case OPCAO_MENU_EXCLUIR_VACINA: {
					this.excluirVacina();
					break;
				}
				default: {
					JOptionPane.showMessageDialog(null, "\nOpção inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}

	private int apresentarOpcoesMenu() throws FileNotFoundException {
		StringBuilder msg = new StringBuilder();
		msg.append("Opções:\n");
		msg.append(OPCAO_MENU_CADASTRAR_VACINA + " - Cadastrar Vacina\n");
		msg.append(OPCAO_MENU_CONSULTAR_TODAS_VACINAS + " - Consultar todas as Vacinas\n");
		msg.append(OPCAO_MENU_EXCLUIR_VACINA + " - Excluir Vacina\n");
		msg.append(OPCAO_MENU_VACINA_VOLTAR + " - Voltar\n");
		msg.append("\nDigite a opção: ");
		
		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Menu Vacina",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = Integer.parseInt(valorInformadoPeloUsuario);
		
		new java.io.FileInputStream(valorInformadoPeloUsuario);
			return this.apresentarOpcoesMenu();
		}
		
	
	
	private void cadastrarVacina() throws FileNotFoundException {
		VacinaVO vacinaVO = new VacinaVO();
		
		String nomeInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Nome");
		vacinaVO.setNomeVacina(nomeInformadoPeloUsuario);
		
		String paisInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Pais de Origem");
		vacinaVO.setPaisOrigem(paisInformadoPeloUsuario);
		
		int estagio = this.apresentarOpcoesEstagio();
		while(estagio != OPCAO_ESTAGIO_FIM) {
			switch(estagio) {
				case OPCAO_ESTAGIO_INICIAL: {
					estagio = OPCAO_ESTAGIO_FIM;
					vacinaVO.setEstagioPesquisa(EstagioPesquisa.INICIAL);
					break;
				}
				case OPCAO_ESTAGIO_TESTES: {
					estagio = OPCAO_ESTAGIO_FIM;
					vacinaVO.setEstagioPesquisa(EstagioPesquisa.TESTES);
					break;
				}
				case OPCAO_ESTAGIO_APLICACAO_MASSIVA: {
					estagio = OPCAO_ESTAGIO_FIM;
					vacinaVO.setEstagioPesquisa(EstagioPesquisa.APLICACAO_MASSIVA);
					break;
				}
				default: {
					JOptionPane.showMessageDialog(null, "\nOpção inválida!");
					estagio = this.apresentarOpcoesEstagio();
				}
			}
		}
		
		LocalDate dataInformadoPeloUsuario = LocalDate.parse(JOptionPane.showInputDialog(null, "Digite a Data de Inicio da Pesquisa"), dataFormatter);
		vacinaVO.setDataInicioPesquisa(dataInformadoPeloUsuario);
		
		int dosesInformadoPeloUsuario = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de doses"));
		vacinaVO.setQuantidadeDoses(dosesInformadoPeloUsuario);
		
		int opcao = this.apresentarOpcoesFaseVacina();
		while(opcao != OPCAO_VACINA_FIM) {
			switch(opcao) {
				case OPCAO_VACINA_SOMENTE_PESQUISADOR: {
					opcao = OPCAO_VACINA_FIM;
					vacinaVO.setFaseVacina(FaseVacina.SOMENTE_PESQUISADOR);
					break;
				}
				case OPCAO_VACINA_VOLUNTARIOS: {
					opcao = OPCAO_VACINA_FIM;
					vacinaVO.setFaseVacina(FaseVacina.VOLUNTARIO);
					break;
				}
				case OPCAO_VACINA_PUBLICO_GERAL: {
					opcao = OPCAO_VACINA_FIM;
					vacinaVO.setFaseVacina(FaseVacina.PUBLICO_GERAL);
					break;
				}
				default: {
					JOptionPane.showMessageDialog(null, "\nOpção inválida!");
					opcao = this.apresentarOpcoesFaseVacina();
				}
			}
		}
		
		int ativo = JOptionPane.showConfirmDialog(null, "A vacina pode ser considerada ativa?", "Sim ou Não", JOptionPane.YES_NO_OPTION);
		boolean flag;
		if (ativo == JOptionPane.YES_OPTION) {
			flag = true;
		} else {
			flag = false;
		}
		
		vacinaVO.setVacinaAtiva(flag);
		
		PacienteVo pesquisadorInformadoPeloUsuario = new PacienteVo();
		
		String nomePesquisador = JOptionPane.showInputDialog(null, "Digite o nome do responsavel pela vacina");
		pesquisadorInformadoPeloUsuario.setNome(nomePesquisador);
		
		String cpfPesquisador = JOptionPane.showInputDialog(null, "Digite o CPF do responsavel pela vacina");
		pesquisadorInformadoPeloUsuario.setCpf(cpfPesquisador);
		
		ControladoraPaciente controladoraPaciente = new ControladoraPaciente();
		pesquisadorInformadoPeloUsuario = controladoraPaciente.consultarControladoraPaciente(pesquisadorInformadoPeloUsuario);
		
		String resultado = "";
		
		if (pesquisadorInformadoPeloUsuario.getIdPaciente() > 0) {
			vacinaVO.setPesquisadorResponsavel(pesquisadorInformadoPeloUsuario.getIdPaciente());
			
			ControladoraVacina controladoraVacina = new ControladoraVacina();
			resultado = controladoraVacina.cadastrarVacinaController(vacinaVO);
		} else {
			JOptionPane.showMessageDialog(null, "Pesquisador ainda não cadastrado! Favor cadastrar!", "MENU VACINA",
					JOptionPane.WARNING_MESSAGE);
			MenuPaciente menuPacientee = new MenuPaciente();
			int idPesquisador = MenuPaciente.cadastrarPaciente();
			
			JOptionPane.showMessageDialog(null, "código ID do pesquisador: " + idPesquisador, "MENU VACINA",
					JOptionPane.INFORMATION_MESSAGE);
			vacinaVO.setPesquisadorResponsavel(idPesquisador);
			
			ControladoraVacina controladoraVacina = new ControladoraVacina();
			resultado = controladoraVacina.cadastrarVacinaController(vacinaVO);
		}
		JOptionPane.showMessageDialog(null, resultado);
	}

	private void consultarTodasVacina() {
		
		ControladoraVacina controladoraVacina = new ControladoraVacina();
		List<VacinaVO> todasVacinas = controladoraVacina.consultarVacinaController();
		String listaVacina = "";
		
		for (VacinaVO vacinaVO : todasVacinas) {
			listaVacina += vacinaVO + "\n";
			
			if (vacinaVO.isVacinaAtiva()) {
				listaVacina += "\n";
			} else {
				listaVacina += "\n";
			}
		}
		
		JOptionPane.showMessageDialog(null, listaVacina, "Vacinas", JOptionPane.INFORMATION_MESSAGE, null);
	}

	private void excluirVacina() {
		VacinaVO vacinaVO = new VacinaVO();
		
		String nome = JOptionPane.showInputDialog(null, "Dgite o nome da vacina a ser excluida");
		vacinaVO.setNomeVacina(nome);
		
		String paisOrigem = JOptionPane.showInputDialog(null, "Digite o nome do pais de origem da vacina a ser excluida");
		vacinaVO.setPaisOrigem(paisOrigem);
		
		ControladoraVacina controladoraVacina = new ControladoraVacina();
		String resultado = controladoraVacina.excluirVacinaController(vacinaVO);
		
		JOptionPane.showMessageDialog(null, resultado);
	}
	
	private int apresentarOpcoesEstagio() throws FileNotFoundException {
		StringBuilder msg = new StringBuilder();
		msg.append("\nOpções:\n");
		msg.append(OPCAO_ESTAGIO_INICIAL + " - INICIAL\n");
		msg.append(OPCAO_ESTAGIO_TESTES + " - TESTES\n");
		msg.append(OPCAO_ESTAGIO_APLICACAO_MASSIVA + " - APLICACAO_MASSIVA\n");
		msg.append("\nDigite a opção:");
		
		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Estagio Pesquisa",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = Integer.parseInt(valorInformadoPeloUsuario);
		
		new java.io.FileInputStream("VALOR_INVALIDO"); 
			return this.apresentarOpcoesMenu();
		}
		
	

	private int apresentarOpcoesFaseVacina() throws FileNotFoundException {
		StringBuilder msg = new StringBuilder();
		msg.append("Opções:\n");
		msg.append(OPCAO_VACINA_SOMENTE_PESQUISADOR + " - Somete pesquisador\n");
		msg.append(OPCAO_VACINA_VOLUNTARIOS + " - Voluntarios\n");
		msg.append(OPCAO_VACINA_PUBLICO_GERAL + " - Publico geral\n");
		msg.append("\nDigite a opção:");

		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Fase vacina",
				JOptionPane.INFORMATION_MESSAGE);

		Integer.parseInt(valorInformadoPeloUsuario);
		
		new java.io.FileInputStream("VALOR_INVALIDO");
			return this.apresentarOpcoesMenu();
		
		
	}

}
