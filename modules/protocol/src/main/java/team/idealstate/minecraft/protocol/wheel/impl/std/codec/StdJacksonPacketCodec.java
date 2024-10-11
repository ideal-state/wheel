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

package team.idealstate.minecraft.protocol.wheel.impl.std.codec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.idealstate.minecraft.protocol.wheel.api.PacketCodec;
import team.idealstate.minecraft.protocol.wheel.api.exception.PacketException;

import java.io.IOException;
import java.util.Objects;

public class StdJacksonPacketCodec<T> implements PacketCodec {

    protected final Class<T> packetType;
    protected final ObjectMapper mapper;

    public StdJacksonPacketCodec(@NotNull Class<T> packetType) {
        this.packetType = Objects.requireNonNull(packetType);
        this.mapper = new ObjectMapper();
    }

    @Override
    public byte @NotNull [] encode(@NotNull Object payload) throws PacketException {
        try {
            return mapper.writeValueAsBytes(Objects.requireNonNull(payload));
        } catch (JsonProcessingException | NullPointerException e) {
            throw new PacketException(e);
        }
    }

    @Override
    public @Nullable Object decode(byte @NotNull [] payload) throws PacketException {
        try {
            return mapper.readValue(Objects.requireNonNull(payload), packetType);
        } catch (IOException | NullPointerException e) {
            throw new PacketException(e);
        }
    }
}
