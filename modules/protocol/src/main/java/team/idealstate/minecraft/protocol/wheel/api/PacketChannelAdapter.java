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

package team.idealstate.minecraft.protocol.wheel.api;

import org.jetbrains.annotations.NotNull;
import team.idealstate.minecraft.protocol.wheel.api.exception.PacketException;
import team.idealstate.minecraft.protocol.wheel.util.Entry;

@SuppressWarnings("unchecked")
public interface PacketChannelAdapter {


    void send(short id, @NotNull Object payload, @NotNull Entry<String, Object>... context) throws PacketException;

    void receive(byte @NotNull [] packet, @NotNull Entry<String, Object>... context) throws PacketException;

    @NotNull String getName();
}
