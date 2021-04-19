package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controler.ControladoraPaciente;
import controler.ControladoraVacina;
import enu.EstagioPesquisa;
import enu.FaseVacina;
import jdk.nashorn.internal.parser.DateParser;
import model.vo.PacienteVo;
import model.vo.VacinaVO;
import sun.awt.DebugSettings;

public class TelaCadastroVacina {

	private JFrame frame;
	private JTextField txtNomeVacina;
	private JTextField txtPaisOrigem;
	private JTextField txtNomePesquisador;
	private JTextField txtQuantidadeDoses;
	private JTextField txtCpfPesquisador;
	private JComboBox cbxEstagioPesquisa;
	private JComboBox cbxFaseVacina;
	private JComboBox cbxEstadoVacina;
	private VacinaVO vacinaVO;
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JButton btnBuscarPesquisador;
	private String dateSettings;
	private DateParser dataTeste;
	private Integer setDateField;
	private boolean setDateField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVacina window = new TelaCadastroVacina();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroVacina() {
		initialize();
	}

	public TelaCadastroVacina(VacinaVO vacinaSelecionada) {

	    System.out.println("tela cadastro de vacina");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cadastro de Vacina");
		frame.setBounds(100, 100, 609, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPesquisadorResponsavel = new JLabel("Nome do pesquisador respons\u00E1vel");
		lblPesquisadorResponsavel.setBounds(27, 34, 204, 14);
		frame.getContentPane().add(lblPesquisadorResponsavel);
		
		txtNomePesquisador = new JTextField();
		txtNomePesquisador.setColumns(10);
		txtNomePesquisador.setBounds(241, 31, 317, 20);
		frame.getContentPane().add(txtNomePesquisador);
		
		JLabel lblCpfPesquisador = new JLabel("Cpf do pesquisador respons\u00E1vel");
		lblCpfPesquisador.setBounds(29, 69, 202, 14);
		frame.getContentPane().add(lblCpfPesquisador);
		
		MaskFormatter mascaraCPF;
		try {
			mascaraCPF = new MaskFormatter("###.###.###-##");
			txtCpfPesquisador = new JFormattedTextField(mascaraCPF);
		} catch (ParseException e1) {
		}
		txtCpfPesquisador.setColumns(10);
		txtCpfPesquisador.setBounds(241, 63, 317, 20);
		frame.getContentPane().add(txtCpfPesquisador);
		
		btnBuscarPesquisador = new JButton("Buscar pesquisador");
		btnBuscarPesquisador.addActionListener(new ActionListener() {
			public void actionPerformed1(ActionEvent e) {
				if (txtNomePesquisador.getText().isEmpty() || txtCpfPesquisador.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "É necessario preencher o Nome e Cpf do pesquisador para verificar a sua existência no sistema", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				} else {
					VerificarPaciente();
				}
				
			}

			private int VerificarPaciente() {
				PacienteVo pesquisador = new PacienteVo();
				VacinaVO novaVacina = new VacinaVO();
				
				pesquisador.setNome(txtNomePesquisador.getText());
				pesquisador.setCpf(txtCpfPesquisador.getText().replace(".", "").replace("-", ""));
				
				ControladoraPaciente controladoraPessoa = new ControladoraPaciente();
				pesquisador = controladoraPessoa.consultarControladoraPaciente(pesquisador);
				int opcao;
				String resultado = "Pesquisador Válido"; 
				
				if (pesquisador.getIdPaciente() > 0) {
					novaVacina.setPesquisadorResponsavel(pesquisador.getIdPaciente());
				} else {
					opcao = JOptionPane.showConfirmDialog(null, "Pesquisador(a) ainda não cadastrado(a)!\nVoce Deseja cadastrar?", "MENU VACINA", JOptionPane.YES_NO_OPTION);
					if(opcao == JOptionPane.YES_OPTION) {
						MenuPaciente menuPaciente = new MenuPaciente();
						int idPesquisador = menuPaciente.cadastrarPaciente();
						
						JOptionPane.showMessageDialog(null, "Código ID do pesquisador: " + idPesquisador, "MENU VACINA",
								JOptionPane.INFORMATION_MESSAGE);
						
						novaVacina.setPesquisadorResponsavel(idPesquisador);
					} else {
						resultado = "Não se esqueça que é necessário obter o\nNome e Cpf de uma pesquisador existente\npara cadastrar uma Vacina";
					}
				}
				JOptionPane.showMessageDialog(null, resultado);
				
				return pesquisador.getIdPaciente();
			
				
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnBuscarPesquisador.setBounds(392, 97, 164, 22);
		frame.getContentPane().add(btnBuscarPesquisador);
		
		JLabel lblNomeVacina = new JLabel("Nome da vacina");
		lblNomeVacina.setBounds(27, 141, 90, 14);
		frame.getContentPane().add(lblNomeVacina);
		
		txtNomeVacina = new JTextField();
		txtNomeVacina.setBounds(127, 138, 429, 20);
		frame.getContentPane().add(txtNomeVacina);
		txtNomeVacina.setColumns(10);
		
		JLabel lblPaisOrigem = new JLabel("Pais de origem");
		lblPaisOrigem.setBounds(27, 179, 90, 14);
		frame.getContentPane().add(lblPaisOrigem);
		
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da pesquisa");
		lblEstagioPesquisa.setBounds(27, 216, 114, 14);
		frame.getContentPane().add(lblEstagioPesquisa);
		
		JLabel lblDataInicioPesquisa = new JLabel("Data de in\u00EDcio da pesquisa");
		lblDataInicioPesquisa.setBounds(27, 255, 182, 14);
		frame.getContentPane().add(lblDataInicioPesquisa);
		
		JLabel lblFaseVacina = new JLabel("Fase da vacina");
		lblFaseVacina.setBounds(27, 286, 90, 14);
		frame.getContentPane().add(lblFaseVacina);
		
		JLabel lblQuantidadeDoses = new JLabel("Quantidade de doses");
		lblQuantidadeDoses.setBounds(27, 318, 171, 14);
		frame.getContentPane().add(lblQuantidadeDoses);
		
		txtPaisOrigem = new JTextField();
		txtPaisOrigem.setColumns(10);
		txtPaisOrigem.setBounds(127, 176, 429, 20);
		frame.getContentPane().add(txtPaisOrigem);
		
		
		dataTeste = new DateParser(dateSettings);
		
		
		//Colocar Máscara
//		txtDataInicioPesquisa = new JTextField();
//		txtDataInicioPesquisa.setColumns(10);
//		txtDataInicioPesquisa.setBounds(219, 252, 337, 20);
//		janela.getContentPane().add(txtDataInicioPesquisa);

		JLabel lblEstagioVacina = new JLabel("Estado da vacina");
		lblEstagioVacina.setBounds(27, 351, 102, 14);
		frame.getContentPane().add(lblEstagioVacina);
		
		cbxEstagioPesquisa = new JComboBox();
		cbxEstagioPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Inicial", "Testes", "Aplicação massiva"}));
		cbxEstagioPesquisa.setBounds(159, 213, 397, 20);             
		frame.getContentPane().add(cbxEstagioPesquisa);                  
		
		cbxFaseVacina = new JComboBox();
		cbxFaseVacina.setModel(new DefaultComboBoxModel(new String[] {"Somente pesquisador", "Voluntário", "Público geral"}));
		cbxFaseVacina.setBounds(159, 283, 397, 20);
		frame.getContentPane().add(cbxFaseVacina);
		
		cbxEstadoVacina = new JComboBox();
		cbxEstadoVacina.setModel(new DefaultComboBoxModel(new String[] {"Ativada", "Desativada"}));
		cbxEstadoVacina.setBounds(159, 348, 397, 20);
		frame.getContentPane().add(cbxEstadoVacina);
		
		JButton btnSalvarVacina = new JButton("Salvar vacina");
		btnSalvarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomePesquisador.getText().isEmpty() || txtCpfPesquisador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "É necessario preencher o Nome e Cpf do pesquisador para verificar a sua existência no sistema", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				}
				
				if (txtNomeVacina.getText().isEmpty() 
						|| txtPaisOrigem.getText().isEmpty()
						|| dataTeste.getDateFields() == null 
						|| txtQuantidadeDoses.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "É necessario preencher todos os campos para cadastrar a vacina", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				} else {
					cadastrarVacina();
				}
			}
		});
		btnSalvarVacina.setBounds(433, 386, 125, 22);
		
		JButton btnAtualizarVacina = new JButton("Atualizar vacina");
		btnAtualizarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomePesquisador.getText().isEmpty() || txtCpfPesquisador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "É necessario preencher o Nome e Cpf do pesquisador para verificar a sua existência no sistema", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				}

