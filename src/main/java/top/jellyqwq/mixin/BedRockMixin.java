package top.jellyqwq.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.jellyqwq.NahidaChest;

@Mixin(AbstractBlock.class)
public abstract class BedRockMixin {
    // 修改基岩挖掘进度
    @Inject(method = "calcBlockBreakingDelta(" +
            "Lnet/minecraft/block/BlockState;" +
            "Lnet/minecraft/entity/player/PlayerEntity;" +
            "Lnet/minecraft/world/BlockView;" +
            "Lnet/minecraft/util/math/BlockPos;"+
            ")F",
            at = @At("HEAD"),
            cancellable = true
    )
    public void allowBedrockBreaking(BlockState state, PlayerEntity player, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        // 判断方块是否为基岩
        if (state.isOf(Blocks.BEDROCK)) {
            // 判断龙镐
            if (player.getStackInHand(player.getActiveHand()).isOf(NahidaChest.DRAGON_PICKAXE)) {
                // cir.setReturnValue(player.getBlockBreakingSpeed(state) / 50.0f / (float) i);
                // player.getBlockBreakingSpeed(state) 基岩的基础破坏速度是1.0, 和灵魂沙一样, 黑曜石是9.0
                // 0.5s挖掉
                cir.setReturnValue(player.getBlockBreakingSpeed(state) / 10.0f);
            }
        }
    }


}
