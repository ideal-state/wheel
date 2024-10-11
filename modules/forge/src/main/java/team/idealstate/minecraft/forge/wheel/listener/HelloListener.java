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

package team.idealstate.minecraft.forge.wheel.listener;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import team.idealstate.minecraft.forge.wheel.packet.event.ForgePacketReceivedEvent;
import team.idealstate.minecraft.forge.wheel.proxy.ClientProxy;
import team.idealstate.minecraft.protocol.wheel.api.exception.PacketException;
import team.idealstate.minecraft.protocol.wheel.payload.Hello;

public enum HelloListener {

    INSTANCE;

    private static final Logger log = LogManager.getLogger(HelloListener.class);

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onHello(ForgePacketReceivedEvent event) {
        if (event.getPayload() instanceof Hello) {
            Minecraft.getMinecraft().player.sendChatMessage(((Hello) event.getPayload()).getMessage());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onSayHello(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            return;
        }
        if (Keyboard.getEventKey() == Keyboard.KEY_J) {
            try {
                ClientProxy.channel().send(Hello.ID, new Hello("Hello from Forge!"));
            } catch (PacketException e) {
                log.throwing(e);
            }
        }
    }
}
