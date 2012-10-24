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
package org.napile.thermit.types;

import org.napile.thermit.Project;
import org.napile.thermit.ProjectHelper;
import org.napile.thermit.helper.ProjectHelper2;
import org.napile.thermit.Task;
import org.napile.thermit.UnknownElement;
import org.napile.thermit.Target;
import org.napile.thermit.helper.ProjectHelperImpl;

import java.util.ArrayList;
import java.util.List;


/**
 * Description is used to provide a project-wide description element
 * (that is, a description that applies to a buildfile as a whole).
 * If present, the &lt;description&gt; element is printed out before the
 * target descriptions.
 * <p/>
 * Description has no attributes, only text.  There can only be one
 * project description per project.  A second description element will
 * overwrite the first.
 *
 * @ant.datatype ignore="true"
 */
public class Description extends DataType
{

	/**
	 * Adds descriptive text to the project.
	 *
	 * @param text the descriptive text
	 */
	public void addText(String text)
	{

		ProjectHelper ph = getProject().getReference(ProjectHelper.PROJECTHELPER_REFERENCE);
		if(!(ph instanceof ProjectHelperImpl))
		{
			// New behavior for delayed task creation. Description
			// will be evaluated in Project.getDescription()
			return;
		}
		String currentDescription = getProject().getDescription();
		if(currentDescription == null)
		{
			getProject().setDescription(text);
		}
		else
		{
			getProject().setDescription(currentDescription + text);
		}
	}

	/**
	 * Return the descriptions from all the targets of
	 * a project.
	 *
	 * @param project the project to get the descriptions for.
	 * @return a string containing the concatenated descriptions of
	 *         the targets.
	 */
	public static String getDescription(Project project)
	{
		List<Target> targets = project.getReference(ProjectHelper2.REFID_TARGETS);
		if(targets == null)
		{
			return null;
		}
		StringBuilder description = new StringBuilder();
		for(Target t : targets)
		{
			concatDescriptions(project, t, description);
		}
		return description.toString();
	}

	private static void concatDescriptions(Project project, Target t, StringBuilder description)
	{
		if(t == null)
		{
			return;
		}
		for(Task task : findElementInTarget(project, t, "description"))
		{
			if(!(task instanceof UnknownElement))
			{
				continue;
			}
			UnknownElement ue = ((UnknownElement) task);
			String descComp = ue.getWrapper().getText().toString();
			if(descComp != null)
			{
				description.append(project.replaceProperties(descComp));
			}
		}
	}

	private static List<Task> findElementInTarget(Project project, Target t, String name)
	{
		final List<Task> elems = new ArrayList<Task>();
		for(Task task : t.getTasks())
		{
			if(name.equals(task.getTaskName()))
			{
				elems.add(task);
			}
		}
		return elems;
	}

}
