package top.jellyqwq.tools;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class DragonToolMaterial implements ToolMaterial {
    public static final DragonToolMaterial DRAGON_PICKAXE = new DragonToolMaterial();

    // 耐久
    @Override
    public int getDurability() {
        return 2048;
    }

    // 挖掘速度
    @Override
    public float getMiningSpeedMultiplier() {
        return 8.0F;
    }

    // 攻击伤害
    @Override
    public float getAttackDamage() {
        return 3;
    }

    // 挖掘等级
    @Override
    public int getMiningLevel() {
        return 5;
    }

    // 附魔能力
    @Override
    public int getEnchantability() {
        return 30;
    }

    // 修补材料
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.DRAGON_EGG);
    }
}
