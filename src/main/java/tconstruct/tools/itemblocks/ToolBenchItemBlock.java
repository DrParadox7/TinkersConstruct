package tconstruct.tools.itemblocks;

import net.minecraft.block.Block;

import mantle.blocks.abstracts.MultiItemBlock;

public class ToolBenchItemBlock extends MultiItemBlock {

    public static final String[] blockTypes = { "Bench", "Parts", "Parts", "Parts", "Parts", "PatternChest",
            "PatternChest", "PatternChest", "PatternChest", "PatternChest", "PatternShaper", "PatternShaper",
            "PatternShaper", "PatternShaper", "MoldingTable", "CastingTable" };

    public ToolBenchItemBlock(Block b) {
        super(b, "ToolBench", blockTypes);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
}
