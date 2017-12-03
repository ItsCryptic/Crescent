package io.github.davidm98.crescent.detection.checks.interact.fastbow;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class Fastbow extends Check {

	public Fastbow(Profile profile) {
		super(profile, CheckType.FASTBOW);

		versions.add(new FastbowA(this));
	}

}
