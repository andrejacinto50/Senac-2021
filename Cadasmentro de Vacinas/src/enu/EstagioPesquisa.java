package enu;

public enum EstagioPesquisa {
	
	INICIAL
	, TESTES
	, APLICACAO_MASSIVA;
	
	public static EstagioPesquisa getEstagioPesquisa(String estagioPesquisa){
        for(EstagioPesquisa estagio:EstagioPesquisa.values()){
            if(estagio.toString().equals(estagioPesquisa.toUpperCase())){
                return estagio;
            }
        }
        return null;
    }

}
