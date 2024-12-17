package br.com.guardiaosistemas.tca.execucao.delegate;

import java.util.List;

import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;

public interface ReturnListDelegate {
	void exec(List<HitEntity> list);
}
