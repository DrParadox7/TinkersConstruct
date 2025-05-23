package tconstruct.tools;

import static net.minecraft.util.EnumChatFormatting.AQUA;
import static net.minecraft.util.EnumChatFormatting.DARK_AQUA;
import static net.minecraft.util.EnumChatFormatting.DARK_GRAY;
import static net.minecraft.util.EnumChatFormatting.DARK_GREEN;
import static net.minecraft.util.EnumChatFormatting.DARK_PURPLE;
import static net.minecraft.util.EnumChatFormatting.DARK_RED;
import static net.minecraft.util.EnumChatFormatting.GOLD;
import static net.minecraft.util.EnumChatFormatting.GRAY;
import static net.minecraft.util.EnumChatFormatting.GREEN;
import static net.minecraft.util.EnumChatFormatting.LIGHT_PURPLE;
import static net.minecraft.util.EnumChatFormatting.RED;
import static net.minecraft.util.EnumChatFormatting.WHITE;
import static net.minecraft.util.EnumChatFormatting.YELLOW;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import mantle.utils.RecipeRemover;
import tconstruct.TConstruct;
import tconstruct.achievements.items.CraftAchievementItem;
import tconstruct.common.itemblocks.MetadataItemBlock;
import tconstruct.items.tools.Arrow;
import tconstruct.items.tools.BattleSign;
import tconstruct.items.tools.Battleaxe;
import tconstruct.items.tools.Broadsword;
import tconstruct.items.tools.Chisel;
import tconstruct.items.tools.Cleaver;
import tconstruct.items.tools.Cutlass;
import tconstruct.items.tools.Dagger;
import tconstruct.items.tools.Excavator;
import tconstruct.items.tools.FryingPan;
import tconstruct.items.tools.Hammer;
import tconstruct.items.tools.Hatchet;
import tconstruct.items.tools.Longsword;
import tconstruct.items.tools.LumberAxe;
import tconstruct.items.tools.Mattock;
import tconstruct.items.tools.Pickaxe;
import tconstruct.items.tools.PotionLauncher;
import tconstruct.items.tools.Rapier;
import tconstruct.items.tools.Scythe;
import tconstruct.items.tools.Shovel;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.client.TConstructClientRegistry;
import tconstruct.library.crafting.Detailing;
import tconstruct.library.crafting.ModifyBuilder;
import tconstruct.library.crafting.MoldBuilder;
import tconstruct.library.crafting.PatternBuilder;
import tconstruct.library.crafting.StencilBuilder;
import tconstruct.library.crafting.ToolBuilder;
import tconstruct.library.tools.DynamicToolPart;
import tconstruct.library.tools.ToolCore;
import tconstruct.library.util.IPattern;
import tconstruct.modifiers.tools.ModAntiSpider;
import tconstruct.modifiers.tools.ModAttack;
import tconstruct.modifiers.tools.ModAutoSmelt;
import tconstruct.modifiers.tools.ModBlaze;
import tconstruct.modifiers.tools.ModButtertouch;
import tconstruct.modifiers.tools.ModCreativeToolModifier;
import tconstruct.modifiers.tools.ModDurability;
import tconstruct.modifiers.tools.ModExtraModifier;
import tconstruct.modifiers.tools.ModFlux;
import tconstruct.modifiers.tools.ModInteger;
import tconstruct.modifiers.tools.ModLapis;
import tconstruct.modifiers.tools.ModMoss;
import tconstruct.modifiers.tools.ModPiston;
import tconstruct.modifiers.tools.ModRedstone;
import tconstruct.modifiers.tools.ModReinforced;
import tconstruct.modifiers.tools.ModSmite;
import tconstruct.modifiers.tools.ModToolRepair;
import tconstruct.smeltery.TinkerSmeltery;
import tconstruct.tools.blocks.BattlesignBlock;
import tconstruct.tools.blocks.CraftingSlab;
import tconstruct.tools.blocks.CraftingStationBlock;
import tconstruct.tools.blocks.EquipBlock;
import tconstruct.tools.blocks.FurnaceSlab;
import tconstruct.tools.blocks.ToolBenchBlock;
import tconstruct.tools.blocks.ToolForgeBlock;
import tconstruct.tools.blocks.ToolStationBlock;
import tconstruct.tools.itemblocks.CraftingSlabItemBlock;
import tconstruct.tools.itemblocks.ToolBenchItemBlock;
import tconstruct.tools.items.ClayPattern;
import tconstruct.tools.items.CreativeModifier;
import tconstruct.tools.items.Manual;
import tconstruct.tools.items.MaterialItem;
import tconstruct.tools.items.Pattern;
import tconstruct.tools.items.TitleIcon;
import tconstruct.tools.items.ToolShard;
import tconstruct.tools.logic.BattlesignLogic;
import tconstruct.tools.logic.CraftingStationLogic;
import tconstruct.tools.logic.FrypanLogic;
import tconstruct.tools.logic.FurnaceLogic;
import tconstruct.tools.logic.MoldingTableLogic;
import tconstruct.tools.logic.PartBuilderLogic;
import tconstruct.tools.logic.PatternChestLogic;
import tconstruct.tools.logic.StencilTableLogic;
import tconstruct.tools.logic.ToolBenchLogic;
import tconstruct.tools.logic.ToolForgeLogic;
import tconstruct.tools.logic.ToolStationLogic;
import tconstruct.util.ItemHelper;
import tconstruct.util.config.PHConstruct;
import tconstruct.weaponry.TinkerWeaponry;
import tconstruct.world.TDispenserBehaviorSpawnEgg;
import tconstruct.world.TinkerWorld;
import tconstruct.world.blocks.SoilBlock;
import tconstruct.world.itemblocks.CraftedSoilItemBlock;

@ObjectHolder(TConstruct.modID)
@Pulse(
        id = "Tinkers' Tools",
        description = "The main core of the mod! All of the tools, the tables, and the patterns are here.",
        forced = true)
public class TinkerTools {

    /* Proxies for sides, used for graphics processing */
    @SidedProxy(clientSide = "tconstruct.tools.ToolProxyClient", serverSide = "tconstruct.tools.ToolProxyCommon")
    public static ToolProxyCommon proxy;

    // backwards compatibility
    @Deprecated
    public static Item bowstring;

    @Deprecated
    public static Item arrowhead;

    @Deprecated
    public static Item fletching;

    @Deprecated
    public static ToolCore shortbow;

    @Deprecated
    public static ToolCore arrow;

    // Crafting blocks
    public static Block toolBenchWood;
    public static Block craftingStationWood;
    public static Block craftingSlabWood;
    public static Block furnaceSlab;
    public static Block heldItemBlock;
    public static Block battlesignBlock;
    public static Block toolStation;
    public static Block toolForge;

    // Tool parts
    public static Item binding;
    public static Item toughBinding;
    public static Item toughRod;
    public static Item largePlate;
    public static Item pickaxeHead;
    public static Item shovelHead;
    public static Item hatchetHead;
    public static Item frypanHead;
    public static Item signHead;
    public static Item chiselHead;
    public static Item scytheBlade;
    public static Item broadAxeHead;
    public static Item excavatorHead;
    public static Item hammerHead;
    public static Item swordBlade;
    public static Item largeSwordBlade;
    public static Item knifeBlade;
    public static Item wideGuard;

    // Patterns and other materials
    public static Item blankPattern;
    public static Item materials; // TODO: Untwine this item
    public static Item toolRod;
    public static Item toolShard;
    public static Item titleIcon;

    // Tools
    public static ToolCore pickaxe;
    public static ToolCore shovel;
    public static ToolCore hatchet;
    public static ToolCore broadsword;
    public static ToolCore longsword;
    public static ToolCore rapier;
    public static ToolCore dagger;
    public static ToolCore cutlass;
    public static ToolCore frypan;
    public static ToolCore battlesign;
    public static ToolCore chisel;
    public static ToolCore mattock;
    public static ToolCore scythe;
    public static ToolCore lumberaxe;
    public static ToolCore cleaver;
    public static ToolCore hammer;
    public static ToolCore battleaxe;
    public static Item potionLauncher;
    public static Item handGuard;
    public static Item crossbar;
    public static Item fullGuard;
    public static Block craftedSoil; // TODO: Untwine this
    public static Block multiBrick;
    public static Block multiBrickFancy;
    public static Block multiBrickMetal;
    // Tool modifiers
    public static ModFlux modFlux;
    public static ModLapis modLapis;
    public static ModAttack modAttack;
    public static Item[] patternOutputs;
    public static Item woodPattern;
    public static Item clayPattern;

    public static Item manualBook;
    public static ToolCore excavator;
    public static Item creativeModifier;

    // recipe stuff
    public static boolean thaumcraftAvailable;
    public static boolean metallurgyAvailable;

    // Dev/Null Stuff
    public static Item openBlocksDevNull;

