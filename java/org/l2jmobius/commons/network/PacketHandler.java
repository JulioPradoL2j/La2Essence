package org.l2jmobius.commons.network;

@FunctionalInterface
public interface PacketHandler<T extends Client<Connection<T>>>
{
	ReadablePacket<T> handlePacket(ReadableBuffer var1, T var2);
}
