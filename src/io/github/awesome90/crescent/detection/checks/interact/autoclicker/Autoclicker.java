package io.github.awesome90.crescent.detection.checks.interact.autoclicker;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.info.Profile;

public class Autoclicker extends Check {

	public Autoclicker(Profile profile) {
		super(profile, CheckType.AUTOCLICKER);

		versions.add(new AutoclickerA(this));
	}

}