    @Handler
    public void preInit(FMLPreInitializationEvent event) {
        TinkerToolEvents toolEvents = new TinkerToolEvents();
        MinecraftForge.EVENT_BUS.register(toolEvents);
        FMLCommonHandler.instance().bus().register(toolEvents);

        // Blocks
        TinkerTools.toolBenchWood = new ToolBenchBlock(Material.wood).setBlockName("ToolBench");
        TinkerTools.craftingStationWood = new CraftingStationBlock(Material.wood).setBlockName("CraftingStation");
        TinkerTools.craftingSlabWood = new CraftingSlab(Material.wood).setBlockName("CraftingSlab");
        TinkerTools.furnaceSlab = new FurnaceSlab(Material.rock).setBlockName("FurnaceSlab");
        TinkerTools.toolStation = new ToolStationBlock(Material.rock).setBlockName("ToolStation");
        TinkerTools.toolForge = new ToolForgeBlock(Material.iron).setBlockName("ToolForge");

        TinkerTools.heldItemBlock = new EquipBlock(Material.wood).setBlockName("Frypan");
        TinkerTools.battlesignBlock = new BattlesignBlock(Material.wood).setBlockName("Battlesign");

        TinkerTools.craftedSoil = new SoilBlock().setLightOpacity(0).setBlockName("TConstruct.Soil");
        TinkerTools.craftedSoil.stepSound = Block.soundTypeGravel;

        GameRegistry.registerBlock(TinkerTools.toolBenchWood, ToolBenchItemBlock.class, "ToolBenchBlock");
        GameRegistry.registerTileEntity(ToolBenchLogic.class, "ToolBench");
        GameRegistry.registerTileEntity(PartBuilderLogic.class, "PartCrafter");
        GameRegistry.registerTileEntity(PatternChestLogic.class, "PatternHolder");
        GameRegistry.registerTileEntity(StencilTableLogic.class, "PatternShaper");
        GameRegistry.registerTileEntity(MoldingTableLogic.class, "MoldingTable");
        GameRegistry.registerBlock(TinkerTools.craftingStationWood, "CraftingStation");
        GameRegistry.registerTileEntity(CraftingStationLogic.class, "CraftingStation");
        GameRegistry.registerBlock(TinkerTools.craftingSlabWood, CraftingSlabItemBlock.class, "CraftingSlab");
        GameRegistry.registerBlock(TinkerTools.furnaceSlab, "FurnaceSlab");
        GameRegistry.registerTileEntity(FurnaceLogic.class, "TConstruct.Furnace");
        GameRegistry.registerBlock(TinkerTools.heldItemBlock, "HeldItemBlock");
        GameRegistry.registerTileEntity(FrypanLogic.class, "FrypanLogic");
        GameRegistry.registerBlock(TinkerTools.battlesignBlock, "BattleSignBlock");
        GameRegistry.registerTileEntity(BattlesignLogic.class, "BattlesignLogic");
        GameRegistry.registerBlock(TinkerTools.toolStation, MetadataItemBlock.class, "ToolStationBlock");
        GameRegistry.registerTileEntity(ToolStationLogic.class, "ToolStation");
        GameRegistry.registerBlock(TinkerTools.toolForge, MetadataItemBlock.class, "ToolForgeBlock");
        GameRegistry.registerTileEntity(ToolForgeLogic.class, "ToolForge");

        GameRegistry.registerBlock(TinkerTools.craftedSoil, CraftedSoilItemBlock.class, "CraftedSoil");

        // Items
        TinkerTools.titleIcon = new TitleIcon().setUnlocalizedName("tconstruct.titleicon");
        GameRegistry.registerItem(TinkerTools.titleIcon, "titleIcon");

        String[] blanks;
        if (!PHConstruct.steelPatterns)
            blanks = new String[] { "blank_pattern", "blank_cast", "blank_cast", "blank_template" };
        else {
            blanks = new String[] { "blank_pattern", "blank_cast", "blank_cast_steel", "blank_template" };
        }

        TinkerTools.blankPattern = new CraftAchievementItem(
                blanks,
                blanks,
                "materials/",
                "tinker",
                TConstructRegistry.materialTab,
                "tconstruct.pattern").setUnlocalizedName("tconstruct.Pattern");
        GameRegistry.registerItem(TinkerTools.blankPattern, "blankPattern");

        TinkerTools.materials = new MaterialItem().setUnlocalizedName("tconstruct.Materials");
        TinkerTools.toolRod = new DynamicToolPart("_rod", "ToolRod");
        TinkerTools.toolShard = new ToolShard("_chunk", "ToolShard");
        TinkerTools.woodPattern = new Pattern("pattern_", "materials/").setUnlocalizedName("tconstruct.Pattern");
        GameRegistry.registerItem(TinkerTools.materials, "materials");
        GameRegistry.registerItem(TinkerTools.woodPattern, "woodPattern");
        TConstructRegistry.addItemToDirectory("blankPattern", TinkerTools.blankPattern);
        TConstructRegistry.addItemToDirectory("woodPattern", TinkerTools.woodPattern);

        TinkerTools.clayPattern = new ClayPattern("template_", "materials/")
                .setUnlocalizedName("tconstruct.ClayPattern");
        GameRegistry.registerItem(TinkerTools.clayPattern, "clayPattern");
        TConstructRegistry.addItemToDirectory("clayPattern", TinkerTools.clayPattern);

        String[] patternTypes = { "ingot", "toolRod", "pickaxeHead", "shovelHead", "hatchetHead", "swordBlade",
                "wideGuard", "handGuard", "crossbar", "binding", "frypanHead", "signHead", "knifeBlade", "chiselHead",
                "toughRod", "toughBinding", "largePlate", "broadAxeHead", "scytheHead", "excavatorHead", "largeBlade",
                "hammerHead", "fullGuard", "bowString", "fletching", "arrowHead" };

        for (int i = 1; i < patternTypes.length; i++) {
            TConstructRegistry
                    .addItemStackToDirectory(patternTypes[i] + "Pattern", new ItemStack(TinkerTools.woodPattern, 1, i));
            TConstructRegistry.addItemStackToDirectory(
                    patternTypes[i] + "PatternClay",
                    new ItemStack(TinkerTools.clayPattern, 1, i));
        }

        TinkerTools.manualBook = new Manual();
        GameRegistry.registerItem(TinkerTools.manualBook, "manualBook");

        TinkerTools.pickaxe = new Pickaxe();
        TinkerTools.shovel = new Shovel();
        TinkerTools.hatchet = new Hatchet();
        TinkerTools.broadsword = new Broadsword();
        TinkerTools.longsword = new Longsword();
        TinkerTools.rapier = new Rapier();
        TinkerTools.dagger = new Dagger();
        TinkerTools.cutlass = new Cutlass();

        TinkerTools.frypan = new FryingPan();
        TinkerTools.battlesign = new BattleSign();
        TinkerTools.mattock = new Mattock();
        TinkerTools.chisel = new Chisel();

        TinkerTools.lumberaxe = new LumberAxe();
        TinkerTools.cleaver = new Cleaver();
        TinkerTools.scythe = new Scythe();
        TinkerTools.excavator = new Excavator();
        TinkerTools.hammer = new Hammer();
        TinkerTools.battleaxe = new Battleaxe();

        TinkerTools.arrow = new Arrow(); // to prevent nullpointers

        Item[] tools = { TinkerTools.pickaxe, TinkerTools.shovel, TinkerTools.hatchet, TinkerTools.broadsword,
                TinkerTools.longsword, TinkerTools.rapier, TinkerTools.dagger, TinkerTools.cutlass, TinkerTools.frypan,
                TinkerTools.battlesign, TinkerTools.mattock, TinkerTools.chisel, TinkerTools.lumberaxe,
                TinkerTools.cleaver, TinkerTools.scythe, TinkerTools.excavator, TinkerTools.hammer,
                TinkerTools.battleaxe };
        String[] toolStrings = { "pickaxe", "shovel", "hatchet", "broadsword", "longsword", "rapier", "dagger",
                "cutlass", "frypan", "battlesign", "mattock", "chisel", "lumberaxe", "cleaver", "scythe", "excavator",
                "hammer", "battleaxe" };

        for (int i = 0; i < tools.length; i++) {
            GameRegistry.registerItem(tools[i], toolStrings[i]); // 1.7 compat
            TConstructRegistry.addItemToDirectory(toolStrings[i], tools[i]);
        }

        TinkerTools.potionLauncher = new PotionLauncher().setUnlocalizedName("tconstruct.PotionLauncher");
        GameRegistry.registerItem(TinkerTools.potionLauncher, "potionLauncher");

        // TinkerTools.pickaxeHead = new ToolPart("_pickaxe_head",
        // "PickHead").setUnlocalizedName("tconstruct.PickaxeHead");
        TinkerTools.pickaxeHead = new DynamicToolPart("_pickaxe_head", "PickaxeHead");
        TinkerTools.shovelHead = new DynamicToolPart("_shovel_head", "ShovelHead");
        TinkerTools.hatchetHead = new DynamicToolPart("_axe_head", "AxeHead");
        TinkerTools.binding = new DynamicToolPart("_binding", "Binding");
        TinkerTools.toughBinding = new DynamicToolPart("_toughbind", "ToughBinding");
        TinkerTools.toughRod = new DynamicToolPart("_toughrod", "ToughRod");
        TinkerTools.largePlate = new DynamicToolPart("_largeplate", "LargePlate");

        TinkerTools.swordBlade = new DynamicToolPart("_sword_blade", "SwordBlade");
        TinkerTools.wideGuard = new DynamicToolPart("_large_guard", "LargeGuard");
        TinkerTools.handGuard = new DynamicToolPart("_medium_guard", "MediumGuard");
        TinkerTools.crossbar = new DynamicToolPart("_crossbar", "Crossbar");
        TinkerTools.knifeBlade = new DynamicToolPart("_knife_blade", "KnifeBlade");
        TinkerTools.fullGuard = new DynamicToolPart("_full_guard", "FullGuard").hide();

        TinkerTools.frypanHead = new DynamicToolPart("_frypan_head", "FrypanHead");
        TinkerTools.signHead = new DynamicToolPart("_battlesign_head", "SignHead");
        TinkerTools.chiselHead = new DynamicToolPart("_chisel_head", "ChiselHead");

        TinkerTools.scytheBlade = new DynamicToolPart("_scythe_head", "ScytheHead");
        TinkerTools.broadAxeHead = new DynamicToolPart("_lumberaxe_head", "LumberAxeHead");
        TinkerTools.excavatorHead = new DynamicToolPart("_excavator_head", "ExcavatorHead");
        TinkerTools.largeSwordBlade = new DynamicToolPart("_large_sword_blade", "LargeSwordBlade");
        TinkerTools.hammerHead = new DynamicToolPart("_hammer_head", "HammerHead");

        Item[] toolParts = { TinkerTools.toolRod, TinkerTools.toolShard, TinkerTools.pickaxeHead,
                TinkerTools.shovelHead, TinkerTools.hatchetHead, TinkerTools.binding, TinkerTools.toughBinding,
                TinkerTools.toughRod, TinkerTools.largePlate, TinkerTools.swordBlade, TinkerTools.wideGuard,
                TinkerTools.handGuard, TinkerTools.crossbar, TinkerTools.knifeBlade, TinkerTools.fullGuard,
                TinkerTools.frypanHead, TinkerTools.signHead, TinkerTools.chiselHead, TinkerTools.scytheBlade,
                TinkerTools.broadAxeHead, TinkerTools.excavatorHead, TinkerTools.largeSwordBlade,
                TinkerTools.hammerHead };
        String[] toolPartStrings = { "toolRod", "toolShard", "pickaxeHead", "shovelHead", "hatchetHead", "binding",
                "toughBinding", "toughRod", "heavyPlate", "swordBlade", "wideGuard", "handGuard", "crossbar",
                "knifeBlade", "fullGuard", "frypanHead", "signHead", "chiselHead", "scytheBlade", "broadAxeHead",
                "excavatorHead", "largeSwordBlade", "hammerHead" };

        for (int i = 0; i < toolParts.length; i++) {
            GameRegistry.registerItem(toolParts[i], toolPartStrings[i]); // 1.7
            // compat
            TConstructRegistry.addItemToDirectory(toolPartStrings[i], toolParts[i]);
        }

        TinkerTools.creativeModifier = new CreativeModifier().setUnlocalizedName("tconstruct.modifier.creative");
        GameRegistry.registerItem(TinkerTools.creativeModifier, "creativeModifier");

        String[] materialStrings = { "paperStack", "greenSlimeCrystal", "searedBrick", "ingotCobalt", "ingotArdite",
                "ingotManyullyn", "mossBall", "lavaCrystal", "necroticBone", "ingotCopper", "ingotTin", "ingotAluminum",
                "rawAluminum", "ingotBronze", "ingotAluminumBrass", "ingotAlumite", "ingotSteel", "blueSlimeCrystal",
                "ingotObsidian", "nuggetIron", "nuggetCopper", "nuggetTin", "nuggetAluminum", "nuggetSilver",
                "nuggetAluminumBrass", "silkyCloth", "silkyJewel", "nuggetObsidian", "nuggetCobalt", "nuggetArdite",
                "nuggetManyullyn", "nuggetBronze", "nuggetAlumite", "nuggetSteel", "ingotPigIron", "nuggetPigIron",
                "glueball" };

        for (int i = 0; i < materialStrings.length; i++) {
            TConstructRegistry.addItemStackToDirectory(materialStrings[i], new ItemStack(TinkerTools.materials, 1, i));
        }

        registerMaterials();

        registerStencils();

        // this array is only used to register the remaining pattern-part-interactions
        TinkerTools.patternOutputs = new Item[] { TinkerTools.toolRod, TinkerTools.pickaxeHead, TinkerTools.shovelHead,
                TinkerTools.hatchetHead, TinkerTools.swordBlade, TinkerTools.wideGuard, TinkerTools.handGuard,
                TinkerTools.crossbar, TinkerTools.binding, TinkerTools.frypanHead, TinkerTools.signHead,
                TinkerTools.knifeBlade, TinkerTools.chiselHead, TinkerTools.toughRod, TinkerTools.toughBinding,
                TinkerTools.largePlate, TinkerTools.broadAxeHead, TinkerTools.scytheBlade, TinkerTools.excavatorHead,
                TinkerTools.largeSwordBlade, TinkerTools.hammerHead, TinkerTools.fullGuard, null, null,
                TinkerWeaponry.arrowhead, null };

        // Moved temporarily to deal with AE2 Quartz
        TinkerTools.modFlux = new ModFlux();
        ModifyBuilder.registerModifier(TinkerTools.modFlux);

        ItemStack lapisItem = new ItemStack(Items.dye, 1, 4);
        ItemStack lapisBlock = new ItemStack(Blocks.lapis_block);
        TinkerTools.modLapis = new ModLapis(10, new ItemStack[] { lapisItem, lapisBlock }, new int[] { 1, 9 });
        ModifyBuilder.registerModifier(TinkerTools.modLapis);

        TinkerTools.modAttack = new ModAttack(
                "Quartz",
                11,
                new ItemStack[] { new ItemStack(Items.quartz), new ItemStack(Blocks.quartz_block, 1, Short.MAX_VALUE) },
                new int[] { 1, 4 });
        ModifyBuilder.registerModifier(TinkerTools.modAttack);

        oreRegistry();
    }

