package io.github.awesome90.crescent.detection.checks.damage.killaura;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;
import io.github.awesome90.crescent.detection.checks.damage.reach.ReachA;
import io.github.awesome90.crescent.detection.checks.interact.autoclicker.AutoclickerDamage;
import io.github.awesome90.crescent.util.Helper;

public class KillauraLearn extends CheckVersion {

	public KillauraLearn(Check check) {
		super(check, "Learn", "Uses data to calculate a value which is then stored for future retrieval.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof EntityDamageByEntityEvent) {
			final EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) event;

			final Player player = profile.getPlayer();

			final double reachSquared = Helper.getDistanceSquaredXZ(player.getLocation(),
					edbe.getEntity().getLocation());

			// Get the ReachA CheckVersion.
			final ReachA reach = (ReachA) profile.getCheck(CheckType.REACH).getCheckVersion("A");

			final double averageReach = reach.getAverageSquaredReach();

			final AutoclickerDamage autoclicker = (AutoclickerDamage) profile.getCheck(CheckType.AUTOCLICKER)
					.getCheckVersion("Damage");

			final double currentDamageCPS = autoclicker.getStore().getCurrentClickRate();

			final int ping = profile.getPing();

			Bukkit.broadcastMessage(
					"average reach: " + averageReach + ", cps: " + currentDamageCPS + ", ping: " + ping);

			final double value = reachSquared * averageReach * ((profile.getPing() + 100) / 100) * currentDamageCPS;

			Bukkit.broadcastMessage("Calculated value: " + value);
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
