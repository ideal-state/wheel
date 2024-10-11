/*
 *     Copyright (C) 2024 ideal-state
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

package team.idealstate.minecraft.forge.wheel.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.jetbrains.annotations.NotNull;
import team.idealstate.minecraft.forge.wheel.listener.HelloListener;
import team.idealstate.minecraft.forge.wheel.packet.ForgePacketChannel;
import team.idealstate.minecraft.protocol.wheel.impl.std.codec.StdJacksonPacketCodec;
import team.idealstate.minecraft.protocol.wheel.payload.Hello;
import team.idealstate.minecraft.protocol.wheel.util.Entry;
import team.idealstate.minecraft.tags.wheel.Tags;

import java.util.Objects;

public class ClientProxy extends CommonProxy {

    private static volatile ForgePacketChannel channel;

    @NotNull
    public static ForgePacketChannel channel() {
        return Objects.requireNonNull(channel);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(HelloListener.INSTANCE);
        channel = new ForgePacketChannel(Tags.ID,
                Entry.of(Hello.ID, new StdJacksonPacketCodec<>(Hello.class)));
    }
}
