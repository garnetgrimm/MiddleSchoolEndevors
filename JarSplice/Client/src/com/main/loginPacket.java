package com.main;

import org.gnet.packet.Packet;

public class loginPacket extends Packet {

	public loginPacket(String name) {
		super("loginPacket", 1);
		addEntry("username", name);		
		
	}

}
