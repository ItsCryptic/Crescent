package io.github.awesome90.crescent.detection.checks.interact.noswing;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.info.Profile;

public class NoSwing extends Check {

	public NoSwing(Profile profile) {
		super(profile, CheckType.NOSWING);

		versions.add(new NoSwingA(this));
	}

}
