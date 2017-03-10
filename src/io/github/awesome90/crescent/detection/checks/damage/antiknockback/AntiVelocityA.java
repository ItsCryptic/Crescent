package io.github.awesome90.crescent.detection.checks.damage.antiknockback;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

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

			final Location original = player.getLocation().clone();
			final Vector velocity = pve.getVelocity().clone();

			// Check a little later.
			Bukkit.getScheduler().runTaskLater(Crescent.getInstance(), () -> {
				final Vector expected = player.getLocation().toVector().subtract(original.toVector());
				final double distance = expected.distanceSquared(velocity);

				Bukkit.broadcastMessage("taken distance: " + distance);
			}, 2L);

		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
