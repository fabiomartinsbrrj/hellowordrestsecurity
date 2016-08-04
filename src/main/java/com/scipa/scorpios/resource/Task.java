/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scipa.scorpios.resource;

import java.io.Serializable;
import java.io.StringReader;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
@XmlRootElement(name = "task")
public class Task implements Serializable {

	private Long id;

	private String owner;

	private String title;

	private String naoLeva;

	private SubTask subTask;

	public Task() {
	}

	public Task(final String title) {
		super();
		this.title = title;
	}

	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@XmlAttribute
	public String getOwner() {
		return owner;
	}

	public void setOwner(final String owner) {
		this.owner = owner;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public static Task stringToTask(final String content) {
		return JAXB.unmarshal(new StringReader(content), Task.class);
	}

	@XmlTransient
	@JsonIgnore
	public String getNaoLeva() {
		return naoLeva;
	}

	public void setNaoLeva(final String naoLeva) {
		this.naoLeva = naoLeva;
	}

	@XmlElement
	public SubTask getSubTask() {
		return subTask;
	}

	public void setSubTask(final SubTask subTask) {
		this.subTask = subTask;
	}
}
