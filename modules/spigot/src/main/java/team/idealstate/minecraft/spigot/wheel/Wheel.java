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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import team.idealstate.minecraft.Tags;
import team.idealstate.minecraft.spigot.wheel.listener.MouseWheelEventListener;
import team.idealstate.minecraft.spigot.wheel.message.WheelMessageHandler;

public class Wheel extends JavaPlugin {
    private static Wheel instance;

    public Wheel() {
        instance = this;
    }

    public static Wheel getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        Messenger messenger = Bukkit.getMessenger();
        messenger.registerIncomingPluginChannel(
                this, Tags.ID, new WheelMessageHandler());
        messenger.registerOutgoingPluginChannel(
                this, Tags.ID);
        Bukkit.getPluginManager().registerEvents(
                new MouseWheelEventListener(), this);
    }
}
