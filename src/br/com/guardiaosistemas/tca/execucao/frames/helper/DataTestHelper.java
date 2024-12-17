package br.com.guardiaosistemas.tca.execucao.frames.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.guardiaosistemas.tca.execucao.consts.C;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;

public class DataTestHelper {
	
	public static List<HitEntity> generateStimuliList(int totalItems, int frequency, int soa) {
	    List<HitEntity> stimuliList = new ArrayList<>();

	    int numTargets = (int) (totalItems * (frequency / 100.0));
	    int numNonTargets = totalItems - numTargets;

	    // Gerar alvos
	    for (int i = 0; i < numTargets; i++) {
	        stimuliList.add(new HitEntity(C.T_STAR, soa));
	    }

	    // Gerar não-alvos
	    for (int i = 0; i < numNonTargets; i++) {
	        stimuliList.add(new HitEntity(C.T_BALLON, soa));
	    }

	    Collections.shuffle(stimuliList);
	    return stimuliList;
	}
	
	public static List<HitEntity> getExample() {
	    List<HitEntity> exampleList = new ArrayList<>();

	    exampleList.add(new HitEntity(C.T_STAR, 2));
	    exampleList.get(0).setHit(true);
	    exampleList.get(0).setSpeed(450);

	    exampleList.add(new HitEntity(C.T_BALLON, 2));
	    exampleList.get(1).setHit(false);

	    exampleList.add(new HitEntity(C.T_STAR, 2));
	    exampleList.get(2).setHit(false);

	    exampleList.add(new HitEntity(C.T_BALLON, 2));
	    exampleList.get(3).setHit(false);

	    return exampleList;
	}

	//funçao para teste da lista de alvos
	public static void main(String[] args) {
	    int totalAlvos = 48;  // teste com: 48 alvos, 80% e 2s
	    int frequency = 80;
	    int soa = 2;

	    List<HitEntity> stimuliList = generateStimuliList(totalAlvos, frequency, soa);

	    System.out.println("Parâmetros do Teste:");
	    System.out.println("Total de Alvos: " + totalAlvos);
	    System.out.println("Frequência: " + frequency + "%");
	    System.out.println("SOA (Tempo entre estímulos): " + soa + "s");
	    System.out.println("Total de Estímulos: " + stimuliList.size());

	    System.out.println("\nLista de Estímulos Gerada:");
	    int index = 1;
	    for (HitEntity hit : stimuliList) {
	        System.out.println(index++ + ". " + hit);
	    }
	}

}
