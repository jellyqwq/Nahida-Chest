package top.jellyqwq.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(targets = "net.minecraft.block.AbstractBlock$AbstractBlockState")
public abstract class BedRockLootTableMixin {

    @Shadow public abstract Block getBlock();

    @Shadow protected abstract BlockState asBlockState();

    // 修改挖掘基岩掉落
    @Inject(method = "getDroppedStacks(" +
            "Lnet/minecraft/loot/context/LootContextParameterSet$Builder;" +
            ")Ljava/util/List;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void setLootTable(LootContextParameterSet.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> tmp = this.getBlock().getDroppedStacks(this.asBlockState(), builder);
        if (this.getBlock().asItem().equals(Items.BEDROCK)) {
            cir.setReturnValue(new ArrayList<>(Collections.singleton(new ItemStack(Items.BEDROCK))));
        }
    }
}
