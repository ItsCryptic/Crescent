package io.github.davidm98.crescent.detection.checks.movement.fly;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class Fly extends Check {

	public Fly(Profile profile) {
		super(profile, CheckType.FLY);

		versions.add(new FlyA(this));
	}

}
