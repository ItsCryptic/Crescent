package io.github.davidm98.crescent.detection.checks.inventory.inventorytweaks;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class InventoryTweaks extends Check {

	public InventoryTweaks(Profile profile) {
		super(profile, CheckType.INVENTORYTWEAKS);

		versions.add(new InventoryTweaksA(this));
		versions.add(new InventoryTweaksB(this));
	}

}
