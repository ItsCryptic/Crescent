package io.github.awesome90.crescent.detection.checks.damage.reach;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.info.Profile;

public class Reach extends Check {

	public Reach(Profile profile) {
		super(profile, CheckType.REACH);

		versions.add(new ReachA(this));
	}

}
