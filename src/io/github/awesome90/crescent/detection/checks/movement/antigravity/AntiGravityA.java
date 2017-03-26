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

			if (!behaviour.isOnGround() && !behaviour.isInLiquid() && !behaviour.isInWeb()) {
				if (behaviour.getMotion().isDescending()) {
					Bukkit.broadcastMessage("y vel: " + pme.getPlayer().getVelocity().getY());
					final double difference = Math.abs((behaviour.getMotion()
							.calculateGravityEffect() /*
														 * Expected y difference
														 */)
							- (pme.getTo().getY() - pme.getFrom()
									.getY()) /* Actual y difference */);

					if (difference > 0.075) {
						callback(true);
					}
				}
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
