package io.github.awesome90.crescent.detection.checks.blocks.liquids;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.info.Profile;

public class Liquids extends Check {

	public Liquids(Profile profile) {
		super(profile, CheckType.LIQUIDS);

		versions.add(new LiquidsA(this));
	}

}
