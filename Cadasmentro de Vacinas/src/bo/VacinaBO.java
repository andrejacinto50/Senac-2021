package bo;

import java.util.List;

import model.dao.VacinaDAO;
import model.vo.VacinaVO;

public class VacinaBO {
	
	public String cadastrarVacinaBO(VacinaVO vacinaVO) {
		String retorno = "";
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		VacinaVO verificar = vacinaDAO.consultarVacinaPorNomeAndPais(vacinaVO);
		
		if (verificar.getPesquisadorResponsavel() > 0) {
			if (verificar.getIdVacina() != null && verificar.getIdVacina() != 0) {
				retorno =  "Nome da Vacina já existente no pais, favor escolher outro nome.";
			} else {
				if (vacinaDAO.cadastrarVacina(vacinaVO) != null) {
					retorno = "Vacina cadastrada com sucesso.";
				} else {
					retorno = "Não foi possivel cadastrar vacina.";
				}
			}
		} else {
			retorno = "É necessario cadastrar um pesquisador responsavel primeiro";
		}
		
		return retorno;
	}
	
	public String atualizarVacinaBO(VacinaVO atualizarVacina) {
		String retorno = "Erro ao verificar validação de cadastro";
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		VacinaVO verificar = vacinaDAO.consultarVacinaPorNomeAndPais(atualizarVacina);
		
		if (verificar.getPesquisadorResponsavel() > 0) {
			if (verificar.getIdVacina() != null && verificar.getIdVacina() != 0 && verificar.getIdVacina() != atualizarVacina.getIdVacina()) {
				retorno =  "Nome da Vacina já existente no pais, favor escolher outro nome.";
			} else {
				if (vacinaDAO.atualizarVacinaDAO(atualizarVacina)) {
					retorno = "Vacina atualizada com sucesso.";
				} else {
					retorno = "Não foi possivel atualizar vacina.";
				}
			}
		} else {
			retorno = "É necessario cadastrar um pesquisador responsavel primeiro";
		}
		return retorno;
	}

	public String excluirVacinaBO(VacinaVO vacinaVO) {
		String retorno = "Erro ao verificar validação de exclusão";
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		VacinaVO verificar = vacinaDAO.consultarVacinaPorNomeAndPais(vacinaVO);
		
		if (verificar.getIdVacina() != null) {
			if (vacinaDAO.desativarVacina(vacinaVO.getIdVacina())) {
				retorno = "Vacina excluida com sucesso.";
			} else {
				retorno = "Não foi possivel excluir a vacina.";
			}
		} else {
			retorno = "Vacina ainda não foi cadastrada no banco.";
		}

		return retorno;
	}

	public List<VacinaVO> consultarTodasVacinas() {
		VacinaDAO vacinaDAO = new VacinaDAO();
		return vacinaDAO.consultarTodasVacinas();
	}


}
