package io.github.awesome90.crescent.detection.checks.inventory.inventorytweaks;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.info.Profile;

public class InventoryTweaks extends Check {

	public InventoryTweaks(Profile profile) {
		super(profile, CheckType.INVENTORYTWEAKS);

		versions.add(new InventoryTweaksA(this));
	}

}
