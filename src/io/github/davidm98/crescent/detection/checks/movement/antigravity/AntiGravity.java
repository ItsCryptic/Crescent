package io.github.davidm98.crescent.detection.checks.movement.antigravity;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class AntiGravity extends Check {

	public AntiGravity(Profile profile) {
		super(profile, CheckType.ANTIGRAVITY);

		versions.add(new AntiGravityA(this));
	}

}
