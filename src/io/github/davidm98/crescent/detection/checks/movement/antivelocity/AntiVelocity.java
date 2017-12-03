package io.github.davidm98.crescent.detection.checks.movement.antivelocity;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class AntiVelocity extends Check {

	public AntiVelocity(Profile profile) {
		super(profile, CheckType.ANTIVELOCITY);

		versions.add(new AntiVelocityA(this));
	}

}
