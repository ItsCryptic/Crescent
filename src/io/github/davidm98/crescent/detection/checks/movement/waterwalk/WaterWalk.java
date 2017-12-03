package io.github.davidm98.crescent.detection.checks.movement.waterwalk;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class WaterWalk extends Check {

	public WaterWalk(Profile profile) {
		super(profile, CheckType.WATERWALK);

		versions.add(new WaterWalkA(this));
	}

}
