package com.med.voll.api.domain.consulta.validacoes.cancelamento;

import com.med.voll.api.domain.ValidacaoException;
import com.med.voll.api.domain.consulta.ConsultaRepository;
import com.med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidadorConsultaJaCancelada implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var consultaCancelada = Objects.nonNull(consulta.getMotivoCancelamento());

        if (consultaCancelada) {
            throw new ValidacaoException("A Consulta já esta cancelada.");
        }
    }
}
