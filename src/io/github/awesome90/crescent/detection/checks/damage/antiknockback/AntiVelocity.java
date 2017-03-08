package io.github.awesome90.crescent.detection.checks.damage.antiknockback;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.info.Profile;

public class AntiVelocity extends Check {

	public AntiVelocity(Profile profile) {
		super(profile, CheckType.ANTIVELOCITY);

		versions.add(new AntiVelocityA(this));
	}

}
