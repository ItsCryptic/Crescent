package io.github.awesome90.crescent.detection.checks.damage.killaura;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class KillauraB extends CheckVersion {

	public KillauraB(Check check) {
		super(check, "B", "Check if the attacked entity is in the player's line of sight.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof EntityDamageByEntityEvent) {
			final EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) event;

			if (edbe.getCause() != DamageCause.ENTITY_ATTACK) {
				return;
			}

			final Player player = profile.getPlayer();

			if (!player.hasLineOfSight(edbe.getEntity())) {
				callback(true);
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}
}