    private void oreRegistry() {
        OreDictionary.registerOre("ingotCobalt", new ItemStack(TinkerTools.materials, 1, 3));
        OreDictionary.registerOre("ingotArdite", new ItemStack(TinkerTools.materials, 1, 4));
        OreDictionary.registerOre("ingotManyullyn", new ItemStack(TinkerTools.materials, 1, 5));
        OreDictionary.registerOre("ingotCopper", new ItemStack(TinkerTools.materials, 1, 9));
        OreDictionary.registerOre("ingotTin", new ItemStack(TinkerTools.materials, 1, 10));
        OreDictionary.registerOre("ingotAluminum", new ItemStack(TinkerTools.materials, 1, 11));
        OreDictionary.registerOre("ingotAluminium", new ItemStack(TinkerTools.materials, 1, 11));
        OreDictionary.registerOre("ingotBronze", new ItemStack(TinkerTools.materials, 1, 13));
        OreDictionary.registerOre("ingotAluminumBrass", new ItemStack(TinkerTools.materials, 1, 14));
        OreDictionary.registerOre("ingotAluminiumBrass", new ItemStack(TinkerTools.materials, 1, 14));
        OreDictionary.registerOre("ingotAlumite", new ItemStack(TinkerTools.materials, 1, 15));
        OreDictionary.registerOre("ingotSteel", new ItemStack(TinkerTools.materials, 1, 16));
        TinkerWorld.ensureOreIsRegistered("ingotIron", new ItemStack(Items.iron_ingot));
        TinkerWorld.ensureOreIsRegistered("ingotGold", new ItemStack(Items.gold_ingot));
        OreDictionary.registerOre("ingotObsidian", new ItemStack(TinkerTools.materials, 1, 18));
        OreDictionary.registerOre("ingotPigIron", new ItemStack(TinkerTools.materials, 1, 34));
        OreDictionary.registerOre("itemRawRubber", new ItemStack(TinkerTools.materials, 1, 36));
        TinkerWorld.ensureOreIsRegistered("blockIron", new ItemStack(Blocks.iron_block));
        TinkerWorld.ensureOreIsRegistered("blockGold", new ItemStack(Blocks.gold_block));

        OreDictionary.registerOre("nuggetIron", new ItemStack(TinkerTools.materials, 1, 19));
        OreDictionary.registerOre("nuggetCopper", new ItemStack(TinkerTools.materials, 1, 20));
        OreDictionary.registerOre("nuggetTin", new ItemStack(TinkerTools.materials, 1, 21));
        OreDictionary.registerOre("nuggetAluminum", new ItemStack(TinkerTools.materials, 1, 22));
        OreDictionary.registerOre("nuggetAluminium", new ItemStack(TinkerTools.materials, 1, 22));
        OreDictionary.registerOre("nuggetAluminumBrass", new ItemStack(TinkerTools.materials, 1, 24));
        OreDictionary.registerOre("nuggetAluminiumBrass", new ItemStack(TinkerTools.materials, 1, 24));
        OreDictionary.registerOre("nuggetObsidian", new ItemStack(TinkerTools.materials, 1, 27));
        OreDictionary.registerOre("nuggetCobalt", new ItemStack(TinkerTools.materials, 1, 28));
        OreDictionary.registerOre("nuggetArdite", new ItemStack(TinkerTools.materials, 1, 29));
        OreDictionary.registerOre("nuggetManyullyn", new ItemStack(TinkerTools.materials, 1, 30));
        OreDictionary.registerOre("nuggetBronze", new ItemStack(TinkerTools.materials, 1, 31));
        OreDictionary.registerOre("nuggetAlumite", new ItemStack(TinkerTools.materials, 1, 32));
        OreDictionary.registerOre("nuggetSteel", new ItemStack(TinkerTools.materials, 1, 33));
        TinkerWorld.ensureOreIsRegistered("nuggetGold", new ItemStack(Items.gold_nugget));
        OreDictionary.registerOre("nuggetPigIron", new ItemStack(TinkerTools.materials, 1, 35));

        OreDictionary.registerOre("dustArdite", new ItemStack(TinkerTools.materials, 1, 38));
        OreDictionary.registerOre("dustCobalt", new ItemStack(TinkerTools.materials, 1, 39));
        OreDictionary.registerOre("dustAluminium", new ItemStack(TinkerTools.materials, 1, 40));
        OreDictionary.registerOre("dustAluminum", new ItemStack(TinkerTools.materials, 1, 40));
        OreDictionary.registerOre("dustManyullyn", new ItemStack(TinkerTools.materials, 1, 41));
        OreDictionary.registerOre("dustAluminiumBrass", new ItemStack(TinkerTools.materials, 1, 42));
        OreDictionary.registerOre("dustAluminumBrass", new ItemStack(TinkerTools.materials, 1, 42));

        OreDictionary.registerOre("stencilTable", new ItemStack(TinkerTools.toolBenchWood, 1, 10));
        OreDictionary.registerOre("stencilTable", new ItemStack(TinkerTools.toolBenchWood, 1, 11));
        OreDictionary.registerOre("stencilTable", new ItemStack(TinkerTools.toolBenchWood, 1, 12));
        OreDictionary.registerOre("stencilTable", new ItemStack(TinkerTools.toolBenchWood, 1, 13));

        String[] matNames = { "Wood", "Stone", "Iron", "Flint", "Cactus", "Bone", "Obsidian", "Netherrack", "Slime",
                "Paper", "Cobalt", "Ardite", "Manyullyn", "Copper", "Bronze", "Alumite", "Steel", "Blueslime" };
        if (ItemHelper.getStaticItem("itemResource", "thaumcraft.common.config.ConfigItems") != null) {
            String matNamesTC4[] = { "Thaumium", "Void" };
            String matNamesNew[] = new String[matNamesTC4.length + matNames.length];

            int i;
            for (i = 0; i < matNames.length; i++) {
                matNamesNew[i] = matNames[i];
            }
            for (i = 0; i < matNamesTC4.length; i++) {
                matNamesNew[matNames.length + i] = matNamesTC4[i];
            }
        }
        for (int i = 0; i < matNames.length; i++) {
            // TODO 1.8 remove this ore dict entry as it's outdated(use materialRod instead)
            OreDictionary.registerOre(matNames[i].toLowerCase() + "Rod", new ItemStack(TinkerTools.toolRod, 1, i));
            OreDictionary.registerOre("rod" + matNames[i], new ItemStack(TinkerTools.toolRod, 1, i));
        }
        OreDictionary.registerOre("thaumiumRod", new ItemStack(TinkerTools.toolRod, 1, 31));

        OreDictionary.registerOre("slimeball", new ItemStack(TinkerTools.materials, 1, 36));

        BlockDispenser.dispenseBehaviorRegistry.putObject(TinkerTools.titleIcon, new TDispenserBehaviorSpawnEgg());

        // Added into forge in 1.8. Remove this line when porting.
        OreDictionary.registerOre("chestWood", new ItemStack(Blocks.chest));
    }

    void setupToolTabs() {
        TConstructRegistry.materialTab.init(new ItemStack(TinkerTools.manualBook, 1, 0));
        TConstructRegistry.partTab.init(new ItemStack(TinkerTools.titleIcon, 1, 255));
        TConstructRegistry.blockTab.init(new ItemStack(TinkerTools.toolBenchWood));
        ItemStack tool = new ItemStack(TinkerTools.longsword, 1, 0);

        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("InfiTool", new NBTTagCompound());
        compound.getCompoundTag("InfiTool").setInteger("RenderHead", 2);
        compound.getCompoundTag("InfiTool").setInteger("RenderHandle", 0);
        compound.getCompoundTag("InfiTool").setInteger("RenderAccessory", 10);
        tool.setTagCompound(compound);

        TConstructRegistry.toolTab.init(tool);
    }

