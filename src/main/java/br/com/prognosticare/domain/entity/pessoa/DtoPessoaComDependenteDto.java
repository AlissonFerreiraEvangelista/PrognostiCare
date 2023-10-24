package br.com.prognosticare.domain.entity.pessoa;

import java.util.List;

public class DtoPessoaComDependenteDto {
    private DtoDetalhePessoa pessoa;
    private List<DtoDetalheDependente> dependentes;

    public DtoPessoaComDependenteDto() {
    }

    public DtoPessoaComDependenteDto(DtoDetalhePessoa pessoa, List<DtoDetalheDependente> dependentes) {
        this.pessoa = pessoa;
        this.dependentes = dependentes;
    }

    public DtoDetalhePessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(DtoDetalhePessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<DtoDetalheDependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<DtoDetalheDependente> dependentes) {
        this.dependentes = dependentes;
    }
}
