package io.github.awesome90.crescent.detection.checks.damage.killaura;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;
import io.github.awesome90.crescent.util.Helper;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;

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

				final double angle = Helper.getAngle(eye, le.getLocation());

				Bukkit.broadcastMessage("KillauraC angle: " + angle);

				if (angle >= DISALLOWED_ANGLE) {
					final AxisAlignedBB axis = ((CraftEntity) le).getHandle().getBoundingBox();

					final double widthOfAttacked = axis.d - axis.a;

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