				if (txtNomeVacina.getText().isEmpty() 
						|| txtPaisOrigem.getText().isEmpty()
						|| dataTeste.getDateFields() == null 
						|| txtQuantidadeDoses.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "É necessario preencher todos os campos para cadastrar a vacina", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				} else {
					cadastrarVacina();
				}
			}
		});
		btnAtualizarVacina.setBounds(433, 386, 125, 22);
		
		MaskFormatter mascaraDose;
		try {
			mascaraDose = new MaskFormatter("#");
			txtQuantidadeDoses = new JFormattedTextField(mascaraDose);
		} catch (ParseException e1) {
		}
		txtQuantidadeDoses.setColumns(10);
		txtQuantidadeDoses.setBounds(208, 315, 348, 20);
		frame.getContentPane().add(txtQuantidadeDoses);
		
		if (vacinaVO != null) {
			frame.setTitle("Edição de Endereço");
			frame.getContentPane().add(btnAtualizarVacina);
			preencerVacinaNaTela(vacinaVO);
		} else {
			frame.getContentPane().add(btnSalvarVacina);
			frame.setTitle("Cadastro de Vacina");
		}
	}

	private void preencerVacinaNaTela(VacinaVO v) {
		
		PacienteVo pesquisador = retornarPesquisador(v.getPesquisadorResponsavel());
		
		this.txtNomePesquisador.setText(pesquisador.getNome());
		this.txtCpfPesquisador.setText(pesquisador.getCpf());
		
		this.txtNomeVacina.setText(v.getNomeVacina());
		this.txtPaisOrigem.setText(v.getPaisOrigem());
		
		this.cbxEstagioPesquisa.setSelectedItem(v.getEstagioPesquisa().toString());
		
		if (v.getEstagioPesquisa().toString().equals("INICIAL")) {
			this.cbxEstagioPesquisa.setSelectedIndex(0);
		} else if (v.getEstagioPesquisa().toString().equals("TESTES")) {
			this.cbxEstagioPesquisa.setSelectedIndex(1);
		} else if (v.getEstagioPesquisa().toString().equals("APLICACAO_MASSIVA")) {
			this.cbxEstagioPesquisa.setSelectedIndex(2);
		}
		
		
		if (v.getFase().toString().equals("SOMENTE_PESQUISADOR")) {
			this.cbxFaseVacina.setSelectedIndex(0);
		} else if (v.getFase().toString().equals("VOLUNTARIO")) {
			this.cbxFaseVacina.setSelectedIndex(1);
		} else if (v.getFase().toString().equals("PUBLICO_GERAL")) {
			this.cbxFaseVacina.setSelectedIndex(2);
		}
		this.txtQuantidadeDoses.setText(String.valueOf(v.getQuantidadeDoses()));
		
		if(v.isVacinaAtiva()) {
			this.cbxEstadoVacina.setSelectedIndex(0);
		} else {			
			this.cbxEstadoVacina.setSelectedIndex(1);
		}
	}

	private PacienteVo retornarPesquisador(int pesquisadorResponsavel) {
		ControladoraPaciente controladoraPaciente = new ControladoraPaciente();
		PacienteVo pesquisador = controladoraPaciente.consultarControladoraPaciente(pesquisadorResponsavel);
		return pesquisador;
	}

		
	protected void cadastrarVacina() {
		VacinaVO novaVacina = new VacinaVO();
		
		novaVacina.setNomeVacina(txtNomeVacina.getText());
		novaVacina.setPaisOrigem(txtPaisOrigem.getText());
		
		if (cbxEstagioPesquisa.getSelectedItem().toString().equals("Inicial")) {
			novaVacina.setEstagioPesquisa(EstagioPesquisa.INICIAL);
		} else if (cbxEstagioPesquisa.getSelectedItem().toString().equals("Testes")) {
			novaVacina.setEstagioPesquisa(EstagioPesquisa.TESTES);
		} else if (cbxEstagioPesquisa.getSelectedItem().toString().equals("Aplicação massiva")) {
			novaVacina.setEstagioPesquisa(EstagioPesquisa.APLICACAO_MASSIVA);
		}
		
		// novaVacina.setDataInicioPesquisa(LocalDate.parse(txtDataInicioPesquisa.getText(), dataFormatter));
		novaVacina.setDataInicioPesquisa(dataTeste.getDateFields());
		
		if (cbxFaseVacina.getSelectedItem().toString().equals("Somente pesquisador")) {
			novaVacina.setFaseVacina(FaseVacina.SOMENTE_PESQUISADOR);
		} else if (cbxFaseVacina.getSelectedItem().toString().equals("Voluntário")) {
			novaVacina.setFaseVacina(FaseVacina.VOLUNTARIO);
		} else if (cbxFaseVacina.getSelectedItem().toString().equals("Público geral")) {
			novaVacina.setFaseVacina(FaseVacina.PUBLICO_GERAL);
		}
		
		novaVacina.setQuantidadeDoses(Integer.parseInt(txtQuantidadeDoses.getText()));
		
		novaVacina.setVacinaAtiva(cbxEstadoVacina.getSelectedItem().toString().equals("Ativada"));
		
		int idPesquisador = VerificarPaciente();
		novaVacina.setPesquisadorResponsavel(idPesquisador);
		
		ControladoraVacina controladoraVacina = new ControladoraVacina();
		String resultado ="";
		
		if (novaVacina.getIdVacina() == null) {
			resultado = controladoraVacina.cadastrarVacinaController(novaVacina);
		} else {
			novaVacina.setIdVacina(vacinaVO.getIdVacina());
			resultado = controladoraVacina.atualizarVacina(novaVacina);
		}
		
		JOptionPane.showMessageDialog(null, resultado);
	}

	private int VerificarPaciente() {
		PacienteVo pesquisador = new PacienteVo();
		VacinaVO novaVacina = new VacinaVO();
		
		pesquisador.setNome(txtNomePesquisador.getText());
		pesquisador.setCpf(txtCpfPesquisador.getText().replace(".", "").replace("-", ""));
		
		ControladoraPaciente controladoraPessoa = new ControladoraPaciente();
		pesquisador = controladoraPessoa.consultarControladoraPaciente(pesquisador);
		int opcao;
		String resultado = "Pesquisador Válido"; 
		
		if (pesquisador.getIdPaciente() > 0) {
			novaVacina.setPesquisadorResponsavel(pesquisador.getIdPaciente());
		} else {
			opcao = JOptionPane.showConfirmDialog(null, "Pesquisador(a) ainda não cadastrado(a)!\nVoce Deseja cadastrar?", "MENU VACINA", JOptionPane.YES_NO_OPTION);
			if(opcao == JOptionPane.YES_OPTION) {
				MenuPaciente menuPaciente = new MenuPaciente();
				int idPesquisador = menuPaciente.cadastrarPaciente();
				
				JOptionPane.showMessageDialog(null, "Código ID do pesquisador: " + idPesquisador, "MENU VACINA",
						JOptionPane.INFORMATION_MESSAGE);
				
				novaVacina.setPesquisadorResponsavel(idPesquisador);
			} else {
				resultado = "Não se esqueça que é necessário obter o\nNome e Cpf de uma pesquisador existente\npara cadastrar uma Vacina";
			}
		}
		JOptionPane.showMessageDialog(null, resultado);
		
		return pesquisador.getIdPaciente();
	

	}

	public JFrame getJanela() {
		return frame;
	}
	
	public VacinaVO getVacinaVO() {
		return vacinaVO;
	}
	
	public void setVacinaVO(VacinaVO vacinaVO) {
		this.vacinaVO = vacinaVO;
	}
}




