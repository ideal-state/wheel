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

package team.idealstate.minecraft.forge.wheel.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import team.idealstate.minecraft.Tags;
import team.idealstate.minecraft.forge.wheel.mod.proxy.ClientProxy;
import team.idealstate.minecraft.protocol.wheel.spi.EventPacketSender;

public class ForgeEventPacketSender implements EventPacketSender {

    @Override
    public void sendPacket(byte[] data) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(data);
        PacketBuffer payload = new PacketBuffer(byteBuf);
        FMLProxyPacket packet = new FMLProxyPacket(payload, Tags.ID);
        ClientProxy.getChannel().sendToServer(packet);
    }
}
