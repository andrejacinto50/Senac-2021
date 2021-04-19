package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.vo.AplicacaoVO;
import model.vo.VacinaVO;
import repositorio.Banco;

public class VacinaDAO {



	public VacinaVO insert(VacinaVO vacinaVO) {

		String sql = "INSERT INTO vacina(nome_vacina, responsavel_pesquisa, pais_origem, "
				+ "quantidade_doses, estagio_pesquisa, inicio_pesquisa, fase_vacina)" + " values(?, ?, ?, ?, ?, ?, ?);";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);) {

			stmt.setString(1, vacinaVO.getNomeVacina());
			stmt.setString(2, vacinaVO.getResponsavelPesquisa());
			stmt.setString(3, vacinaVO.getPaisOrigem());
			stmt.setInt(4, vacinaVO.getQuantidadeDoses());
			stmt.setString(5, vacinaVO.getEstagioPesquisa());
			stmt.setDate(6, java.sql.Date.valueOf(vacinaVO.getDataInicioPesquisa()));
			stmt.setString(7, vacinaVO.getFaseVacina());
			stmt.executeUpdate();

			ResultSet returnId = stmt.getGeneratedKeys();
			if (returnId.next()) {
				vacinaVO.setIdVacina(returnId.getInt(1));

			}

			System.out.println( "Inclusão efetuada!");

		} catch (SQLException e) {
			System.out.println("Erro ao inserir cadastro!\n" + e.getMessage());
		}
		return vacinaVO;
	}

	public boolean update(VacinaVO vacinaVO) {

		boolean updated = false;
		String sql = "UPDATE vacina SET nome_vacina = ?, responsavel_pesquisa = ?, pais_origem = ?, quantidade_doses = ?"
				+ ", estagio_pesquisa = ?, inicio_pesquisa = ?, fase_vacina = ? WHERE id_vacina = ?;";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			stmt.setString(1, vacinaVO.getNomeVacina());
			stmt.setString(2, vacinaVO.getResponsavelPesquisa());
			stmt.setString(3, vacinaVO.getPaisOrigem());
			stmt.setInt(4, vacinaVO.getQuantidadeDoses());
			stmt.setString(5, vacinaVO.getEstagioPesquisa());
			stmt.setDate(6, java.sql.Date.valueOf(vacinaVO.getDataInicioPesquisa()));
			stmt.setString(7, vacinaVO.getFaseVacina());
			stmt.setInt(8, vacinaVO.getIdVacina());
			stmt.executeUpdate();

			updated = true;
			System.out.println( "Alteração efetuada!");

		} catch (SQLException e) {
			System.out.println( "Erro ao tentar alterar!\n" + e.getMessage());
			updated = false;
		}
		return updated;

	}

	public boolean delete(Integer idVacina) {

		boolean deleted = false;
		String sql = "DELETE FROM vacina WHERE id_vacina = ?;";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			stmt.setInt(1, idVacina);
			stmt.executeUpdate();

			deleted = true;
			System.out.println( "Exclusão efetuada!");

		} catch (SQLException e) {
			System.out.println( "Erro ao tentar excluir!\n" + e.getMessage());
			deleted = false;
		}
		return deleted;
	}

	public VacinaVO findById(Integer idVacina) {

		String sql = "SELECT * FROM vacina WHERE id_vacina = ?";
		VacinaVO vacina = new VacinaVO();

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			stmt.setInt(1, idVacina);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				vacina = this.converterDoResultSet1(rs);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao realizar consulta por ID!\n" + e.getMessage());
		}
		return vacina;
	}

	private VacinaVO converterDoResultSet1(ResultSet resultadoConsulta) throws SQLException {
		VacinaVO vacina = new VacinaVO();
		vacina.setIdVacina(resultadoConsulta.getInt("id Vacina"));
		vacina.setNomeVacina(resultadoConsulta.getString("nome Vacina"));
		vacina.setResponsavelPesquisa(resultadoConsulta.getString("responsavel pesquisa"));
		vacina.setPaisOrigem(resultadoConsulta.getString("pais_origem"));
		vacina.setQuantidadeDoses(resultadoConsulta.getInt("responsavel pesquisa"));
		vacina.setEstagioPesquisa(resultadoConsulta.getString("estagio_pesquisa"));


		return vacina;
	}

	private VacinaVO converterDoResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<AplicacaoVO> findByPaciente(String idpaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	public VacinaDAO consultarPorId(int int1) {

		return consultarPorId(0);

	}

	public VacinaVO consultarVacinaPorNomeAndPais(VacinaVO vacinaVO) {

		System.out.println("consulta vacina por nome");
		
		return null;
	}

	public Object cadastrarVacina(VacinaVO vacinaVO) {

		System.out.println("cadastrar vacina");
		
		return null;
	}

	public boolean atualizarVacinaDAO(VacinaVO atualizarVacina) {
		
		System.out.println("atualiza vacina");
		return false;
	}

	public boolean desativarVacina(Integer idVacina) {
		
		System.out.println("desativa vacina");

		
		return false;
	}

	public List<VacinaVO> consultarTodasVacinas() {
		
		System.out.println("consulta todas vacina");

		
		return null;
	}

}