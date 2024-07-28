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

package team.idealstate.minecraft.spigot.wheel.message;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import team.idealstate.minecraft.protocol.wheel.EventPacketHandler;
import team.idealstate.minecraft.protocol.wheel.std.StdEventPacket;
import team.idealstate.minecraft.protocol.wheel.std.WheelEventPacket;
import team.idealstate.minecraft.spigot.wheel.api.event.MouseWheelEvent;

import java.util.HashMap;

public final class WheelMessageHandler implements PluginMessageListener {

    public WheelMessageHandler() {
        EventPacketHandler.registerListener(
                StdEventPacket.MOUSE_WHEEL.getType(),
                (packet, context) -> {
                    WheelEventPacket that = (WheelEventPacket) packet;
                    Bukkit.getPluginManager().callEvent(
                            new MouseWheelEvent(
                                    (Player) context.get("player"),
                                    that.getValue()
                            )
                    );
                }
        );
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if ("wheel".equals(channel)) {
            HashMap<String, Object> context = new HashMap<>();
            context.put("player", player);
            EventPacketHandler.receivePacket(message, context);
        }
    }
}
