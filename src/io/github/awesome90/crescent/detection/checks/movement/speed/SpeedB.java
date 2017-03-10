package io.github.awesome90.crescent.detection.checks.movement.speed;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;
import io.github.awesome90.crescent.util.Helper;

public class SpeedB extends CheckVersion {

	private long lastTime;
	private double distance;

	public SpeedB(Check check) {
		super(check, "B",
				"Compares the player's speed in blocks per second (on the x and z axes) against predetermined values.");

		this.lastTime = -1;
		this.distance = 0.0;
	}

	@Override
	public void call(Event event) {
		if (event instanceof PlayerMoveEvent) {
			final PlayerMoveEvent pme = (PlayerMoveEvent) event;

			if (lastTime != -1) {

				final long difference = System.currentTimeMillis() - lastTime;

				if (difference == 0) {
					return;
				}

				if (difference >= 1000) {
					// Check every second.

					final double blocksPerSecond = distance / (difference * 1000.0), allowed = getAllowedSpeed();

					if (blocksPerSecond > allowed) {
						final double certainty = (blocksPerSecond / allowed) * 100.0;

						if (certainty > 25.0) {
							callback(true);
						}
					}

					// Reset the values.
					lastTime = System.currentTimeMillis();
					distance = 0.0;
				}

				distance += Helper.getDistanceSquaredXZ(pme.getFrom(), pme.getTo());
			} else {
				lastTime = System.currentTimeMillis();
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

	private double getAllowedSpeed() {
		/*
		 * Compare against average movement values. Source:
		 * http://minecraft.gamepedia.com/The_Player
		 */

		final Player player = profile.getPlayer();

		if (player.isSprinting()) {

			if (player.isFlying()) {
				// Player is flying and sprinting.

				return 14.16;
			} else {
				// Player is sprinting on the ground.
				return 5.612;
			}

		} else {

			if (player.isFlying()) {
				return 10.89;
			} else {
				if (player.isSneaking()) {
					return 1.295;
				} else {
					return 4.317;
				}
			}

		}
	}

}
