package br.com.guardiaosistemas.tca.execucao.delegate;

import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.TestEntity;

public interface ExecuteDelegate {
    void executar(PatientEntity patient, TestEntity testEntity);
}

