package br.com.guardiaosistemas.tca.execucao.delegate;

import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;

public interface ExecuteDelegate {
	void executar(PatientEntity patient);
}
