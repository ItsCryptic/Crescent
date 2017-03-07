package io.github.awesome90.crescent.detection.checks.damage.killaura;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class KillauraC extends CheckVersion {

	public KillauraC(Check check) {
		super(check, "C", "Checks the angle between the player and the target.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof EntityDamageByEntityEvent) {
			final EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) event;

			if (!(edbe.getEntity() instanceof LivingEntity)) {
				return;
			}

			LivingEntity le = (LivingEntity) edbe.getEntity();

			final Location original = profile.getPlayer().getLocation();
			final Location change = original.clone();

			final Vector start = change.toVector();
			final Vector target = le.getLocation().toVector();

			// Get the difference between the two locations and set this as the
			// direction.
			change.setDirection(target.subtract(start));

			final double angle = Math.abs((change.getYaw() - original.getYaw()) % 180);

			if (angle > 90.0) {
				callback(true);
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
