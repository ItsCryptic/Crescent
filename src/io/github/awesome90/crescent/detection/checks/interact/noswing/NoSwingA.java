package io.github.awesome90.crescent.detection.checks.interact.noswing;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class NoSwingA extends CheckVersion {

	public NoSwingA(Check check) {
		super(check, "A", "Check if the player swings");
	}

	@Override
	public void call(Event event) {
		if (event instanceof PlayerAnimationEvent) {
			final PlayerAnimationEvent pae = (PlayerAnimationEvent) event;

			if (pae.getAnimationType() == PlayerAnimationType.ARM_SWING) {

			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
