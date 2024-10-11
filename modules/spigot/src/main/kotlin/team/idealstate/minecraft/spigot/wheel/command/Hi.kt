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

package team.idealstate.minecraft.spigot.wheel.command

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import team.idealstate.minecraft.protocol.wheel.api.exception.PacketException
import team.idealstate.minecraft.protocol.wheel.payload.Hello
import team.idealstate.minecraft.spigot.wheel.Wheel

object Hi : CommandExecutor {

    const val NAME = "hi"
    private val log: Logger = LogManager.getLogger(Hi::class.java)

    override fun onCommand(
        commandSender: CommandSender?,
        command: Command?,
        s: String?,
        strings: Array<String?>?
    ): Boolean {
        if (NAME.equals(s, ignoreCase = true)) {
            try {
                Wheel.channel().send(Hello.ID, Hello("Hi from Spigot!"))
            } catch (e: PacketException) {
                log.throwing(e)
            }
            return true
        }
        return false
    }
}