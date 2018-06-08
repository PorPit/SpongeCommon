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

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.entity.ImmutableElytraFlyingData;
import org.spongepowered.api.data.manipulator.mutable.entity.ElytraFlyingData;
import org.spongepowered.api.data.value.ValueContainer;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.MutableValue;
import org.spongepowered.common.data.manipulator.mutable.entity.SpongeElytraFlyingData;
import org.spongepowered.common.data.processor.common.AbstractEntitySingleDataProcessor;
import org.spongepowered.common.data.value.immutable.ImmutableSpongeValue;
import org.spongepowered.common.data.value.mutable.SpongeMutableValue;
import org.spongepowered.common.interfaces.entity.IMixinEntityLivingBase;

import java.util.Optional;

public class ElytraFlyingDataProcessor extends AbstractEntitySingleDataProcessor<EntityLivingBase, Boolean, MutableValue<Boolean>, ElytraFlyingData, ImmutableElytraFlyingData> {

    public ElytraFlyingDataProcessor() {
        super(EntityLivingBase.class, Keys.IS_ELYTRA_FLYING);
    }

    @Override
    protected boolean set(EntityLivingBase dataHolder, Boolean value) {
        ((IMixinEntityLivingBase) dataHolder).setElytraFlying(value);
        return true;
    }

    @Override
    protected Optional<Boolean> getVal(EntityLivingBase dataHolder) {
        return Optional.of(dataHolder.isElytraFlying());
    }

    @Override
    protected ImmutableValue<Boolean> constructImmutableValue(Boolean value) {
        return ImmutableSpongeValue.cachedOf(this.key, false, value);
    }

    @Override
    protected MutableValue<Boolean> constructValue(Boolean actualValue) {
        return new SpongeMutableValue<>(this.key, false, actualValue);
    }

    @Override
    public DataTransactionResult removeFrom(ValueContainer<?> container) {
        return DataTransactionResult.failNoData();
    }

    @Override
    protected ElytraFlyingData createManipulator() {
        return new SpongeElytraFlyingData();
    }
}
