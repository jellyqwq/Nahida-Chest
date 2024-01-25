package top.jellyqwq;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.mixin.block.BlockMixin;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.*;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.jellyqwq.item.NahidaChestItem;
import top.jellyqwq.mixin.BedRockMixin;
import top.jellyqwq.tools.DragonPickaxeItem;
import top.jellyqwq.tools.DragonToolMaterial;

import javax.print.attribute.standard.MediaSize;


public class NahidaChest implements ModInitializer {
	public static final String NAMESPACE = "nahida_chest";
	public static final Identifier PLAY_PARTICLE_PACKET_ID = new Identifier(NAMESPACE, "particle");
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("nahida-chest");
	// 生成物品
	public static final NahidaChestItem FILTER_HOPPER = new NahidaChestItem(new FabricItemSettings().maxCount(64));
	// 创建工具
	public static ToolItem DRAGON_PICKAXE = new DragonPickaxeItem(DragonToolMaterial.DRAGON_PICKAXE, 3, 7.0F, new NahidaChestItem.Settings());

	// 添加物品组
	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(FILTER_HOPPER))
			.displayName(Text.translatable("itemGroup.nahida_chest.title"))
			.entries(((displayContext, entries) -> {
				entries.add(FILTER_HOPPER);
				entries.add(DRAGON_PICKAXE);
			}))
			.build();

	// 添加物品到别的物品组, 如红石组
//	private static void	addRedStoneItemGroup() {
//		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE)
//				.register(context -> {
//					context.add(FILTER_HOPPER);
//				});
//	}



	// 工具初始化

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Nahida Chest!");

		// register itemGroup before item
		Registry.register(Registries.ITEM_GROUP, new Identifier(NAMESPACE, "title"), ITEM_GROUP);
		// register item
		Registry.register(Registries.ITEM, new Identifier(NAMESPACE, "filter_hopper"), FILTER_HOPPER);
		Registry.register(Registries.ITEM, new Identifier(NAMESPACE, "dragon_pickaxe"), DRAGON_PICKAXE);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
				.register(context -> {
					context.addBefore(Items.WOODEN_SHOVEL, DRAGON_PICKAXE);
				});

		// other registry
//		FuelRegistry.INSTANCE.add(FILTER_HOPPER, 300);

//		addRedStoneItemGroup();

	}
}