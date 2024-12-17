package br.com.guardiaosistemas.tca.execucao.model.entity;

import br.com.guardiaosistemas.tca.execucao.consts.C;

public class HitEntity {

    private String type; // B = Balloon (não-alvo), S = Star (alvo)
    private int time;    // SOA em segundos
    private int speed = 0; // Milissegundos (tempo de resposta)
    private boolean hit = false; // true se o jogador acertar o estimulo

    public HitEntity(String type, int time) {
        this.type = type;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public int getTime() {
        return time;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
    
    public boolean isOk() {
        return (type.equals(C.T_STAR) && hit) || (type.equals(C.T_BALLON) && !hit);
    }
    
    public int getCode() {
        if (C.T_STAR.equals(type)) { // Alvo (S)
            return hit ? 1 : 0; // 1 = Alvo acertado, 0 = Alvo não acertado
        } else { // Não-alvo (B)
            return hit ? 3 : 2; // 3 = Não-alvo errado, 2 = Não-alvo correto
        }
    }



    @Override
    public String toString() {
        return "HitEntity [type=" + type + ", time=" + time + ", speed=" + speed + ", hit=" + hit + "]";
    }
}

