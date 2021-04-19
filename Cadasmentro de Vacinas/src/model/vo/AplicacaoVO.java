package model.vo;

import java.time.LocalDate;

public class AplicacaoVO {
	private int id;
	private int idPaciente;
	private VacinaVO vacina;
	private LocalDate dataAplicacao;
	private int nota;
	
	public AplicacaoVO() {
		super();
	}
	
	public AplicacaoVO(int id, int setIdPaciente, VacinaVO vacina, LocalDate dataAplicacao, int nota) {
		super();
		this.id = id;
		this.idPaciente = setIdPaciente;
		this.vacina = vacina;
		this.dataAplicacao = dataAplicacao;
		this.nota = nota;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int id) {
		this.idPaciente = id;
	}
	public VacinaVO getVacina() {
		return vacina;
	}
	public void setVacina(VacinaVO vacina) {
		this.vacina = vacina;
	}
	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
		
}