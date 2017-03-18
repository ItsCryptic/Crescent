package io.github.awesome90.crescent.detection.checks.interact.autoclicker;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class AutoclickerDamage extends CheckVersion {

	private AutoclickerStore store;

	public AutoclickerDamage(Check check) {
		super(check, "Damage", "Calculates CPS from data collected from the player hitting other entities.");
		this.store = new AutoclickerStore();
	}

	@Override
	public void call(Event event) {
		if (event instanceof PlayerAnimationEvent) {
			final PlayerAnimationEvent pae = (PlayerAnimationEvent) event;

			if (pae.getAnimationType() == PlayerAnimationType.ARM_SWING) {
				store.call();
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

	public AutoclickerStore getStore() {
		return store;
	}

}
