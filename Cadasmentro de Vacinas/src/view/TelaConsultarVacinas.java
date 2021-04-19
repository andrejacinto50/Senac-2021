package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controler.ControladoraVacina;
import model.vo.VacinaVO;

public class TelaConsultarVacinas {

	private JFrame frame;
	private JPanel contentPane;
	private JButton btnExcluirVacina;
	private JButton btnEditarVacina;
	private JTable tblListaVacina;
	private List<VacinaVO> vacinas;
	private Object[] nomesColunas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultarVacinas window = new TelaConsultarVacinas();
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
	public TelaConsultarVacinas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 999, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConsultarTodasVacinas = new JButton("Consultar todas as vacinas");
		btnConsultarTodasVacinas.addActionListener(new ActionListener() {
			public void actionPerformed1(ActionEvent arg0) {
				atualizarTabelaVacina();
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnConsultarTodasVacinas.setBounds(328, 24, 243, 23);
		contentPane.add(btnConsultarTodasVacinas);
		
		btnExcluirVacina = new JButton("Excluir vacina");
		btnExcluirVacina.setEnabled(false);
		btnExcluirVacina.setBounds(113, 287, 205, 23);
		btnExcluirVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indiceSelecionadoNaTablela = ControladoraVacina.getSelectedRow();
				VacinaVO vacinaSelecionada = VacinaVO.get(indiceSelecionadoNaTablela - 1);
				
				String perguntaExclusao = "Deseja excluir a vacina: " + vacinaSelecionada.toString() + "?";
				
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, perguntaExclusao, "Está certo disso?", JOptionPane.YES_NO_OPTION);
				
				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					String mensagem = ControladoraVacina.excluirVacinaController(vacinaSelecionada);
					JOptionPane.showMessageDialog(null, mensagem);
					atualizarTabelaVacina();
				}
				
			}
		});
		contentPane.add(btnExcluirVacina);
		
		btnEditarVacina = new JButton("Editar vacina");
		btnEditarVacina.addActionListener(new ActionListener() {
			private ControladoraVacina tblListaVacina;

			public void actionPerformed(ActionEvent e) {
				VacinaVO vacinaSelecionada = VacinaVO.get(tblListaVacina.getSelectedRow() - 1);
				TelaCadastroVacina cadastroVacina = new TelaCadastroVacina(vacinaSelecionada);
				cadastroVacina.getJanela().setVisible(true);
			}
		});
		btnEditarVacina.setEnabled(false);
		btnEditarVacina.setBounds(576, 287, 205, 23);
		contentPane.add(btnEditarVacina);
		
		tblListaVacina = new JTable();
		this.limparTabelaVacina();
		
		tblListaVacina.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		) {
			@Override
			public boolean isCellEditable(int rowIndex, int collIndex) {
				return false;
			}
		});
		tblListaVacina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int indiceSelecionado = tblListaVacina.getSelectedRow();
				
				if (indiceSelecionado > 0) {
					btnEditarVacina.setEnabled(true);
					btnExcluirVacina.setEnabled(true);
				} else {
					btnEditarVacina.setEnabled(false);
					btnExcluirVacina.setEnabled(false);
				}
			}
		});
		tblListaVacina.setBounds(-40, 71, 963, 218);
		contentPane.add(tblListaVacina);
	}

	private void setContentPane(JPanel contentPane2) {
		// TODO Auto-generated method stub
		
	}

	private void setBounds(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

	protected void atualizarTabelaVacina() {
		vacinas = ControladoraVacina.consultarVacinaController();
		this.limparTabelaVacina();
		
		DefaultTableModel model = (DefaultTableModel) this.tblListaVacina.getModel();
		
		for(VacinaVO vac: this.vacinas) {
			
			if(vac.isVacinaAtiva()) {
				Object[] novaLinhaTabela = new Object[7];
				
				novaLinhaTabela[0] = vac.getNomeVacina();
				novaLinhaTabela[1] = vac.getPesquisadorResponsavel();
				novaLinhaTabela[2] = vac.getPaisOrigem();
				novaLinhaTabela[3] = vac.getEstagioPesquisa();
				novaLinhaTabela[4] = vac.getFase();
				novaLinhaTabela[5] = vac.getQuantidadeDoses();
				novaLinhaTabela[6] = vac.getDataInicioPesquisa();
				
				model.addRow(novaLinhaTabela);
			}
		}
	}
	
	private void limparTabelaVacina() {
		tblListaVacina.setModel(new DefaultTableModel(new Object[][] {nomesColunas, }, nomesColunas));
		btnEditarVacina.setEnabled(false);
		btnExcluirVacina.setEnabled(false);		
	}
	}


