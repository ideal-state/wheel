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

package team.idealstate.minecraft.spigot.wheel;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import team.idealstate.minecraft.protocol.wheel.impl.std.codec.StdJacksonPacketCodec;
import team.idealstate.minecraft.protocol.wheel.payload.Hello;
import team.idealstate.minecraft.protocol.wheel.util.Entry;
import team.idealstate.minecraft.spigot.wheel.command.Hi;
import team.idealstate.minecraft.spigot.wheel.listener.HelloListener;
import team.idealstate.minecraft.spigot.wheel.packet.SpigotPacketChannel;
import team.idealstate.minecraft.tags.wheel.Tags;

import java.util.Objects;

public class Wheel extends JavaPlugin {

    private static volatile SpigotPacketChannel channel;

    @NotNull
    public static SpigotPacketChannel channel() {
        return Objects.requireNonNull(channel);
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(HelloListener.INSTANCE, this);
        channel = new SpigotPacketChannel(this, Tags.ID,
                Entry.of(Hello.ID, new StdJacksonPacketCodec<>(Hello.class))
                );
        getCommand(Hi.NAME).setExecutor(Hi.INSTANCE);
    }
}
