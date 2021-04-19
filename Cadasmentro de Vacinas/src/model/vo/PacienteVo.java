package model.vo;

import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles.Lookup;
import java.nio.charset.Charset;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import model.dao.Aplicacaodao;

public class PacienteVo {
	
	private int id;
	private  String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private String cpf;
	
	public PacienteVo(int id, String nome, LocalDate dataNascimento, char sexo, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
	}

	public PacienteVo() {
		super();
	}

	public  int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public  String getNome() {
		return nome;
	}

	public void setnome(String string) {
		this.nome = nome;
	}

	
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate date) {
		this.dataNascimento = date;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char c) {
		this.sexo = c;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	 public void add(PacienteVo pacienteVo) {

		 System.out.println("resultado consulta");
		 
	}

	public void setDate(int i, Date valueOf) {

		System.out.println("converte data nascimento do paciente");
	}

	public void setVacinacoes(List<Aplicacaodao> aplicacoes) {

		System.out.println("Lista vacinaçao que foi aplicada no paciente");
	}

	public void setIdpaciente(int parseInt) {

	System.out.println("idpaciente para ser excluido");
	}

	public void imprimir() {

		System.out.println("consulta lista de paciente");
	}

	

	public void setNome(Object println) {
		// TODO Auto-generated method stub
		
	}

	public int getIdPaciente() {

		System.out.println("cadastrar paciente pela id");
		
		return 0;
	}

	
	}

	 

		
	

	
	
	
