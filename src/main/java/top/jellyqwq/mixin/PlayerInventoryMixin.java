package top.jellyqwq.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Deprecated
// may be invalid
@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {

    @Inject(method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F",
            at = @At("HEAD"),
            cancellable = true
    )
    private void setBedrockBlockBreakingSpeed(BlockState block, CallbackInfoReturnable<Float> cir) {
        if (block.isOf(Blocks.BEDROCK)) {
            cir.setReturnValue(9.0f);
        }
    }

}
