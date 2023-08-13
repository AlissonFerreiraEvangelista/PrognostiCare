package br.com.prognosticare.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.prognosticare.common.PessoaConstants.PESSOA;
import static br.com.prognosticare.common.PessoaConstants.INVALID_PESSOA;
import static br.com.prognosticare.common.PessoaConstants.ID;

import br.com.prognosticare.common.PessoaConstants;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.repository.PessoaRepository;
import br.com.prognosticare.domain.service.PessoaService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pService;

    @Mock
    private PessoaRepository pRepository;

    @Test
    @DisplayName("Cria Pessoa Com dados Validos")
    public void createPessoa_WithValidData_ReturnsPessoa(){
        when(pRepository.save(PESSOA)).thenReturn(PESSOA);
        PessoaEntity sut = pRepository.save(PESSOA);
        assertThat(sut).isEqualTo(PESSOA);
    }

    @Test
    @DisplayName("Cria Pessoa Com dados Invalidos")
    public void createdPessoa_WithInvalidData_ThrowsExceptions() {
        lenient().when(pRepository.save(any(PessoaEntity.class))).thenReturn(PessoaConstants.PESSOA);
        lenient().when(pRepository.save(INVALID_PESSOA)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> pService.save(INVALID_PESSOA)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Pesquisa Pessoa por ID")
    public void getPessoa_PorIDExistente_RetornaPessoa() {
        when(pRepository.findById(ID)).thenReturn(Optional.of(PESSOA));
        Optional<PessoaEntity> sut = pService.get(ID);
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PESSOA);
    }
    @Test
    @DisplayName("Pesquisa Pessoa com ID sem Resultado")
    public void getPessoa_PorIDExistente_RetornaEmpty() {
        when(pRepository.findById(ID)).thenReturn(Optional.empty());
        Optional<PessoaEntity> sut = pService.get(ID);
        assertThat(sut).isEmpty();
    }

}
