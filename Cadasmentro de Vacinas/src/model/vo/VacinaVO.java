package model.vo;

import java.time.LocalDate;

import enu.EstagioPesquisa;
import enu.FaseVacina;

public class VacinaVO {

	private Integer idVacina;
	private String nomeVacina;
	private String responsavelPesquisa;
	private String paisOrigem;
	private int quantidadeDoses;
	private String estagioPesquisa;
	private LocalDate dataInicioPesquisa;
	private String faseVacina;

	public VacinaVO(String faseVacina) {
		super();
		this.faseVacina = faseVacina;
	}

	public VacinaVO() {
		super();
		this.nomeVacina = nomeVacina;
		this.responsavelPesquisa = responsavelPesquisa;
		this.paisOrigem = paisOrigem;
		this.quantidadeDoses = quantidadeDoses;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.faseVacina = faseVacina;
	}

	@Override
	public String toString() {
		return "VacinaVO [\nidVacina=" + idVacina + "\n, nomeVacina=" + nomeVacina + "\n, responsavelPesquisa="
				+ responsavelPesquisa + "\n, paisOrigem=" + paisOrigem + "\n, quantidadeDoses=" + quantidadeDoses
				+ "\n, estagioPesquisa=" + estagioPesquisa + "\n, dataInicioPesquisa=" + dataInicioPesquisa
				+ "\n, faseVacina=" + faseVacina + "\n]";
	}

	public Integer getIdVacina() {
		return idVacina;
	}



	public void setIdVacina(Integer idVacina) {
		this.idVacina = idVacina;
	}



	public String getNomeVacina() {
		return nomeVacina;
	}



	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}



	public String getResponsavelPesquisa() {
		return responsavelPesquisa;
	}



	public void setResponsavelPesquisa(String responsavelPesquisa) {
		this.responsavelPesquisa = responsavelPesquisa;
	}



	public String getPaisOrigem() {
		return paisOrigem;
	}



	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}



	public int getQuantidadeDoses() {
		return quantidadeDoses;
	}



	public void setQuantidadeDoses(int quantidadeDoses) {
		this.quantidadeDoses = quantidadeDoses;
	}



	public String getEstagioPesquisa() {
		return estagioPesquisa;
	}



	public void setEstagioPesquisa(String inicial) {
		this.estagioPesquisa = inicial;
	}



	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}



	public void setDataInicioPesquisa(LocalDate dataInformadoPeloUsuario) {
		this.dataInicioPesquisa = dataInformadoPeloUsuario;
	}



	public String getFaseVacina() {
		return faseVacina;
	}




	

	public void setId(String nextLine) {

	this.idVacina = idVacina;
	}

	public static void imprimir() {

		System.out.println("lista de vacina");
	}

	public int getPesquisadorResponsavel() {

		System.out.println("verifica pesquisador responsavel");
		
		return 0;
	}

	public void setEstagioPesquisa(EstagioPesquisa inicial) {

		System.out.println("verifica estagio pesquisa");
	}

	public void setFaseVacina1(String somentePesquisador) {
		this.faseVacina = somentePesquisador;
	}



	public void setFaseVacina(FaseVacina somentePesquisador) {


		System.out.println("pesquisador da vacina");
	}

	public void setVacinaAtiva(boolean flag) {
		System.out.println("ativa vacina");

		
	}

	public void setPesquisadorResponsavel(int idPaciente) {
		System.out.println("pesquisador responsavel");

		
	}

	public boolean isVacinaAtiva() {

		System.out.println("vacina ativada");

		
		
		return false;
	}

	public Object getFase() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDataInicioPesquisa(Integer[] dateFields) {

		System.out.println("data do inicio da pesquisa");
	}

	public static VacinaVO get(int i) {

		System.out.println("seleciona vacina para consulta");

		
		
		return null;
	}


}
