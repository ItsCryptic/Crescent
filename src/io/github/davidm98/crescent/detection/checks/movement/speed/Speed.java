package io.github.davidm98.crescent.detection.checks.movement.speed;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class Speed extends Check {

	public Speed(Profile profile) {
		super(profile, CheckType.SPEED);

		versions.add(new SpeedA(this));
	}

}