    // @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.getItem() == TinkerTools.materials && fuel.getItemDamage() == 7) return 26400;
        return 0;
    }

    @Handler
    public void init(FMLInitializationEvent event) {
        addPartMapping();
        addRecipesForToolBuilder();
        addRecipesForChisel();
        craftingTableRecipes();
        setupToolTabs();
        proxy.initialize();
    }

    @Handler
    public void postInit(FMLPostInitializationEvent evt) {
        vanillaToolRecipes();
        addOreDictPartMapping();
        modIntegration();
        metalPartCraftingIntegration();

        // Fix for chisels harvetslevel derp
        if ("chisel".equals(Blocks.stone.getHarvestTool(0))) Blocks.stone.setHarvestLevel("pickaxe", 0, 0);
    }

    private void addPartMapping() {
        /* Tools */

        int[] nonMetals = { 0, 1, 3, 4, 5, 6, 7, 8, 9, 17 };

        if (PHConstruct.craftMetalTools) {
            for (int mat = 0; mat < 18; mat++) {
                for (int meta = 0; meta < TinkerTools.patternOutputs.length; meta++) {
                    if (TinkerTools.patternOutputs[meta] != null) TConstructRegistry.addPartMapping(
                            TinkerTools.woodPattern,
                            meta + 1,
                            mat,
                            new ItemStack(TinkerTools.patternOutputs[meta], 1, mat));
                }
            }
        } else {
            for (int nonMetal : nonMetals) {
                for (int meta = 0; meta < TinkerTools.patternOutputs.length; meta++) {
                    if (TinkerTools.patternOutputs[meta] != null) TConstructRegistry.addPartMapping(
                            TinkerTools.woodPattern,
                            meta + 1,
                            nonMetal,
                            new ItemStack(TinkerTools.patternOutputs[meta], 1, nonMetal));
                }
            }
        }
    }

    private void addOreDictPartMapping() {
        registerPatternMaterial("plankWood", 2, "Wood");
        registerPatternMaterial("stickWood", 1, "Wood");
        registerPatternMaterial("slabWood", 1, "Wood");
        registerPatternMaterial("cobblestone", 2, "Stone");
        registerPatternMaterial("stone", 2, "Stone");
        registerPatternMaterial("compressedCobblestone1x", 18, "Stone");
    }

    private void metalPartCraftingIntegration() {
        if (TConstruct.pulsar.isPulseLoaded("Tinkers' Smeltery") || !PHConstruct.craftMetalTools) return;

        String[] metals = { "Iron", "Cobalt", "Ardite", "Manyullyn", "Copper", "Bronze", "Alumite", "Steel",
                "PigIron" };

        for (String metal : metals) {
            TinkerTools.registerPatternMaterial("ingot" + metal, 2, metal);
            TinkerTools.registerPatternMaterial("block" + metal, 18, metal);
        }
    }

    private void addRecipesForToolBuilder() {
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.pickaxe,
                TinkerTools.pickaxeHead,
                TinkerTools.toolRod,
                TinkerTools.binding);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.broadsword,
                TinkerTools.swordBlade,
                TinkerTools.toolRod,
                TinkerTools.wideGuard);
        ToolBuilder.addNormalToolRecipe(TinkerTools.hatchet, TinkerTools.hatchetHead, TinkerTools.toolRod);
        ToolBuilder.addNormalToolRecipe(TinkerTools.shovel, TinkerTools.shovelHead, TinkerTools.toolRod);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.longsword,
                TinkerTools.swordBlade,
                TinkerTools.toolRod,
                TinkerTools.handGuard);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.rapier,
                TinkerTools.swordBlade,
                TinkerTools.toolRod,
                TinkerTools.crossbar);
        ToolBuilder.addNormalToolRecipe(TinkerTools.frypan, TinkerTools.frypanHead, TinkerTools.toolRod);
        ToolBuilder.addNormalToolRecipe(TinkerTools.battlesign, TinkerTools.signHead, TinkerTools.toolRod);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.mattock,
                TinkerTools.hatchetHead,
                TinkerTools.toolRod,
                TinkerTools.shovelHead);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.dagger,
                TinkerTools.knifeBlade,
                TinkerTools.toolRod,
                TinkerTools.crossbar);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.cutlass,
                TinkerTools.swordBlade,
                TinkerTools.toolRod,
                TinkerTools.fullGuard);
        ToolBuilder.addNormalToolRecipe(TinkerTools.chisel, TinkerTools.chiselHead, TinkerTools.toolRod);

        ToolBuilder.addNormalToolRecipe(
                TinkerTools.scythe,
                TinkerTools.scytheBlade,
                TinkerTools.toughRod,
                TinkerTools.toughBinding,
                TinkerTools.toughRod);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.lumberaxe,
                TinkerTools.broadAxeHead,
                TinkerTools.toughRod,
                TinkerTools.largePlate,
                TinkerTools.toughBinding);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.cleaver,
                TinkerTools.largeSwordBlade,
                TinkerTools.toughRod,
                TinkerTools.largePlate,
                TinkerTools.toughRod);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.excavator,
                TinkerTools.excavatorHead,
                TinkerTools.toughRod,
                TinkerTools.largePlate,
                TinkerTools.toughBinding);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.hammer,
                TinkerTools.hammerHead,
                TinkerTools.toughRod,
                TinkerTools.largePlate,
                TinkerTools.largePlate);
        ToolBuilder.addNormalToolRecipe(
                TinkerTools.battleaxe,
                TinkerTools.broadAxeHead,
                TinkerTools.toughRod,
                TinkerTools.broadAxeHead,
                TinkerTools.toughBinding);

        ItemStack diamond = new ItemStack(Items.diamond);
        ModifyBuilder.registerModifier(new ModToolRepair());
        ModifyBuilder.registerModifier(
                new ModDurability(
                        new ItemStack[] { diamond },
                        0,
                        500,
                        0f,
                        3,
                        "Diamond",
                        "\u00a7b" + StatCollector.translateToLocal("modifier.tool.diamond"),
                        "\u00a7b"));
        ModifyBuilder.registerModifier(
                new ModDurability(
                        new ItemStack[] { new ItemStack(Items.emerald) },
                        1,
                        0,
                        0.5f,
                        2,
                        "Emerald",
                        "\u00a72" + StatCollector.translateToLocal("modifier.tool.emerald"),
                        "\u00a72"));

        ItemStack redstoneItem = new ItemStack(Items.redstone);
        ItemStack redstoneBlock = new ItemStack(Blocks.redstone_block);
        ModifyBuilder.registerModifier(
                new ModRedstone(2, new ItemStack[] { redstoneItem, redstoneBlock }, new int[] { 1, 9 }));

        ModifyBuilder.registerModifier(
                new ModMoss(
                        new ItemStack[] { new ItemStack(TinkerTools.materials, 1, 6) },
                        4,
                        "Moss",
                        "\u00a72",
                        StatCollector.translateToLocal("modifier.tool.mossnew")));
        ItemStack blazePowder = new ItemStack(Items.blaze_powder);
        ModifyBuilder.registerModifier(new ModBlaze(7, new ItemStack[] { blazePowder }, new int[] { 1 }));
        ModifyBuilder.registerModifier(
                new ModAutoSmelt(
                        new ItemStack[] { new ItemStack(TinkerTools.materials, 1, 7) },
                        6,
                        "Lava",
                        "\u00a74",
                        StatCollector.translateToLocal("modifier.tool.lava")));
        ModifyBuilder.registerModifier(
                new ModInteger(
                        new ItemStack[] { new ItemStack(TinkerTools.materials, 1, 8) },
                        8,
                        "Necrotic",
                        1,
                        "\u00a78",
                        StatCollector.translateToLocal("modifier.tool.necro")));

        ModifyBuilder.registerModifier(
                new ModExtraModifier(new ItemStack[] { diamond, new ItemStack(Blocks.gold_block) }, "Tier1Free"));
        ModifyBuilder.registerModifier(
                new ModExtraModifier(
                        new ItemStack[] { new ItemStack(Blocks.diamond_block),
                                new ItemStack(Items.golden_apple, 1, 1) },
                        "Tier1.5Free"));
        ModifyBuilder.registerModifier(
                new ModExtraModifier(new ItemStack[] { new ItemStack(Items.nether_star) }, "Tier2Free"));
        ModifyBuilder.registerModifier(
                new ModCreativeToolModifier(new ItemStack[] { new ItemStack(TinkerTools.creativeModifier) }));

        ItemStack silkyJewel = new ItemStack(TinkerTools.materials, 1, 26);
        ModifyBuilder.registerModifier(new ModButtertouch(new ItemStack[] { silkyJewel }, 12));

        ItemStack piston = new ItemStack(Blocks.piston);
        ModifyBuilder.registerModifier(new ModPiston(3, new ItemStack[] { piston }, new int[] { 1 }));

        ModifyBuilder.registerModifier(
                new ModInteger(
                        new ItemStack[] { new ItemStack(Blocks.obsidian), new ItemStack(Items.ender_pearl) },
                        13,
                        "Beheading",
                        1,
                        "\u00a7d",
                        "Beheading"));

        ItemStack holySoil = new ItemStack(TinkerTools.craftedSoil, 1, 4);
        ModifyBuilder.registerModifier(new ModSmite("Smite", 14, new ItemStack[] { holySoil }, new int[] { 1 }));

        ItemStack spidereyeball = new ItemStack(Items.fermented_spider_eye);
        ModifyBuilder.registerModifier(
                new ModAntiSpider("ModAntiSpider", 15, new ItemStack[] { spidereyeball }, new int[] { 1 }));

        ItemStack obsidianPlate = new ItemStack(TinkerTools.largePlate, 1, 6);
        ModifyBuilder.registerModifier(new ModReinforced(new ItemStack[] { obsidianPlate }, 16, 1));

        TConstructRegistry.registerActiveToolMod(new TActiveOmniMod());
    }

    private void addRecipesForChisel() {
        /* Detailing */
        Detailing chiseling = TConstructRegistry.getChiselDetailing();
        chiseling.addDetailing(Blocks.stone, 0, Blocks.stonebrick, 0, TinkerTools.chisel);
        if (TinkerSmeltery.speedBlock != null) {
            chiseling.addDetailing(TinkerSmeltery.speedBlock, 0, TinkerSmeltery.speedBlock, 1, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.speedBlock, 2, TinkerSmeltery.speedBlock, 3, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.speedBlock, 3, TinkerSmeltery.speedBlock, 4, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.speedBlock, 4, TinkerSmeltery.speedBlock, 5, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.speedBlock, 5, TinkerSmeltery.speedBlock, 6, TinkerTools.chisel);
        }

        chiseling.addDetailing(Blocks.obsidian, 0, TinkerTools.multiBrick, 0, TinkerTools.chisel);
        chiseling.addDetailing(Blocks.sandstone, 0, Blocks.sandstone, 2, TinkerTools.chisel);
        chiseling.addDetailing(Blocks.sandstone, 2, Blocks.sandstone, 1, TinkerTools.chisel);
        chiseling.addDetailing(Blocks.sandstone, 1, TinkerTools.multiBrick, 1, TinkerTools.chisel);
        // chiseling.addDetailing(Block.netherrack, 0, TRepo.multiBrick, 2,
        // TRepo.chisel);
        // chiseling.addDetailing(Block.stone_refined, 0, TRepo.multiBrick, 3,
        // TRepo.chisel);
        chiseling.addDetailing(Items.iron_ingot, 0, TinkerTools.multiBrick, 4, TinkerTools.chisel);
        chiseling.addDetailing(Items.gold_ingot, 0, TinkerTools.multiBrick, 5, TinkerTools.chisel);
        chiseling.addDetailing(Items.dye, 4, TinkerTools.multiBrick, 6, TinkerTools.chisel);
        chiseling.addDetailing(Items.diamond, 0, TinkerTools.multiBrick, 7, TinkerTools.chisel);
        chiseling.addDetailing(Items.redstone, 0, TinkerTools.multiBrick, 8, TinkerTools.chisel);
        chiseling.addDetailing(Items.bone, 0, TinkerTools.multiBrick, 9, TinkerTools.chisel);
        chiseling.addDetailing(Items.slime_ball, 0, TinkerTools.multiBrick, 10, TinkerTools.chisel);
        chiseling.addDetailing(TinkerWorld.strangeFood, 0, TinkerTools.multiBrick, 11, TinkerTools.chisel);
        chiseling.addDetailing(Blocks.end_stone, 0, TinkerTools.multiBrick, 12, TinkerTools.chisel);
        chiseling.addDetailing(TinkerTools.materials, 18, TinkerTools.multiBrick, 13, TinkerTools.chisel);

        // adding multiBrick / multiBrickFanxy meta 0-13 to list
        for (int sc = 0; sc < 14; sc++) {
            chiseling.addDetailing(TinkerTools.multiBrick, sc, TinkerTools.multiBrickFancy, sc, TinkerTools.chisel);
        }

        chiseling.addDetailing(Blocks.stonebrick, 0, TinkerTools.multiBrickFancy, 15, TinkerTools.chisel);
        chiseling.addDetailing(TinkerTools.multiBrickFancy, 15, TinkerTools.multiBrickFancy, 14, TinkerTools.chisel);
        chiseling.addDetailing(TinkerTools.multiBrickFancy, 14, Blocks.stonebrick, 3, TinkerTools.chisel);
        /*
         * chiseling.addDetailing(TRepo.multiBrick, 14, TRepo.multiBrickFancy, 14, TRepo.chisel);
         * chiseling.addDetailing(TRepo.multiBrick, 15, TRepo.multiBrickFancy, 15, TRepo.chisel);
         */

        if (TinkerSmeltery.smeltery != null) {
            chiseling.addDetailing(TinkerSmeltery.smeltery, 4, TinkerSmeltery.smeltery, 6, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.smeltery, 6, TinkerSmeltery.smeltery, 11, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.smeltery, 11, TinkerSmeltery.smeltery, 2, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.smeltery, 2, TinkerSmeltery.smeltery, 8, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.smeltery, 8, TinkerSmeltery.smeltery, 9, TinkerTools.chisel);
            chiseling.addDetailing(TinkerSmeltery.smeltery, 9, TinkerSmeltery.smeltery, 10, TinkerTools.chisel);

            chiseling.addDetailing(
                    TinkerSmeltery.smelteryNether,
                    4,
                    TinkerSmeltery.smelteryNether,
                    6,
                    TinkerTools.chisel);
            chiseling.addDetailing(
                    TinkerSmeltery.smelteryNether,
                    6,
                    TinkerSmeltery.smelteryNether,
                    11,
                    TinkerTools.chisel);
            chiseling.addDetailing(
                    TinkerSmeltery.smelteryNether,
                    11,
                    TinkerSmeltery.smelteryNether,
                    2,
                    TinkerTools.chisel);
            chiseling.addDetailing(
                    TinkerSmeltery.smelteryNether,
                    2,
                    TinkerSmeltery.smelteryNether,
                    8,
                    TinkerTools.chisel);
            chiseling.addDetailing(
                    TinkerSmeltery.smelteryNether,
                    8,
                    TinkerSmeltery.smelteryNether,
                    9,
                    TinkerTools.chisel);
            chiseling.addDetailing(
                    TinkerSmeltery.smelteryNether,
                    9,
                    TinkerSmeltery.smelteryNether,
                    10,
                    TinkerTools.chisel);
        }

        if (TinkerWorld.metalBlock != null) {
            // metal bricks
            chiseling.addDetailing(TinkerWorld.metalBlock, 8, TinkerTools.multiBrickMetal, 0, TinkerTools.chisel);
            chiseling.addDetailing(TinkerWorld.metalBlock, 1, TinkerTools.multiBrickMetal, 1, TinkerTools.chisel);
            chiseling.addDetailing(TinkerWorld.metalBlock, 0, TinkerTools.multiBrickMetal, 2, TinkerTools.chisel);
            chiseling.addDetailing(TinkerWorld.metalBlock, 2, TinkerTools.multiBrickMetal, 3, TinkerTools.chisel);
            // fancy metal bricks
            chiseling.addDetailing(TinkerTools.multiBrickMetal, 0, TinkerTools.multiBrickMetal, 4, TinkerTools.chisel);
            chiseling.addDetailing(TinkerTools.multiBrickMetal, 1, TinkerTools.multiBrickMetal, 5, TinkerTools.chisel);
            chiseling.addDetailing(TinkerTools.multiBrickMetal, 2, TinkerTools.multiBrickMetal, 6, TinkerTools.chisel);
            chiseling.addDetailing(TinkerTools.multiBrickMetal, 3, TinkerTools.multiBrickMetal, 7, TinkerTools.chisel);
        }
    }

    public void vanillaToolRecipes() {
        if (PHConstruct.removeVanillaToolRecipes) {
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.wooden_pickaxe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.wooden_axe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.wooden_shovel));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.wooden_hoe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.wooden_sword));

            RecipeRemover.removeAnyRecipe(new ItemStack(Items.stone_pickaxe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.stone_axe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.stone_shovel));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.stone_hoe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.stone_sword));

            RecipeRemover.removeAnyRecipe(new ItemStack(Items.iron_pickaxe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.iron_axe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.iron_shovel));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.iron_hoe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.iron_sword));

            RecipeRemover.removeAnyRecipe(new ItemStack(Items.diamond_pickaxe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.diamond_axe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.diamond_shovel));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.diamond_hoe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.diamond_sword));

            RecipeRemover.removeAnyRecipe(new ItemStack(Items.golden_pickaxe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.golden_axe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.golden_shovel));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.golden_hoe));
            RecipeRemover.removeAnyRecipe(new ItemStack(Items.golden_sword));
        }

        if (PHConstruct.labotimizeVanillaTools) {
            Items.wooden_pickaxe.setMaxDamage(1);
            Items.wooden_axe.setMaxDamage(1);
            Items.wooden_shovel.setMaxDamage(1);
            Items.wooden_hoe.setMaxDamage(1);
            Items.wooden_sword.setMaxDamage(1);

            Items.stone_pickaxe.setMaxDamage(1);
            Items.stone_axe.setMaxDamage(1);
            Items.stone_shovel.setMaxDamage(1);
            Items.stone_hoe.setMaxDamage(1);
            Items.stone_sword.setMaxDamage(1);

            Items.iron_pickaxe.setMaxDamage(1);
            Items.iron_axe.setMaxDamage(1);
            Items.iron_shovel.setMaxDamage(1);
            Items.iron_hoe.setMaxDamage(1);
            Items.iron_sword.setMaxDamage(1);

            Items.diamond_pickaxe.setMaxDamage(1);
            Items.diamond_axe.setMaxDamage(1);
            Items.diamond_shovel.setMaxDamage(1);
            Items.diamond_hoe.setMaxDamage(1);
            Items.diamond_sword.setMaxDamage(1);

            Items.golden_pickaxe.setMaxDamage(1);
            Items.golden_axe.setMaxDamage(1);
            Items.golden_shovel.setMaxDamage(1);
            Items.golden_hoe.setMaxDamage(1);
            Items.golden_sword.setMaxDamage(1);
        }
    }

    public static void registerPatternMaterial(String oreName, int value, String materialName) {
        for (ItemStack ore : OreDictionary.getOres(oreName)) {
            PatternBuilder.instance.registerMaterial(ore, value, materialName);
        }
    }

    private void craftingTableRecipes() {
        String[] patBlock = { "###", "###", "###" };
        String[] patSurround = { "###", "#m#", "###" };
        Object[] toolForgeBlocks = { "blockManyullyn" };
        Object[] toolStationBlocks = { "blockBronze", "blockSteel", "blockAlumite" };

        ItemStack smelteryStack = TinkerSmeltery.smeltery != null ? new ItemStack(TinkerSmeltery.smeltery, 1, 2)
                : new ItemStack(Blocks.obsidian, 1, 0);
        ItemStack smelteryStackNether = TinkerSmeltery.smelteryNether != null
                ? new ItemStack(TinkerSmeltery.smelteryNether, 1, 2)
                : new ItemStack(Blocks.obsidian, 1, 0);

        // ToolForge Recipes (Metal Version)
        for (int sc = 0; sc < toolForgeBlocks.length; sc++) {
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(TinkerTools.toolForge, 1, sc),
                            "bbb",
                            "msm",
                            "m m",
                            'b',
                            smelteryStackNether,
                            's',
                            new ItemStack(TinkerTools.toolStation, 1, Short.MAX_VALUE),
                            'm',
                            toolForgeBlocks[sc]));
            // adding slab version recipe
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(TinkerTools.craftingSlabWood, 1, 6),
                            "bbb",
                            "msm",
                            "m m",
                            'b',
                            smelteryStackNether,
                            's',
                            new ItemStack(TinkerTools.craftingSlabWood, 1, 5),
                            'm',
                            toolForgeBlocks[sc]));
        }

        // ToolStation Recipes
        for (int sc = 0; sc < toolStationBlocks.length; sc++) {
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(TinkerTools.toolStation, 1, sc),
                            "bbb",
                            "msm",
                            "m m",
                            'b',
                            smelteryStack,
                            's',
                            new ItemStack(TinkerTools.toolBenchWood, 1, 0),
                            'm',
                            toolStationBlocks[sc]));
            // adding slab version recipe
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(TinkerTools.craftingSlabWood, 1, 5),
                            "bbb",
                            "msm",
                            "m m",
                            'b',
                            smelteryStack,
                            's',
                            new ItemStack(TinkerTools.craftingSlabWood, 1, 1),
                            'm',
                            toolStationBlocks[sc]));
        }

        // ToolStation Recipes (Wooden Version)
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.toolBenchWood, 1, 0),
                        "p",
                        "w",
                        'p',
                        new ItemStack(TinkerTools.blankPattern, 1, 0),
                        'w',
                        "crafterWood"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.toolBenchWood, 1, 0),
                        "p",
                        "w",
                        'p',
                        new ItemStack(TinkerTools.blankPattern, 1, 0),
                        'w',
                        "craftingTableWood"));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 0),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(TinkerTools.craftingStationWood, 1, 0));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 0),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(TinkerTools.craftingSlabWood, 1, 0));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 2),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(Blocks.log, 1, 1));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 3),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(Blocks.log, 1, 2));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 4),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(Blocks.log, 1, 3));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.toolBenchWood, 1, 5),
                        "p",
                        "w",
                        'p',
                        new ItemStack(TinkerTools.blankPattern, 1, 0),
                        'w',
                        "chestWood"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.toolBenchWood, 1, 1),
                        "p",
                        "w",
                        'p',
                        new ItemStack(TinkerTools.blankPattern, 1, 0),
                        'w',
                        "logWood"));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 10),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(Blocks.planks, 1, 0));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 11),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(Blocks.planks, 1, 1));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 12),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(Blocks.planks, 1, 2));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.toolBenchWood, 1, 13),
                "p",
                "w",
                'p',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'w',
                new ItemStack(Blocks.planks, 1, 3));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.toolBenchWood, 1, 14),
                        "sss",
                        "btb",
                        "b b",
                        's',
                        new ItemStack(Blocks.stonebrick, 1, 0),
                        'b',
                        new ItemStack(Blocks.brick_block, 1, 0),
                        't',
                        "stencilTable"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.toolBenchWood, 1, 10),
                        "p",
                        "w",
                        'p',
                        new ItemStack(TinkerTools.blankPattern, 1, 0),
                        'w',
                        "plankWood"));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.furnaceSlab, 1, 0),
                "###",
                "# #",
                "###",
                '#',
                new ItemStack(Blocks.stone_slab, 1, 3));

        // Blank Pattern Recipe
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.blankPattern, 4, 0),
                        "ps",
                        "sp",
                        'p',
                        "plankWood",
                        's',
                        "stickWood"));
        // Blank Template
        GameRegistry
                .addRecipe(new ItemStack(TinkerTools.blankPattern, 16, 3), "cc", "cc", 'c', new ItemStack(Blocks.clay));
        // Manual Book Recipes
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.manualBook),
                "wp",
                'w',
                new ItemStack(TinkerTools.blankPattern, 1, 0),
                'p',
                Items.paper);
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.manualBook, 2, 0),
                new ItemStack(TinkerTools.manualBook, 1, 0),
                Items.book);
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.manualBook, 1, 1),
                new ItemStack(TinkerTools.manualBook, 1, 0));
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.manualBook, 2, 1),
                new ItemStack(TinkerTools.manualBook, 1, 1),
                Items.book);
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.manualBook, 1, 2),
                new ItemStack(TinkerTools.manualBook, 1, 1));
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.manualBook, 2, 2),
                new ItemStack(TinkerTools.manualBook, 1, 2),
                Items.book);
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.manualBook, 2, 4),
                new ItemStack(TinkerTools.manualBook, 1, 4),
                Items.book);
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.manualBook, 1, 4),
                new ItemStack(TinkerTools.manualBook, 1, 2));
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.manualBook, 1, 3),
                new ItemStack(TinkerTools.manualBook, 1, 4));
        // alternative Vanilla Book Recipe
        GameRegistry.addShapelessRecipe(
                new ItemStack(Items.book),
                Items.paper,
                Items.paper,
                Items.paper,
                Items.string,
                TinkerTools.blankPattern,
                TinkerTools.blankPattern);
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(Items.name_tag),
                        "P~ ",
                        "~O ",
                        "  ~",
                        '~',
                        Items.string,
                        'P',
                        Items.paper,
                        'O',
                        "slimeball"));

        // Paperstack Recipe
        GameRegistry.addRecipe(new ItemStack(TinkerTools.materials, 1, 0), "pp", "pp", 'p', Items.paper);
        // Mossball Recipe
        GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(TinkerTools.materials, 1, 6), patBlock, '#', "stoneMossy"));
        // LavaCrystal Recipes -Auto-smelt
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.materials, 1, 7),
                "xcx",
                "cbc",
                "xcx",
                'b',
                Items.lava_bucket,
                'c',
                Items.fire_charge,
                'x',
                Items.blaze_rod);
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.materials, 1, 7),
                "xcx",
                "cbc",
                "xcx",
                'b',
                Items.lava_bucket,
                'x',
                Items.fire_charge,
                'c',
                Items.blaze_rod);
        // Slimy sand Recipes
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.craftedSoil, 1, 0),
                Items.slime_ball,
                Items.slime_ball,
                Items.slime_ball,
                Items.slime_ball,
                Blocks.sand,
                Blocks.dirt);
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.craftedSoil, 1, 2),
                TinkerWorld.strangeFood,
                TinkerWorld.strangeFood,
                TinkerWorld.strangeFood,
                TinkerWorld.strangeFood,
                Blocks.sand,
                Blocks.dirt);
        // Grout Recipes
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.craftedSoil, 2, 1),
                Items.clay_ball,
                Blocks.sand,
                Blocks.gravel);
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.craftedSoil, 8, 1),
                new ItemStack(Blocks.clay, 1, Short.MAX_VALUE),
                Blocks.sand,
                Blocks.sand,
                Blocks.sand,
                Blocks.sand,
                Blocks.gravel,
                Blocks.gravel,
                Blocks.gravel,
                Blocks.gravel);
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.craftedSoil, 2, 6),
                Items.nether_wart,
                Blocks.soul_sand,
                Blocks.gravel);
        // Graveyard Soil Recipes
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.craftedSoil, 1, 3),
                Blocks.dirt,
                Items.rotten_flesh,
                new ItemStack(Items.dye, 1, 15));
        // Silky Cloth Recipes
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.materials, 1, 25),
                patSurround,
                'm',
                new ItemStack(TinkerTools.materials, 1, 24),
                '#',
                new ItemStack(Items.string));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.materials, 1, 25),
                        patSurround,
                        'm',
                        "nuggetGold",
                        '#',
                        new ItemStack(Items.string)));
        // Silky Jewel Recipes
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.materials, 1, 26),
                        " c ",
                        "cec",
                        " c ",
                        'c',
                        new ItemStack(TinkerTools.materials, 1, 25),
                        'e',
                        "gemEmerald"));

        // Advanced WorkBench Recipes
        GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(TinkerTools.craftingStationWood, 1, 0), "b", 'b', "crafterWood"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.craftingStationWood, 1, 0),
                        "b",
                        'b',
                        "craftingTableWood"));
        // Slab crafters
        GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(TinkerTools.craftingSlabWood, 6, 0), "bbb", 'b', "crafterWood"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(TinkerTools.craftingSlabWood, 6, 0),
                        "bbb",
                        'b',
                        "craftingTableWood"));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 0),
                "b",
                'b',
                new ItemStack(TinkerTools.craftingStationWood, 1, 0));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 1),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 0));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 2),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 1));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 2),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 2));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 2),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 3));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 2),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 4));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 4),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 5));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 3),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 10));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 3),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 11));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 3),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 12));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 3),
                "b",
                'b',
                new ItemStack(TinkerTools.toolBenchWood, 1, 13));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 5),
                "b",
                'b',
                new ItemStack(TinkerTools.toolStation, 1, Short.MAX_VALUE));
        GameRegistry.addRecipe(
                new ItemStack(TinkerTools.craftingSlabWood, 1, 6),
                "b",
                'b',
                new ItemStack(TinkerTools.toolForge, 1, Short.MAX_VALUE));

        GameRegistry.addRecipe(
                new ShapelessOreRecipe(new ItemStack(TinkerTools.materials, 1, 41), "dustArdite", "dustCobalt"));
        GameRegistry.addRecipe(
                new ShapelessOreRecipe(
                        new ItemStack(TinkerTools.materials, 4, 42),
                        "dustAluminium",
                        "dustAluminium",
                        "dustAluminium",
                        "dustCopper"));
        // Ingot Template
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.clayPattern, 1, 0),
                Items.iron_ingot,
                new ItemStack(TinkerTools.blankPattern, 1, 3)); // Iron Ingot

        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.clayPattern, 1, 0),
                Items.gold_ingot,
                new ItemStack(TinkerTools.blankPattern, 1, 3)); // Gold Ingot

        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.clayPattern, 1, 0),
                Items.brick,
                new ItemStack(TinkerTools.blankPattern, 1, 3)); // Brick

        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.clayPattern, 1, 0),
                Items.netherbrick,
                new ItemStack(TinkerTools.blankPattern, 1, 3)); // Nether Brick

        // Nugget Template
        GameRegistry.addShapelessRecipe(
                new ItemStack(TinkerTools.clayPattern, 1, 27),
                Items.gold_nugget,
                new ItemStack(TinkerTools.blankPattern, 1, 3));
    }

    private void modIntegration() {
        /* Thaumcraft */
        Object obj = ItemHelper.getStaticItem("itemResource", "thaumcraft.common.config.ConfigItems");
        if (obj != null) {
            TConstruct.logger.info("Thaumcraft detected. Adding thaumium tools.");
            TinkerTools.thaumcraftAvailable = true;
            TConstructClientRegistry.addMaterialRenderMapping(MaterialID.Thaumium, "tinker", "thaumium", true);
            TConstructClientRegistry.addMaterialRenderMapping(MaterialID.Void, "tinker", "void", true);

            TConstructRegistry
                    .addToolMaterial(MaterialID.Thaumium, "Thaumium", 3, 400, 700, 2, 1.3F, 0, 0f, "\u00A75", 0x51437c);
            TConstructRegistry
                    .addToolMaterial(MaterialID.Void, "Void", 3, 150, 1400, 3, 0.8F, 0, 0f, "\u00A75", 0x210c35);
            PatternBuilder.instance.registerFullMaterial(
                    new ItemStack((Item) obj, 1, 2),
                    2,
                    "Thaumium",
                    new ItemStack(TinkerTools.toolShard, 1, MaterialID.Thaumium),
                    new ItemStack(TinkerTools.toolRod, 1, MaterialID.Thaumium),
                    MaterialID.Thaumium);
            PatternBuilder.instance.registerFullMaterial(
                    new ItemStack((Item) obj, 1, 16),
                    2,
                    "Void",
                    new ItemStack(TinkerTools.toolShard, 1, MaterialID.Void),
                    new ItemStack(TinkerTools.toolRod, 1, MaterialID.Void),
                    MaterialID.Void);

            // Thaumium weaponry toolparts
            if (TConstruct.pulsar.isPulseLoaded("Tinkers' Weaponry")) {
                TConstructRegistry.addBowMaterial(MaterialID.Thaumium, 35, 4.75f);
                TConstructRegistry.addArrowMaterial(MaterialID.Thaumium, 1.8F, 0.5F);

                TConstructRegistry.addBowMaterial(MaterialID.Void, 45, 8.0f);
                TConstructRegistry.addArrowMaterial(MaterialID.Void, 3.0F, 0.5F);
            }

            TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Thaumium);
            TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Void);
        } else TConstruct.logger.warn("Thaumcraft not detected.");

        if (Loader.isModLoaded("Natura")) {
            try {
                Object plantItem = ItemHelper.getStaticItem("plantItem", "mods.natura.common.NContent");
                TConstructRegistry.addBowstringMaterial(
                        2,
                        2,
                        new ItemStack((Item) plantItem, 1, 7),
                        new ItemStack(TinkerWeaponry.bowstring, 1, 2),
                        1.2F,
                        1.1F,
                        1.2f,
                        0xf3414f);
            } catch (Exception ignored) {} // No need to handle
        }
        TinkerTools.metallurgyAvailable = Loader.isModLoaded("Metallurgy");

        // OpenBlocks
        openBlocksDevNull = GameRegistry.findItem("OpenBlocks", "devnull");
    }

    void registerMaterials() {
        // Tool Materials: id, name, harvestlevel, durability, speed, damage, handlemodifier, reinforced, shoddy, style
        // color, primary color for block use
        TConstructRegistry
                .addToolMaterial(MaterialID.Wood, "Wood", 1, 97, 350, 0, 1.0F, 0, 0f, YELLOW.toString(), 0x755821);
        TConstructRegistry
                .addToolMaterial(MaterialID.Stone, "Stone", 1, 131, 400, 1, 0.5F, 0, 1f, GRAY.toString(), 0x7F7F7F);
        TConstructRegistry
                .addToolMaterial(MaterialID.Iron, "Iron", 2, 250, 600, 2, 1.3F, 1, 0f, WHITE.toString(), 0xDADADA);
        TConstructRegistry.addToolMaterial(
                MaterialID.Flint,
                "Flint",
                1,
                171,
                525,
                2,
                0.7F,
                0,
                0f,
                DARK_GRAY.toString(),
                0x484848);
        TConstructRegistry.addToolMaterial(
                MaterialID.Cactus,
                "Cactus",
                1,
                150,
                500,
                2,
                1.0F,
                0,
                -1f,
                DARK_GREEN.toString(),
                0x12690b);
        TConstructRegistry
                .addToolMaterial(MaterialID.Bone, "Bone", 1, 200, 400, 1, 1.0F, 0, 0f, YELLOW.toString(), 0xEDEBCA);
        TConstructRegistry.addToolMaterial(
                MaterialID.Obsidian,
                "Obsidian",
                3,
                89,
                700,
                2,
                0.8F,
                3,
                0f,
                LIGHT_PURPLE.toString(),
                0xaa7ff5);
        TConstructRegistry.addToolMaterial(
                MaterialID.Netherrack,
                "Netherrack",
                2,
                131,
                400,
                1,
                1.2F,
                0,
                1f,
                DARK_RED.toString(),
                0x833238);
        TConstructRegistry
                .addToolMaterial(MaterialID.Slime, "Slime", 0, 500, 150, 0, 1.5F, 0, 0f, GREEN.toString(), 0x6EB065);
        TConstructRegistry
                .addToolMaterial(MaterialID.Paper, "Paper", 0, 30, 200, 0, 0.3F, 0, 0f, WHITE.toString(), 0xFFFFFF);
        TConstructRegistry.addToolMaterial(
                MaterialID.Cobalt,
                "Cobalt",
                4,
                800,
                1400,
                3,
                1.75F,
                2,
                0f,
                DARK_AQUA.toString(),
                0x2376DD);
        TConstructRegistry.addToolMaterial(
                MaterialID.Ardite,
                "Ardite",
                4,
                500,
                800,
                3,
                2.0F,
                0,
                2f,
                DARK_RED.toString(),
                0xA53000);
        TConstructRegistry.addToolMaterial(
                MaterialID.Manyullyn,
                "Manyullyn",
                5,
                1200,
                900,
                4,
                2.5F,
                0,
                0f,
                DARK_PURPLE.toString(),
                0x7338A5);
        TConstructRegistry
                .addToolMaterial(MaterialID.Copper, "Copper", 1, 180, 500, 2, 1.15F, 0, 0f, RED.toString(), 0xCC6410);
        TConstructRegistry
                .addToolMaterial(MaterialID.Bronze, "Bronze", 2, 550, 800, 2, 1.3F, 1, 0f, GOLD.toString(), 0xCA9956);
        TConstructRegistry.addToolMaterial(
                MaterialID.Alumite,
                "Alumite",
                4,
                700,
                800,
                3,
                1.3F,
                2,
                0f,
                LIGHT_PURPLE.toString(),
                0xffa7e9);
        TConstructRegistry
                .addToolMaterial(MaterialID.Steel, "Steel", 4, 750, 1000, 4, 1.3F, 2, 0f, GRAY.toString(), 0xA0A0A0);
        TConstructRegistry.addToolMaterial(
                MaterialID.BlueSlime,
                "BlueSlime",
                0,
                1200,
                150,
                0,
                2.0F,
                0,
                0f,
                AQUA.toString(),
                0x66AEB0);
        TConstructRegistry
                .addToolMaterial(MaterialID.PigIron, "PigIron", 3, 250, 600, 2, 1.3F, 1, 0f, RED.toString(), 0xF0A8A4);

        // Register all the materials for default toolparts
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Wood);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Stone);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Iron);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Flint);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Cactus);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Bone);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Obsidian);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Netherrack);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Slime);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Paper);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Cobalt);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Ardite);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Manyullyn);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Copper);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Bronze);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Alumite);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Steel);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.BlueSlime);
        TConstructRegistry.addDefaultToolPartMaterial(MaterialID.PigIron);

        // Register all the materials for default toolparts
        // TConstructRegistry.addDefaultShardMaterial(MaterialID.Wood);
        TConstructRegistry.addDefaultShardMaterial(MaterialID.Stone);
        TConstructRegistry.addDefaultShardMaterial(MaterialID.Flint);
        TConstructRegistry.addDefaultShardMaterial(MaterialID.Cactus);
        // TConstructRegistry.addDefaultShardMaterial(MaterialID.Bone);
        TConstructRegistry.addDefaultShardMaterial(MaterialID.Obsidian);
        TConstructRegistry.addDefaultShardMaterial(MaterialID.Netherrack);
        TConstructRegistry.addDefaultShardMaterial(MaterialID.Slime);
        // TConstructRegistry.addDefaultShardMaterial(MaterialID.Paper);
        TConstructRegistry.addDefaultShardMaterial(MaterialID.BlueSlime);

        if (PHConstruct.craftMetalTools) {
            TConstructRegistry.addDefaultShardMaterial(MaterialID.Iron);
            TConstructRegistry.addDefaultShardMaterial(MaterialID.Cobalt);
            TConstructRegistry.addDefaultShardMaterial(MaterialID.Ardite);
            TConstructRegistry.addDefaultShardMaterial(MaterialID.Manyullyn);
            TConstructRegistry.addDefaultShardMaterial(MaterialID.Copper);
            TConstructRegistry.addDefaultShardMaterial(MaterialID.Bronze);
            TConstructRegistry.addDefaultShardMaterial(MaterialID.Alumite);
            TConstructRegistry.addDefaultShardMaterial(MaterialID.Steel);
            TConstructRegistry.addDefaultShardMaterial(MaterialID.PigIron);
        }

        PatternBuilder pb = PatternBuilder.instance;
        if (PHConstruct.enableTWood) pb.registerFullMaterial(
                Blocks.planks,
                2,
                "Wood",
                new ItemStack(Items.stick),
                new ItemStack(Items.stick),
                0);
        else pb.registerMaterialSet("Wood", new ItemStack(Items.stick, 2), new ItemStack(Items.stick), 0);
        if (PHConstruct.enableTStone) {
            pb.registerFullMaterial(
                    Blocks.stone,
                    2,
                    "Stone",
                    new ItemStack(TinkerTools.toolShard, 1, 1),
                    new ItemStack(TinkerTools.toolRod, 1, 1),
                    1);
            pb.registerMaterial(Blocks.cobblestone, 2, "Stone");
        } else pb.registerMaterialSet(
                "Stone",
                new ItemStack(TinkerTools.toolShard, 1, 1),
                new ItemStack(TinkerTools.toolRod, 1, 1),
                1);
        pb.registerFullMaterial(
                Items.iron_ingot,
                2,
                "Iron",
                new ItemStack(TinkerTools.toolShard, 1, 2),
                new ItemStack(TinkerTools.toolRod, 1, 2),
                2);
        if (PHConstruct.enableTFlint) pb.registerFullMaterial(
                Items.flint,
                2,
                "Flint",
                new ItemStack(TinkerTools.toolShard, 1, 3),
                new ItemStack(TinkerTools.toolRod, 1, 3),
                3);
        else pb.registerMaterialSet(
                "Flint",
                new ItemStack(TinkerTools.toolShard, 1, 3),
                new ItemStack(TinkerTools.toolRod, 1, 3),
                3);
        if (PHConstruct.enableTCactus) pb.registerFullMaterial(
                Blocks.cactus,
                2,
                "Cactus",
                new ItemStack(TinkerTools.toolShard, 1, 4),
                new ItemStack(TinkerTools.toolRod, 1, 4),
                4);
        else pb.registerMaterialSet(
                "Cactus",
                new ItemStack(TinkerTools.toolShard, 1, 4),
                new ItemStack(TinkerTools.toolRod, 1, 4),
                4);
        if (PHConstruct.enableTBone) pb.registerFullMaterial(
                Items.bone,
                2,
                "Bone",
                new ItemStack(Items.dye, 1, 15),
                new ItemStack(Items.bone),
                5);
        else pb.registerMaterialSet("Bone", new ItemStack(Items.dye, 1, 15), new ItemStack(Items.bone), 5);
        pb.registerFullMaterial(
                Blocks.obsidian,
                2,
                "Obsidian",
                new ItemStack(TinkerTools.toolShard, 1, 6),
                new ItemStack(TinkerTools.toolRod, 1, 6),
                6);
        pb.registerMaterial(new ItemStack(materials, 1, 18), 2, "Obsidian");
        if (PHConstruct.enableTNetherrack) pb.registerFullMaterial(
                Blocks.netherrack,
                2,
                "Netherrack",
                new ItemStack(TinkerTools.toolShard, 1, 7),
                new ItemStack(TinkerTools.toolRod, 1, 7),
                7);
        else pb.registerMaterialSet(
                "Netherrack",
                new ItemStack(TinkerTools.toolShard, 1, 7),
                new ItemStack(TinkerTools.toolRod, 1, 7),
                7);
        if (PHConstruct.enableTSlime) pb.registerFullMaterial(
                new ItemStack(materials, 1, 1),
                2,
                "Slime",
                new ItemStack(toolShard, 1, 8),
                new ItemStack(toolRod, 1, 8),
                8);
        else pb.registerMaterialSet(
                "Slime",
                new ItemStack(TinkerTools.toolShard, 1, 8),
                new ItemStack(TinkerTools.toolRod, 1, 17),
                8);
        if (PHConstruct.enableTPaper) pb.registerFullMaterial(
                new ItemStack(materials, 1, 0),
                2,
                "Paper",
                new ItemStack(Items.paper, 2),
                new ItemStack(toolRod, 1, 9),
                9);
        else pb.registerMaterialSet(
                "Paper",
                new ItemStack(Items.paper, 2),
                new ItemStack(TinkerTools.toolRod, 1, 9),
                9);
        pb.registerMaterialSet("Cobalt", new ItemStack(toolShard, 1, 10), new ItemStack(toolRod, 1, 10), 10);
        pb.registerMaterialSet("Ardite", new ItemStack(toolShard, 1, 11), new ItemStack(toolRod, 1, 11), 11);
        pb.registerMaterialSet("Manyullyn", new ItemStack(toolShard, 1, 12), new ItemStack(toolRod, 1, 12), 12);
        pb.registerMaterialSet("Copper", new ItemStack(toolShard, 1, 13), new ItemStack(toolRod, 1, 13), 13);
        pb.registerMaterialSet("Bronze", new ItemStack(toolShard, 1, 14), new ItemStack(toolRod, 1, 14), 14);
        pb.registerMaterialSet("Alumite", new ItemStack(toolShard, 1, 15), new ItemStack(toolRod, 1, 15), 15);
        pb.registerMaterialSet("Steel", new ItemStack(toolShard, 1, 16), new ItemStack(toolRod, 1, 16), 16);
        if (PHConstruct.enableTBlueSlime) pb.registerFullMaterial(
                new ItemStack(materials, 1, 17),
                2,
                "BlueSlime",
                new ItemStack(toolShard, 1, 17),
                new ItemStack(toolRod, 1, 17),
                17);
        else pb.registerMaterialSet(
                "BlueSlime",
                new ItemStack(TinkerTools.toolShard, 1, 17),
                new ItemStack(TinkerTools.toolRod, 1, 17),
                17);
        pb.registerFullMaterial(
                new ItemStack(materials, 1, 34),
                2,
                "PigIron",
                new ItemStack(toolShard, 1, 18),
                new ItemStack(toolRod, 1, 18),
                18);

        pb.addToolPattern((IPattern) TinkerTools.woodPattern);
    }

    private void registerStencils() {
        StencilBuilder.registerBlankStencil(new ItemStack(TinkerTools.blankPattern, 1, 0));
        // we register this manually because we want that specific order
        if (PHConstruct.balancedPartCrafting) {
            StencilBuilder.registerStencil(0, TinkerTools.woodPattern, 1); // tool rod
            StencilBuilder.registerStencil(1, TinkerTools.woodPattern, 9); // binding
            StencilBuilder.registerStencil(2, TinkerTools.woodPattern, 6); // wide guard

            StencilBuilder.registerStencil(3, TinkerTools.woodPattern, 2); // pickaxe head
            StencilBuilder.registerStencil(4, TinkerTools.woodPattern, 3); // shovel head
            StencilBuilder.registerStencil(5, TinkerTools.woodPattern, 4); // hatchet head

            StencilBuilder.registerStencil(6, TinkerTools.woodPattern, 12); // knifeblade
            StencilBuilder.registerStencil(7, TinkerTools.woodPattern, 5); // swordblade
            StencilBuilder.registerStencil(8, TinkerTools.woodPattern, 10); // frying pan

            StencilBuilder.registerStencil(9, TinkerTools.woodPattern, 11); // battlesign
            StencilBuilder.registerStencil(10, TinkerTools.woodPattern, 13); // chisel
        } else {
            StencilBuilder.registerStencil(0, TinkerTools.woodPattern, 1); // tool rod
            StencilBuilder.registerStencil(1, TinkerTools.woodPattern, 9); // binding
            StencilBuilder.registerStencil(2, TinkerTools.woodPattern, 14); // large tool rod
            StencilBuilder.registerStencil(3, TinkerTools.woodPattern, 15); // large binding

            StencilBuilder.registerStencil(4, TinkerTools.woodPattern, 2); // pickaxe head
            StencilBuilder.registerStencil(5, TinkerTools.woodPattern, 3); // shovel head
            StencilBuilder.registerStencil(6, TinkerTools.woodPattern, 4); // hatchet head
            StencilBuilder.registerStencil(7, TinkerTools.woodPattern, 18); // scythe

            StencilBuilder.registerStencil(8, TinkerTools.woodPattern, 21); // hammer head
            StencilBuilder.registerStencil(9, TinkerTools.woodPattern, 19); // excavator head
            StencilBuilder.registerStencil(10, TinkerTools.woodPattern, 17); // lumberaxe head
            StencilBuilder.registerStencil(11, TinkerTools.woodPattern, 16); // large plate

            StencilBuilder.registerStencil(12, TinkerTools.woodPattern, 10); // frying pan
            StencilBuilder.registerStencil(13, TinkerTools.woodPattern, 11); // battlesign
            StencilBuilder.registerStencil(14, TinkerTools.woodPattern, 13); // chisel

            StencilBuilder.registerStencil(15, TinkerTools.woodPattern, 12); // knifeblade
            StencilBuilder.registerStencil(16, TinkerTools.woodPattern, 5); // swordblade
            StencilBuilder.registerStencil(17, TinkerTools.woodPattern, 20); // cleaver blade

            StencilBuilder.registerStencil(18, TinkerTools.woodPattern, 8); // crossbar
            StencilBuilder.registerStencil(19, TinkerTools.woodPattern, 7); // small guard
            StencilBuilder.registerStencil(20, TinkerTools.woodPattern, 6); // wide guard
        }
        // Moulds
        MoldBuilder.registerBlankMold(new ItemStack(TinkerTools.blankPattern, 1, 3));

        // we register this manually because we want that specific order
        MoldBuilder.registerMold(0, TinkerTools.clayPattern, 1); // tool rod
        MoldBuilder.registerMold(1, TinkerTools.clayPattern, 9); // binding
        MoldBuilder.registerMold(2, TinkerTools.clayPattern, 14); // large tool rod
        MoldBuilder.registerMold(3, TinkerTools.clayPattern, 15); // large binding

        MoldBuilder.registerMold(4, TinkerTools.clayPattern, 2); // pickaxe head
        MoldBuilder.registerMold(5, TinkerTools.clayPattern, 3); // shovel head
        MoldBuilder.registerMold(6, TinkerTools.clayPattern, 4); // hatchet head
        MoldBuilder.registerMold(7, TinkerTools.clayPattern, 18); // scythe

        MoldBuilder.registerMold(8, TinkerTools.clayPattern, 21); // hammer head
        MoldBuilder.registerMold(9, TinkerTools.clayPattern, 19); // excavator head
        MoldBuilder.registerMold(10, TinkerTools.clayPattern, 17); // lumberaxe head
        MoldBuilder.registerMold(11, TinkerTools.clayPattern, 16); // large plate

        MoldBuilder.registerMold(12, TinkerTools.clayPattern, 10); // frying pan
        MoldBuilder.registerMold(13, TinkerTools.clayPattern, 11); // battlesign
        MoldBuilder.registerMold(14, TinkerTools.clayPattern, 13); // chisel

        MoldBuilder.registerMold(15, TinkerTools.clayPattern, 12); // knifeblade
        MoldBuilder.registerMold(16, TinkerTools.clayPattern, 5); // swordblade
        MoldBuilder.registerMold(17, TinkerTools.clayPattern, 20); // cleaver blade

        MoldBuilder.registerMold(18, TinkerTools.clayPattern, 8); // crossbar
        MoldBuilder.registerMold(19, TinkerTools.clayPattern, 7); // small guard
        MoldBuilder.registerMold(20, TinkerTools.clayPattern, 6); // wide guard
    }

    public static final class MaterialID {

        public static final int Wood = 0;
        public static final int Stone = 1;
        public static final int Iron = 2;
        public static final int Flint = 3;
        public static final int Cactus = 4;
        public static final int Bone = 5;
        public static final int Obsidian = 6;
        public static final int Netherrack = 7;
        public static final int Slime = 8;
        public static final int Paper = 9;
        public static final int Cobalt = 10;
        public static final int Ardite = 11;
        public static final int Manyullyn = 12; // I'd still call it Manymetal --boni
        public static final int Copper = 13;
        public static final int Bronze = 14;
        public static final int Alumite = 15;
        public static final int Steel = 16;
        public static final int BlueSlime = 17;
        public static final int PigIron = 18;

        public static final int Thaumium = 31;
        public static final int Void = 32;
    }
}
