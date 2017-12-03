package io.github.davidm98.crescent.detection.checks.damage.nofall;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class NoFall extends Check {

	public NoFall(Profile profile) {
		super(profile, CheckType.NOFALL);

		versions.add(new NoFallA(this));
	}

}
