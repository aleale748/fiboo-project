package es.uca.fiboo.naveminigame.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class EscudoActor extends Actor implements HealthActor {
	
	private transient float health, timer;
	
	public EscudoActor() {
		super();
		health = 1;
		timer = 0;
	}
	
	public float getHealth() {
		return health;
	}
	
	public void setHealth(final float health) {
		this.health = health;
	}
	
	public void sumHealth(final float sum) {
		health +=sum;
		if (health > 1) {
			health = 1;
		}
		else {
			if (health < 0) {
				health = 0;
			}
		}
	}
	
	@Override
	public void act(final float delta) {
		timer += delta;
		if (timer > 2 && health < 0.99f) {
			//health += 0.01f;
			timer = 0;
		}
	}

}
