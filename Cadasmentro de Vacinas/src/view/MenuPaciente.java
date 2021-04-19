package view;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import controler.ControladoraPaciente;
import model.vo.PacienteVo;

public class MenuPaciente {
	
	private static final int OPCAO_MENU_CADASTRAR_PACIENTE = 1;
	private static final int OPCAO_MENU_VOLTAR = 2;
	
	private static final int OPCAO_PACIENTE_FIM = 99;
	private static final int OPCAO_PESQUISADOR = 1;
	private static final int OPCAO_PACIENTE = 2;
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void apresentarMenuPaciente() throws FileNotFoundException {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_VOLTAR) {
			switch (opcao) {
				case OPCAO_MENU_CADASTRAR_PACIENTE : {
					this.cadastrarPaciente1();
					opcao = OPCAO_MENU_VOLTAR;
					break;
				}
				default : {
					System.out.println( "\nOpção inválida!");
				}
			}
		}
	}

	public String cadastrarPaciente1() {
		
		PacienteVo pacienteVO = new PacienteVo();
		ControladoraPaciente controladoraPaciente = new ControladoraPaciente();
		
		System.out.println( "Digite o nome do paciente");
		Object nome = null;
		pacienteVO.setNome(nome);		
		
		LocalDate dataNascimento = (LocalDate.parse(JOptionPane.showInputDialog(null,
				"Digite a data de nascimento  da pessoa"), dataFormatter));
		pacienteVO.setDataNascimento(dataNascimento);
		 
		char sexo = JOptionPane.showInputDialog(null, "Digite o sexo  da paciente").charAt(0);
		pacienteVO.setSexo(sexo);

		String cpf = JOptionPane.showInputDialog(null, "Digite o cpf  da paciente");
		pacienteVO.setCpf(cpf); 

		
		
		String retorno = ControladoraPaciente.cadastrarPaciente(pacienteVO);
		
		if (pacienteVO.getIdPaciente() > 0) {
			JOptionPane.showMessageDialog(null, "paciente cadastrado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o Paciente");
		}

		return retorno;
	}

	
	
		static int cadastrarPaciente() {
			PacienteVo pacienteVO = new PacienteVo();

			String nomeInformado = JOptionPane.showInputDialog("Digite o nome do paciente:");
			pacienteVO.setNome(nomeInformado);

			pacienteVO.setCpf(JOptionPane.showInputDialog("Digite o CPF do paciente:"));

			ControladoraPaciente controladorapaciente = new ControladoraPaciente();
			String resultado = controladorapaciente.cadastrarPaciente(pacienteVO);
			JOptionPane.showMessageDialog(null, resultado);
			return 0;
		}

			
	

	private int apresentarOpcoesMenu() throws FileNotFoundException {
			StringBuilder msg = new StringBuilder();
			msg.append("Opções:\n");
			msg.append(OPCAO_MENU_CADASTRAR_PACIENTE + " Cadastrar Paciente\n");
			msg.append(OPCAO_MENU_VOLTAR + " Voltar\n");
			msg.append("\nDigite a opção: ");
			
			String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Menu Vacina",
					JOptionPane.INFORMATION_MESSAGE);
			
			int opcaoSelecionada = Integer.parseInt(valorInformadoPeloUsuario);
			
	        new java.io.FileInputStream("Valor invalido");


				
			{	this.apresentarOpcoesMenu();
			}
			
			return opcaoSelecionada;
	}

	}
	

