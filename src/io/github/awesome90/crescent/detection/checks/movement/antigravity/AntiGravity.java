package io.github.awesome90.crescent.detection.checks.movement.antigravity;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.info.Profile;

public class AntiGravity extends Check {

	public AntiGravity(Profile profile) {
		super(profile, CheckType.ANTIGRAVITY);

		versions.add(new AntiGravityA(this));
	}

}
