/*
 *     <one line to give the program's name and a brief idea of what it does.>
 *     Copyright (C) 2024/01/19 ideal-state
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package team.idealstate.minecraft.forge.wheel.mod.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import team.idealstate.minecraft.forge.wheel.common.Tags;
import team.idealstate.minecraft.forge.wheel.mod.message.WheelEventHandler;

/**
 * <p>ClientProxy</p>
 *
 * <p>Created on 2024/1/15 19:51</p>
 *
 * @author ketikai
 * @since 1.0.0
 */
public class ClientProxy extends CommonProxy {

    private static FMLEventChannel channel;

    public static FMLEventChannel getChannel() {
        return channel;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(Tags.MOD_ID);
        MinecraftForge.EVENT_BUS.register(new WheelEventHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }
}
