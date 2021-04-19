package bo;

import model.dao.PacienteDao;
import model.vo.PacienteVo;

public class PacienteBO {

	public PacienteVo consultarPessoaPorNomeAndCpfBO(PacienteVo pesquisadorInformadoPeloUsuario) {
		PacienteDao pacienteDao = new PacienteDao();
		return pacienteDao.consultarPacientePorNomeAndCpf(pesquisadorInformadoPeloUsuario);
	}

	public int cadastrarPacienteBO(PacienteVo pacienteVO) {
		int retorno = 0;
		PacienteDao pacienteDao = new PacienteDao();

		pacienteVO = pacienteDao.cadastrar(pacienteVO);
		if (pacienteVO.getIdPaciente() > 0) {
			retorno =  pacienteVO.getIdPaciente();
		} else {
			retorno = 0;
		}

		return  retorno;
	}
}
