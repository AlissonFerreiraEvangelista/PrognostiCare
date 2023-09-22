package br.com.prognosticare.domain.entity.vacina;

public record DtoDetalheVacina(
    Long id,

    TipoVacina TipoVacina,               

    String vacina,

    String protecaoContra,

    String composicao,

    String esquemaBasico,

    String reforco,

    String idadeRecomendada,

    String intervaloRecomendado,

    String intervaloMinimo
) {

    public DtoDetalheVacina(VacinaEntity vacinaEntity){
        this(
            vacinaEntity.getId(),
            vacinaEntity.getTipoVacina(),
            vacinaEntity.getVacina(), 
            vacinaEntity.getProtecaoContra(), 
            vacinaEntity.getComposicao(),
            vacinaEntity.getEsquemaBasico(),
            vacinaEntity.getReforco(),
            vacinaEntity.getIdadeRecomendada(), 
            vacinaEntity.getIntervaloRecomendado(),
            vacinaEntity.getIntervaloMinimo()
             );

    }
    
}
