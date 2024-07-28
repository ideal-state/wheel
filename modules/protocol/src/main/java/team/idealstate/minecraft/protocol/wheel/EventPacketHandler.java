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

package team.idealstate.minecraft.protocol.wheel;

import team.idealstate.minecraft.protocol.wheel.api.*;
import team.idealstate.minecraft.protocol.wheel.std.StdEventPacket;

import java.nio.ByteBuffer;
import java.util.*;

public abstract class EventPacketHandler {

    private static final EventPacketSender SENDER;
    private static final Map<Short, EventPacketSerializer<?>> SERIALIZER_MAP = new HashMap<>(8, 0.6f);
    private static final Map<Short, EventPacketDeserializer<?>> DESERIALIZER_MAP = new HashMap<>(8, 0.6f);
    private static final List<TypedEventPacketListener<?>> LISTENERS = new LinkedList<>();

    static {
        ServiceLoader<EventPacketSender> loader = ServiceLoader.load(EventPacketSender.class);
        Iterator<EventPacketSender> iterator = loader.iterator();
        if (iterator.hasNext()) {
            SENDER = iterator.next();
        } else {
            throw new IllegalStateException("No EventPacketSender found");
        }
        registerSerializer(
                StdEventPacket.MOUSE_WHEEL.getId(),
                StdEventPacket.MOUSE_WHEEL.getSerializer()
        );
        registerDeserializer(
                StdEventPacket.MOUSE_WHEEL.getId(),
                StdEventPacket.MOUSE_WHEEL.getDeserializer()
        );
    }

    public static void registerSerializer(short eventId, EventPacketSerializer<?> serializer) {
        SERIALIZER_MAP.put(eventId, serializer);
    }

    public static EventPacketSerializer<?> unregisterSerializer(short eventId) {
        return SERIALIZER_MAP.remove(eventId);
    }

    public static void registerDeserializer(short eventId, EventPacketDeserializer<?> deserializer) {
        DESERIALIZER_MAP.put(eventId, deserializer);
    }

    public static EventPacketDeserializer<?> unregisterDeserializer(short eventId) {
        return DESERIALIZER_MAP.remove(eventId);
    }

    public static <T extends EventPacket> void registerListener(
            Class<T> packetType,
            EventPacketListener<T> listener
    ) {
        LISTENERS.add(new TypedEventPacketListener<>(packetType, listener));
    }

    public static boolean unregisterListener(EventPacketListener<?> listener) {
        return LISTENERS.removeIf(typedListener -> typedListener.listener.equals(listener));
    }

    public static boolean unregisterListener(Class<? extends EventPacket> listener) {
        return LISTENERS.removeIf(typedListener -> typedListener.packetType.equals(listener));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sendPacket(EventPacket packet) {
        EventPacketSerializer serializer = SERIALIZER_MAP.get(packet.getEventId());
        if (serializer == null) {
            throw new UnsupportedOperationException("No serializer found for event id " + packet.getEventId());
        }
        byte[] serialized = serializer.serialize(packet);
        ByteBuffer buffer = ByteBuffer.allocate(serialized.length + 2);
        buffer.putShort(packet.getEventId());
        buffer.put(serialized);
        buffer.flip();
        SENDER.sendPacket(buffer.array());
    }

    public static void receivePacket(byte[] data) {
        receivePacket(data, Collections.emptyMap());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void receivePacket(byte[] data, Map<String, Object> context) {
        if (LISTENERS.isEmpty()) {
            return;
        }
        ByteBuffer wrapped = ByteBuffer.wrap(data);
        wrapped.flip();
        short eventId = wrapped.getShort();
        EventPacketDeserializer<?> deserializer = DESERIALIZER_MAP.get(eventId);
        if (deserializer == null) {
            throw new UnsupportedOperationException("No deserializer found for event id " + eventId);
        }
        byte[] dst = new byte[wrapped.remaining()];
        wrapped.get(dst);
        EventPacket packet = deserializer.deserialize(dst);
        Class<? extends EventPacket> packetType = packet.getClass();
        for (TypedEventPacketListener listener : LISTENERS) {
            if (packetType.equals(listener.getPacketType())) {
                listener.onPacketReceived(packet, context);
            }
        }
    }

    private static final class TypedEventPacketListener<T extends EventPacket> implements EventPacketListener<T> {

        private final Class<T> packetType;
        private final EventPacketListener<T> listener;

        private TypedEventPacketListener(Class<T> packetType, EventPacketListener<T> listener) {
            this.packetType = packetType;
            this.listener = listener;
        }

        @Override
        public void onPacketReceived(T packet, Map<String, Object> context) {
            listener.onPacketReceived(packet, context);
        }

        public Class<T> getPacketType() {
            return packetType;
        }
    }
}
