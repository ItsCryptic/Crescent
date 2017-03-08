package io.github.awesome90.crescent.detection.checks.damage.reach;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class ReachA extends CheckVersion {

	private static final double ALLOWED_REACH = Crescent.getInstance().getConfig().getDouble("reach.a.allowedReach");

	public ReachA(Check check) {
		super(check, "A", "Checks the distance between the player and their target on attack.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof EntityDamageByEntityEvent) {
			final EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) event;

			final Player player = profile.getPlayer();

			final double reachSquared = getDistanceSquaredXZ(player.getLocation(), edbe.getEntity().getLocation());

			if (reachSquared > ALLOWED_REACH * ALLOWED_REACH) {
				callback(true);
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

	private double getDistanceSquaredXZ(Location from, Location to) {
		final double diffX = Math.abs(from.getX() - to.getX()), diffY = Math.abs(from.getY() - to.getY());
		return diffX * diffX + diffY * diffY;
	}

}
