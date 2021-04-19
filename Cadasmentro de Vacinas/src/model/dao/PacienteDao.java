package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.PacienteVo;
import repositorio.Banco;

public class PacienteDao {
	
	public PacienteVo cadastrar(PacienteVo novaPessoa) {
		String sql = " INSERT INTO pessoa(nome, sexo, cpf, data nascimento) VALUES (?, ?, ?, ?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setString(1, novaPessoa.getNome());
			stmt.setString(2, novaPessoa.getSexo() + "");
			stmt.setString(3, novaPessoa.getCpf());
			stmt.setInt(4, novaPessoa.getId());
			stmt.setDate(3, java.sql.Date.valueOf( novaPessoa.getDataNascimento()));

			

			stmt.executeUpdate();

			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novaPessoa.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar pessoa: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (cadastrar pessoa): \n" + e.getMessage());

			}
		}

		return novaPessoa;
	}
		
		
		public boolean atualizar(PacienteVo paciente) {
		boolean atualizou = false;

		String sql = " UPDATE paciente SET NOME = ?, id = ?,Sexo = ?, CPF = ?, datanascimento = ? " + " E ID = ?";

		Connection conexao = Banco.getConnection();
	PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
	
	try {
		stmt.setString(1, paciente.getNome());
		stmt.setInt(2,paciente.getId());
		stmt.setString(2, paciente.getSexo() + "");
		stmt.setString(3, paciente.getCpf());
		stmt.setDate(3, java.sql.Date.valueOf( paciente.getDataNascimento()));
		
		int quantidadeLinhasAfetadas = stmt.executeUpdate();

		atualizou = quantidadeLinhasAfetadas > 0;

	} catch (SQLException e) {
		System.out.println("Erro ao atualizar paciente: \n" + e.getMessage());
	} finally {
		try {
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexão (atualizar paciente): \n" + e.getMessage());

		}

		
	}
	return atualizou;


	}
	
		public boolean excluir(Integer id) {
			boolean excluiu = false;

			String sql = " DELETE FROM PACIENTE " + " WHERE ID = ? ";

			Connection conexao = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

			try {
				stmt.setInt(1, id);
				excluiu = stmt.executeUpdate() > 0;
			} catch (SQLException e) {
				System.out.println("Erro ao excluir paciente: \n" + e.getMessage());
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar conexão (excluir paciente): \n" + e.getMessage());

				}
			}

			return excluiu;
		}

		public PacienteVo consultarPorId(Integer id) {
			PacienteVo pacientevoConsultada = null;

			String sql = " SELECT * FROM PACIENTE WHERE ID = ? ";

			Connection conexao = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

			try {
				stmt.setInt(1, id);

				ResultSet resultadoConsulta = stmt.executeQuery();

				if (resultadoConsulta.next()) {
					pacientevoConsultada = this.converterDoResultSet1(resultadoConsulta);
				}
			} catch (SQLException e) {
				System.out.println("Erro ao consultar paciente por id: \n" + e.getMessage());
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar conexão (consultar paciente por id): \n" + e.getMessage());

				}
			}

			return pacientevoConsultada;
		}

		public List<PacienteVo> consultarTodos() {
			List<PacienteVo> paciente = new ArrayList<PacienteVo>();

			String sql = " SELECT * FROM PACIENTE ";

			Connection conexao = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

			try {
				ResultSet resultadoConsulta = stmt.executeQuery();

				while (resultadoConsulta.next()) {
					PacienteVo pacienteVo = this.converterDoResultSet1(resultadoConsulta);
					pacienteVo.add(pacienteVo);
				}
			} catch (SQLException e) {
				System.out.println("Erro ao consultar paciente: \n" + e.getMessage());
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar conexão (consultar pacientes): \n" + e.getMessage());

				}
			}

			return paciente;
		}
		
		
		private PacienteVo converterDoResultSet1(ResultSet resultadoConsulta) throws SQLException {
			
				PacienteVo pacientevo = new PacienteVo();
				pacientevo.setId(resultadoConsulta.getInt("id"));
				pacientevo.setNome(resultadoConsulta.getString("nome"));
				pacientevo.setSexo(resultadoConsulta.getString("sexo").charAt(0));
				pacientevo.setCpf(resultadoConsulta.getString("cpf"));
				pacientevo.setDate(3, java.sql.Date.valueOf( pacientevo.getDataNascimento()));

				Aplicacaodao aplicacaoDAO = new Aplicacaodao();
				List<Aplicacaodao> aplicacoes = aplicacaoDAO.consultarPorIdPaciente(pacientevo.getId());
				pacientevo.setVacinacoes(aplicacoes);

				return pacientevo;
			}


		public PacienteVo consultarPacientePorNomeAndCpf(PacienteVo pesquisadorInformadoPeloUsuario) {

			System.out.println("consulta paciente");
			return null;
		}
		
}


