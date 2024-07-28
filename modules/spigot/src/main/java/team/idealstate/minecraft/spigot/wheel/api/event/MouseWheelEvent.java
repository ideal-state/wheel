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

package team.idealstate.minecraft.spigot.wheel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public final class MouseWheelEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private final int value;

    public MouseWheelEvent(Player who, int value) {
        super(who);
        this.value = value;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public int getValue() {
        return value;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
