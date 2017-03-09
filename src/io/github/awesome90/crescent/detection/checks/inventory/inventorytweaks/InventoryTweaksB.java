package io.github.awesome90.crescent.detection.checks.inventory.inventorytweaks;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class InventoryTweaksB extends CheckVersion {

	// The slots that the bowl has been placed in in the past.
	private HashMap<Integer, Integer> slotsChosen;

	public InventoryTweaksB(Check check) {
		super(check, "B", "Check if bowls are stacking up in the player's inventory.");

		this.slotsChosen = new HashMap<Integer, Integer>();
	}

	@Override
	public void call(Event event) {
		if (event instanceof InventoryClickEvent) {
			final InventoryClickEvent ice = (InventoryClickEvent) event;

			if (ice.getCurrentItem().getType() == Material.BOWL) {

				final int slot = ice.getSlot();

				if (!slotsChosen.containsKey(slot)) {
					slotsChosen.put(slot, 1);
				} else {
					slotsChosen.put(slot, slotsChosen.get(slot) + 1);
				}
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}
}