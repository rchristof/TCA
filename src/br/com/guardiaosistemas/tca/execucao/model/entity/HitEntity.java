package br.com.guardiaosistemas.tca.execucao.model.entity;

import br.com.guardiaosistemas.tca.execucao.consts.C;

public class HitEntity {

	private String type; // B=ballon, S=star
	private String frequency; // L low, H high
	private int time; // 1, 2 or 4 seconds
	private int speed = 0; // Miliseconds
	private boolean hit; // 0
	
	public HitEntity() {
		super();
	}

	public HitEntity(String type, String frequency, int time) {
		this();
		this.type = type;
		this.frequency = frequency;
		this.time = time;
	}

	public HitEntity(String type, int speed, boolean hit) {
		this();
		this.type = type;
		this.speed = speed;
		this.hit = hit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
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

	@Override
	public String toString() {
		return "HitEntity [type=" + type + ", frequency=" + frequency + ", time=" + time + ", speed=" + speed + ", hit="
				+ hit + "]";
	}
	
	public void tookShot(int speed) {
		this.hit = true;
		this.speed = speed;
	}
	
	public boolean isOk() {
		return (type.equals(C.T_STAR) && hit) || (type.equals(C.T_BALLON) && !hit);
	}
	
	public int getCode() {
		if (C.T_STAR.equals(type)) {
			return hit ? 1 : 0;
		} else {
			return hit ? 3 : 2;
		}
	}
		
}
