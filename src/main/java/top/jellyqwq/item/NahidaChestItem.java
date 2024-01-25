package top.jellyqwq.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class NahidaChestItem extends Item {

    public NahidaChestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0f, 1.0f);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    // 用于追加工具用途, 提示的函数
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.nahida_chest.filter_hopper.tooltips").formatted(Formatting.AQUA));
    }
}
