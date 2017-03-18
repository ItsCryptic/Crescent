package io.github.awesome90.crescent.detection.checks.damage.killaura;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;
import io.github.awesome90.crescent.detection.checks.damage.reach.ReachA;
import io.github.awesome90.crescent.detection.checks.interact.autoclicker.AutoclickerDamage;
import io.github.awesome90.crescent.learn.Learn;
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

			// Add one, as this can't be zero as it will mess up our
			// calculations if it is.
			final double angle = Helper.getYawBetween(profile.getPlayer().getEyeLocation(),
					edbe.getEntity().getLocation()) + 1;

			final double value = reachSquared * averageReach * angle * ((ping + 100) / 100) * currentDamageCPS;

			final Learn learn = new Learn(type, value);

			learn.storeData(profile.getKnownCheating());

			final double comparison = learn.compare();

			if (comparison > 50.0) {
				callback(true);
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
