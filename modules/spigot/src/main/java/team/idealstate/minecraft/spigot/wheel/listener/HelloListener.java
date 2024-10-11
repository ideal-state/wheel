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

package team.idealstate.minecraft.spigot.wheel.listener;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import team.idealstate.minecraft.protocol.wheel.payload.Hello;
import team.idealstate.minecraft.spigot.wheel.packet.event.SpigotPacketReceivedEvent;

public enum HelloListener implements Listener {

    INSTANCE;

    @EventHandler
    public void onHello(SpigotPacketReceivedEvent event) {
        if (event.getPayload() instanceof Hello) {
            CommandSender sender = event.getPlayer();
            if (sender == null) {
                return;
            }
            sender.sendMessage(((Hello) event.getPayload()).getMessage());
        }
    }
}
