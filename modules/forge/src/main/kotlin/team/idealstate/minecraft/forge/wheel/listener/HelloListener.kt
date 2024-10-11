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

package team.idealstate.minecraft.forge.wheel.listener

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.lwjgl.input.Keyboard
import team.idealstate.minecraft.forge.wheel.packet.event.ForgePacketReceivedEvent
import team.idealstate.minecraft.forge.wheel.proxy.ClientProxy
import team.idealstate.minecraft.protocol.wheel.api.exception.PacketException
import team.idealstate.minecraft.protocol.wheel.payload.Hello

object HelloListener {

    private val log: Logger = LogManager.getLogger(
        HelloListener::class.java
    )

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun onHello(event: ForgePacketReceivedEvent) {
        if (event.payload is Hello) {
            Minecraft.getMinecraft().player.sendChatMessage((event.payload as Hello).message)
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    @Suppress("UNUSED_PARAMETER")
    fun onSayHello(event: KeyInputEvent?) {
        if (Keyboard.getEventKeyState()) {
            return
        }
        if (Keyboard.getEventKey() == Keyboard.KEY_J) {
            try {
                ClientProxy.channel().send(Hello.ID, Hello("Hello from Forge!"))
            } catch (e: PacketException) {
                log.throwing(e)
            }
        }
    }
}