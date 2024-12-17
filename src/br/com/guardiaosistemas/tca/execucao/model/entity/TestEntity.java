package br.com.guardiaosistemas.tca.execucao.model.entity;

import java.util.List;

public class TestEntity {
    private int totalItems;
    private int frequency; // 80 = alta, 20 = baixa
    private int soa; // Tempo entre estímulos (1, 2 ou 4 segundos)
    private List<HitEntity> hitEntities; // Lista de estímulos gerados

    public TestEntity(int totalItems, int frequency, int soa, List<HitEntity> hitEntities) {
        this.totalItems = totalItems;
        this.frequency = frequency;
        this.setSoa(soa);
        this.hitEntities = hitEntities;
    }


    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public List<HitEntity> getHitEntities() {
        return hitEntities;
    }

    public void setHitEntities(List<HitEntity> hitEntities) {
        this.hitEntities = hitEntities;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                ", totalItems=" + totalItems +
                ", frequency=" + frequency +
                ", hitEntities=" + hitEntities +
                ", soa=" + soa +
                '}';
    }


	public int getSoa() {
		return soa;
	}


	public void setSoa(int soa) {
		this.soa = soa;
	}
}