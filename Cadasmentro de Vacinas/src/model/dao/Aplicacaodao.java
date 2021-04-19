package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.vo.AplicacaoVO;
import model.vo.VacinaVO;
import repositorio.Banco;

public class Aplicacaodao {

	public AplicacaoVO insert(AplicacaoVO aplicacaoVO) {

		String sql = "INSERT INTO aplicacao_vacina(id_vacina, id_pessoa, reacao, data_aplicacao) values(?, ?, ?, ?);";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);) {

			stmt.setInt(1, aplicacaoVO.getVacina().getIdVacina());
			stmt.setInt(2, aplicacaoVO.getIdPaciente());
			stmt.setDate(4, java.sql.Date.valueOf(aplicacaoVO.getDataAplicacao()));

			stmt.executeUpdate();

			ResultSet returnId = stmt.getGeneratedKeys();

			if (returnId.next()) {
				aplicacaoVO.setId(returnId.getInt(0));

			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir cadastro!\n" + e.getMessage());
		}
		return aplicacaoVO;
	}

	public boolean update(AplicacaoVO aplicacaoVO) {

		boolean updated = false;
		String sql = "UPDATE aplicacao_vacina SET id_vacina = ?, id_pessoa = ?, reacao = ?, data_aplicacao = ?"
				+ " WHERE id_aplicacao_vacina = ?;";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			stmt.setInt(1, aplicacaoVO.getVacina().getIdVacina());
			stmt.setInt(2, aplicacaoVO.getIdPaciente());
			stmt.setInt(3, aplicacaoVO.getNota());
			stmt.setDate(4, java.sql.Date.valueOf(aplicacaoVO.getDataAplicacao()));
			stmt.executeUpdate();

			updated = true;

		} catch (SQLException e) {
			System.out.println("Erro ao tentar alterar!\n" + e.getMessage());
			updated = false;
		}
		return updated;

	}

	public boolean delete(Integer idAplicacaoVacina) {

		boolean deleted = false;
		String sql = "DELETE FROM aplicacao_vacina WHERE id_aplicacao_vacina = ?;";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			stmt.setInt(1, idAplicacaoVacina);
			stmt.executeUpdate();

			deleted = true;
		} catch (SQLException e) {
			System.out.println("Erro ao tentar excluir!\n" + e.getMessage());
			deleted = false;
		}
		return deleted;
	}

	public AplicacaoVO findById(Integer idaplicacaoVO) {

		AplicacaoVO aplicacao = new AplicacaoVO();

		String sql = "SELECT * FROM aplicacao_vacina WHERE id_aplicacao_vacina = ?;";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {

			stmt.setInt(1, idaplicacaoVO);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				aplicacao = this.completeResultset(rs);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao realizar consulta por ID!\n" + e.getMessage());
		}
		return aplicacao;

	}

	public List<AplicacaoVO> findAll() {

		String sql = "SELECT * FROM aplicacao_vacina;";
		List<AplicacaoVO> aplicacoes = new ArrayList<>();
		AplicacaoVO aplicacaoVacina = new AplicacaoVO();

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aplicacaoVacina = this.completeResultset(rs);
				aplicacoes.add(aplicacaoVacina);

			}

		} catch (SQLException e) {
			System.out.println("Erro ao realizar consulta geral!\n" + e.getMessage());
		}
		return aplicacoes;

	}

	public List<AplicacaoVO> findByPessoa(Integer idPessoa) {

		List<AplicacaoVO> aplicacoes = new ArrayList<>();
		AplicacaoVO aplicacaoVacina = new AplicacaoVO();
		String sql = "SELECT * FROM aplicacao_vacina WHERE id_pessoa = ?;";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {

			stmt.setInt(1, idPessoa);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aplicacaoVacina = this.completeResultset(rs);
				aplicacoes.add(aplicacaoVacina);

			}

		} catch (Exception e) {
			System.out.println("Erro ao realizar consulta por pessoa!\n" + e.getMessage());
		}

		return aplicacoes;

	}

	private AplicacaoVO completeResultset(ResultSet rs) throws SQLException  {
		AplicacaoVO aplicacao = new AplicacaoVO();
		aplicacao.setId(rs.getInt("id_aplicacao_vacina"));
		aplicacao.setIdPaciente(rs.getInt("id_pessoa"));
		aplicacao.setNota(rs.getInt("reacao")); //TODO conferir o nome da coluna
		aplicacao.setDataAplicacao(LocalDate.parse(rs.getString("data_aplicacao")));

		VacinaDAO vacinaDAO = new VacinaDAO();
		VacinaVO vacinaBuscada = vacinaDAO.findById(rs.getInt("id_vacina"));
		aplicacao.setVacina(vacinaBuscada);
		return aplicacao;

	}

	public List<Aplicacaodao> consultarPorIdPaciente(int id) {

		System.out.println("Lista de aplicaçoes que é consultada");

		return null;
	}

}












