/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.napile.thermit.taskdefs;

import org.napile.thermit.BuildException;
import org.napile.thermit.Project;
import org.napile.thermit.PropertyHelper;
import org.napile.thermit.taskdefs.condition.Condition;
import org.napile.thermit.taskdefs.condition.ConditionBase;

/**
 * Task to set a property conditionally using &lt;uptodate&gt;, &lt;available&gt;,
 * and many other supported conditions.
 * <p/>
 * <p>This task supports boolean logic as well as pluggable conditions
 * to decide, whether a property should be set.</p>
 * <p/>
 * <p>This task does not extend Task to take advantage of
 * ConditionBase.</p>
 *
 * @ant.task category="control"
 * @since Ant 1.4
 */
public class ConditionTask extends ConditionBase
{

	private String property = null;
	private Object value = "true";
	private Object alternative = null;

	/**
	 * Constructor, names this task "condition".
	 */
	public ConditionTask()
	{
		super("condition");
	}

	/**
	 * The name of the property to set. Required.
	 *
	 * @param p the name of the property
	 * @since Ant 1.4
	 */
	public void setProperty(String p)
	{
		property = p;
	}

	/**
	 * The value for the property to set, if condition evaluates to true.
	 * Defaults to "true".
	 *
	 * @param value the (Object) value of the property
	 * @since Ant 1.8
	 */
	public void setValue(Object value)
	{
		this.value = value;
	}

	/**
	 * The value for the property to set, if condition evaluates to true.
	 * Defaults to "true".
	 *
	 * @param v the value of the property
	 * @since Ant 1.4
	 */
	public void setValue(String v)
	{
		setValue((Object) v);
	}

	/**
	 * The value for the property to set, if condition evaluates to false.
	 * If this attribute is not specified, the property will not be set.
	 *
	 * @param alt the alternate value of the property.
	 * @since Ant 1.8
	 */
	public void setElse(Object alt)
	{
		alternative = alt;
	}

	/**
	 * The value for the property to set, if condition evaluates to false.
	 * If this attribute is not specified, the property will not be set.
	 *
	 * @param e the alternate value of the property.
	 * @since Ant 1.6.3
	 */
	public void setElse(String e)
	{
		setElse((Object) e);
	}

	/**
	 * See whether our nested condition holds and set the property.
	 *
	 * @throws BuildException if an error occurs
	 * @since Ant 1.4
	 */
	public void execute() throws BuildException
	{
		if(countConditions() > 1)
		{
			throw new BuildException("You must not nest more than one condition into <" + getTaskName() + ">");
		}
		if(countConditions() < 1)
		{
			throw new BuildException("You must nest a condition into <" + getTaskName() + ">");
		}
		if(property == null)
		{
			throw new BuildException("The property attribute is required.");
		}
		Condition c = (Condition) getConditions().nextElement();
		if(c.eval())
		{
			log("Condition true; setting " + property + " to " + value, Project.MSG_DEBUG);
			PropertyHelper.getPropertyHelper(getProject()).setNewProperty(property, value);
		}
		else if(alternative != null)
		{
			log("Condition false; setting " + property + " to " + alternative, Project.MSG_DEBUG);
			PropertyHelper.getPropertyHelper(getProject()).setNewProperty(property, alternative);
		}
		else
		{
			log("Condition false; not setting " + property, Project.MSG_DEBUG);
		}
	}
}
