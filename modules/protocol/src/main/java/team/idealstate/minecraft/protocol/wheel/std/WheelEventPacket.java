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

import java.nio.ByteBuffer;

public final class WheelEventPacket implements EventPacket {

    private final int value;

    public WheelEventPacket(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public short getEventId() {
        return StdEventPacket.MOUSE_WHEEL.getId();
    }

    static final class Deserializer implements EventPacketDeserializer<WheelEventPacket> {

        @Override
        public WheelEventPacket deserialize(byte[] data) {
            if (data == null || data.length != 4) {
                throw new IllegalArgumentException("Invalid data");
            }
            ByteBuffer buffer = ByteBuffer.allocate(4).put(data);
            buffer.flip();
            return new WheelEventPacket(buffer.getInt());
        }
    }

    static final class Serializer implements EventPacketSerializer<WheelEventPacket> {
        @Override
        public byte[] serialize(WheelEventPacket packet) {
            ByteBuffer buffer = ByteBuffer.allocate(4).putInt(packet.getValue());
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            return data;
        }
    }
}
