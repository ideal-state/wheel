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

package team.idealstate.minecraft.forge.wheel.mod.message;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import team.idealstate.minecraft.protocol.wheel.EventPacketHandler;
import team.idealstate.minecraft.protocol.wheel.std.WheelEventPacket;

/**
 * <p>WheelMessageHandler</p>
 *
 * <p>Created on 2024/1/15 11:21</p>
 *
 * @author ketikai
 * @since 1.0.0
 */
public class WheelEventHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        int eventDWheel = Mouse.getEventDWheel();
        if (eventDWheel != 0) {
            EventPacketHandler.sendPacket(new WheelEventPacket(eventDWheel));
        }
    }

}
