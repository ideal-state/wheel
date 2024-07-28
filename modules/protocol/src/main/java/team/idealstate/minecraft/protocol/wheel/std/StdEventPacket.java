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

package team.idealstate.minecraft.protocol.wheel.std;

import team.idealstate.minecraft.protocol.wheel.api.EventPacket;
import team.idealstate.minecraft.protocol.wheel.api.EventPacketDeserializer;
import team.idealstate.minecraft.protocol.wheel.api.EventPacketSerializer;

public enum StdEventPacket {

    MOUSE_WHEEL(
            (short) 0,
            WheelEventPacket.class,
            new WheelEventPacket.Serializer(),
            new WheelEventPacket.Deserializer()
    );

    private final short id;
    private final Class<? extends EventPacket> type;
    private final EventPacketSerializer<?> serializer;
    private final EventPacketDeserializer<?> deserializer;


    <T extends EventPacket> StdEventPacket(short id, Class<T> type, EventPacketSerializer<T> serializer, EventPacketDeserializer<T> deserializer) {
        this.id = id;
        this.type = type;
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    public short getId() {
        return id;
    }

    public Class<? extends EventPacket> getType() {
        return type;
    }

    public EventPacketSerializer<?> getSerializer() {
        return serializer;
    }

    public EventPacketDeserializer<?> getDeserializer() {
        return deserializer;
    }
}
