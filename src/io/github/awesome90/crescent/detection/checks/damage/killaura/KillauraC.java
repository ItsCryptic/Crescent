package io.github.awesome90.crescent.detection.checks.damage.killaura;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;
import io.github.awesome90.crescent.util.Helper;

public class KillauraC extends CheckVersion {

	public KillauraC(Check check) {
		super(check, "C", "Checks the angle between the player and the target.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof EntityDamageByEntityEvent) {
			final EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) event;

			final double angle = Helper.getAngle(profile.getPlayer(), edbe.getEntity());

			if (angle > Crescent.getInstance().getConfig().getInt("killaura.c.disallowedAngle")) {
				callback(true);
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
