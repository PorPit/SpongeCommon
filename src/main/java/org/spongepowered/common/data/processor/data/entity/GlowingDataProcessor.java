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
package org.spongepowered.common.data.processor.data.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import net.minecraft.entity.Entity;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.entity.ImmutableGlowingData;
import org.spongepowered.api.data.manipulator.mutable.entity.GlowingData;
import org.spongepowered.api.data.value.ValueContainer;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.MutableValue;
import org.spongepowered.api.util.OptBool;
import org.spongepowered.common.data.manipulator.mutable.entity.SpongeGlowingData;
import org.spongepowered.common.data.processor.common.AbstractEntitySingleDataProcessor;
import org.spongepowered.common.data.util.DataConstants;
import org.spongepowered.common.data.value.immutable.ImmutableSpongeValue;
import org.spongepowered.common.data.value.mutable.SpongeMutableValue;

import java.util.Optional;

public class GlowingDataProcessor extends AbstractEntitySingleDataProcessor<Entity, Boolean, MutableValue<Boolean>, GlowingData, ImmutableGlowingData> {

    public GlowingDataProcessor() {
        super(Entity.class, Keys.GLOWING);
    }

    @Override
    protected boolean set(Entity dataHolder, Boolean value) {
        dataHolder.setGlowing(checkNotNull(value, "value"));
        return true;
    }

    @Override
    protected Optional<Boolean> getVal(Entity dataHolder) {
        return OptBool.of(dataHolder.isGlowing());
    }

    @Override
    protected ImmutableValue<Boolean> constructImmutableValue(Boolean value) {
        return ImmutableSpongeValue.cachedOf(Keys.GLOWING, value, DataConstants.DEFAULT_GLOWING);
    }

    @Override
    protected MutableValue<Boolean> constructValue(Boolean actualValue) {
        return new SpongeMutableValue<>(Keys.GLOWING, DataConstants.DEFAULT_GLOWING, actualValue);
    }

    @Override
    protected GlowingData createManipulator() {
        return new SpongeGlowingData();
    }

    @Override
    public DataTransactionResult removeFrom(ValueContainer<?> container) {
        return DataTransactionResult.failNoData();
    }
}
