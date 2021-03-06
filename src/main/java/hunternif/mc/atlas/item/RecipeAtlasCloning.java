package hunternif.mc.atlas.item;

import hunternif.mc.atlas.RegistrarAntiqueAtlas;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipeAtlasCloning extends RecipeBase {

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		int i = 0; // number of empty atlases
		ItemStack filledAtlas = ItemStack.EMPTY;

		for (int j = 0; j < inv.getSizeInventory(); ++j) {
			ItemStack stack = inv.getStackInSlot(j);

			if (!stack.isEmpty()) {
				if (stack.getItem() == RegistrarAntiqueAtlas.ATLAS) {
					if (!filledAtlas.isEmpty()) {
						return false;
					}
					filledAtlas = stack;
				} else {
					if (stack.getItem() != RegistrarAntiqueAtlas.EMPTY_ATLAS) {
						return false;
					}
					i++;
				}
			}
		}

		return !filledAtlas.isEmpty() && i > 0;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		int i = 0; // number of new copies
		ItemStack filledAtlas = ItemStack.EMPTY;

		for (int j = 0; j < inv.getSizeInventory(); ++j) {
			ItemStack stack = inv.getStackInSlot(j);

			if (!stack.isEmpty()) {
				if (stack.getItem() == RegistrarAntiqueAtlas.ATLAS) {
					if (!filledAtlas.isEmpty()) {
						return ItemStack.EMPTY;
					}
					filledAtlas = stack;
				} else {
					if (stack.getItem() != RegistrarAntiqueAtlas.EMPTY_ATLAS) {
						return ItemStack.EMPTY;
					}
					i++;
				}
			}
		}

		if (!filledAtlas.isEmpty() && i >= 1) {
			ItemStack newAtlas = new ItemStack(RegistrarAntiqueAtlas.ATLAS, i + 1, filledAtlas.getItemDamage());

			if (filledAtlas.hasDisplayName()) {
				newAtlas.setStackDisplayName(filledAtlas.getDisplayName());
			}

			return newAtlas;
		} else {
			return ItemStack.EMPTY;
		}
	}

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}
}
