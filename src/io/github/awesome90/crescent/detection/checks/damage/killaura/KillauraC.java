package io.github.awesome90.crescent.detection.checks.damage.killaura;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;
import io.github.awesome90.crescent.util.Helper;

public class KillauraC extends CheckVersion {

	private static final double DISALLOWED_ANGLE = Crescent.getInstance().getConfig()
			.getInt("killaura.c.disallowedAngle");

	public KillauraC(Check check) {
		super(check, "C", "Checks the angle between the player and the target.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof EntityDamageByEntityEvent) {
			final EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) event;

			if (edbe.getEntity() instanceof LivingEntity) {
				final LivingEntity le = (LivingEntity) edbe.getEntity();

				final Location eye = profile.getPlayer().getEyeLocation();

				final double yawBetween = Helper.getYawBetween(eye, le.getLocation());
				final double playerYaw = profile.getPlayer().getEyeLocation().getYaw();

				final double difference = Math.abs((360.0 - Math.abs(yawBetween)) - (360.0 - Math.abs(playerYaw)));

				if (yawBetween >= DISALLOWED_ANGLE) {
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
