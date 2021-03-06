/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.world.schematic;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.schematic.BlockPalette;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.api.world.schematic.BlockPaletteTypes;

import java.util.Collection;
import java.util.Optional;

public class GlobalPalette implements BlockPalette {

    public static GlobalPalette instance = new GlobalPalette();

    private final int length;

    private GlobalPalette() {
        int highest = 0;
        for (BlockState state : Sponge.getRegistry().getAllOf(BlockState.class)) {
            int id = Block.BLOCK_STATE_IDS.get((IBlockState) state);
            if (id > highest) {
                highest = id;
            }
        }
        this.length = highest;
    }

    @Override
    public BlockPaletteType getType() {
        return BlockPaletteTypes.GLOBAL;
    }

    @Override
    public int getHighestId() {
        return this.length;
    }

    @Override
    public Optional<Integer> get(BlockState state) {
        return Optional.of(Block.BLOCK_STATE_IDS.get((IBlockState) state));
    }

    @Override
    public int getOrAssign(BlockState state) {
        return Block.BLOCK_STATE_IDS.get((IBlockState) state);
    }

    @Override
    public Optional<BlockState> get(int id) {
        return Optional.ofNullable((BlockState) Block.BLOCK_STATE_IDS.getByValue(id));
    }

    @Override
    public boolean remove(BlockState state) {
        throw new UnsupportedOperationException("Cannot remove blockstates from the global palette");
    }

    @Override
    public Collection<BlockState> getEntries() {
        return Sponge.getRegistry().getAllOf(BlockState.class);
    }

}
