package io.github.awesome90.crescent.detection.checks.movement.antigravity;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.behaviour.Behaviour;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class AntiGravityA extends CheckVersion {

	private static final double ALLOWED_MAX_GRAVITY_DIFFERENCE = Crescent.getInstance().getConfig()
			.getDouble("antigravity.a.allowedMaxGravityDifference");

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

					if (difference > ALLOWED_MAX_GRAVITY_DIFFERENCE && behaviour
							.countBlocksUntilBlockBelow() > 2 /*
																 * The player
																 * being too
																 * close to the
																 * ground causes
																 * false
																 * positives.
																 */) {
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
