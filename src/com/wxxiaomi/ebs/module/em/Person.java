package com.wxxiaomi.ebs.module.em;

import java.util.Map;

import com.fasterxml.jackson.databind.node.ContainerNode;
import com.wxxiaomi.ebs.module.em.comm.body.MessageBody;

public class Person extends MessageBody{
	public Person(String targetType, String[] targets, String from,
			Map<String, String> ext) {
		super(targetType, targets, from, ext);
		// TODO Auto-generated constructor stub
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ContainerNode<?> getBody() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
