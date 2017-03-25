package io.github.awesome90.crescent.detection.checks.movement.antigravity;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.awesome90.crescent.behaviour.Behaviour;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class AntiGravityA extends CheckVersion {

	public AntiGravityA(Check check) {
		super(check, "A", "Checks if the player is following gravity properly or not.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof PlayerMoveEvent) {
			final PlayerMoveEvent pme = (PlayerMoveEvent) event;

			final Behaviour behaviour = profile.getBehaviour();

			if (!behaviour.isOnGround()) {
				final double expected = behaviour.getMotion().calculateGravityEffect(),
						actual = pme.getTo().getY() - pme.getFrom().getY(), difference = expected - actual;

				if (difference > 0.1) {
					Bukkit.broadcastMessage("actual: " + actual + ", expected: " + expected + ", fall distance: "
							+ behaviour.getMotion().getFallDistance());
					callback(true);
				}
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
