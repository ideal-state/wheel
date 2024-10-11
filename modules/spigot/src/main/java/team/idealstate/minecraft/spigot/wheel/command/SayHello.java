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

package team.idealstate.minecraft.spigot.wheel.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import team.idealstate.minecraft.protocol.wheel.api.exception.PacketException;
import team.idealstate.minecraft.protocol.wheel.payload.Hello;
import team.idealstate.minecraft.spigot.wheel.Wheel;

public enum SayHello implements CommandExecutor {

    INSTANCE;

    private static final Logger log = LogManager.getLogger(SayHello.class);
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ("hi".equalsIgnoreCase(s)) {
            try {
                Wheel.channel().send(Hello.ID, new Hello("Hi from Spigot!"));
            } catch (PacketException e) {
                log.throwing(e);
            }
            return true;
        }
        return false;
    }
}
