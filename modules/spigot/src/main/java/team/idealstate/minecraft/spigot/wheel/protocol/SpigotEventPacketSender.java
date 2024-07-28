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

package team.idealstate.minecraft.spigot.wheel.protocol;

import org.bukkit.Bukkit;
import team.idealstate.minecraft.protocol.wheel.api.EventPacketSender;
import team.idealstate.minecraft.spigot.wheel.Wheel;

public class SpigotEventPacketSender implements EventPacketSender {
    @Override
    public void sendPacket(byte[] data) {
        Bukkit.getServer().sendPluginMessage(
                Wheel.getInstance(),
                Wheel.ID,
                data
        );
    }
}
