package io.github.awesome90.crescent.detection.checks.movement.antivelocity;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class AntiVelocityA extends CheckVersion {

	public AntiVelocityA(Check check) {
		super(check, "A", "Check if the player has taken knockback when hit.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof PlayerVelocityEvent) {
			final PlayerVelocityEvent pve = (PlayerVelocityEvent) event;

			final Player player = pve.getPlayer();

			final double originalY = player.getLocation().getY();

			final double ticksToMove = Math.min((profile.getPing() * profile.getPing()) + 50.0, 300.0) / 20.0;

			final double expectedYVel = pve.getVelocity().getY();

			// If the player is supposed to move a bit, check if they actually
			// do so!
			if (expectedYVel > 0.1) {
				// Check a little later.
				new BukkitRunnable() {
					int time = 0;

					@Override
					public void run() {
						time++;

						final double current = player.getLocation().getY() - originalY;
						if (current > expectedYVel || expectedYVel - current < 0.20) {
							cancel();
							return;
						}

						if (time > ticksToMove) {
							callback(true);
							cancel();
							return;
						}
					}
				}.runTaskTimer(Crescent.getInstance(), 0L,
						1L /* Check every tick */);

			}
		}

	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
