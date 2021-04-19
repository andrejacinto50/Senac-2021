package enu;

public enum FaseVacina {
	
	SOMENTE_PESQUISADOR
	, VOLUNTARIO
	, PUBLICO_GERAL;
	
	public static FaseVacina getFaseVacina(String faseVacina){
        for(FaseVacina fase:FaseVacina.values()){
            if(fase.toString().equals(faseVacina.toUpperCase())){
                return fase;
            }
        }
        return null;
    }

}
