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

/*
 * Since the initial version of this file was deveolped on the clock on
 * an NSF grant I should say the following boilerplate:
 *
 * This material is based upon work supported by the National Science
 * Foundaton under Grant No. EIA-0196404. Any opinions, findings, and
 * conclusions or recommendations expressed in this material are those
 * of the author and do not necessarily reflect the views of the
 * National Science Foundation.
 */

package org.napile.thermit.taskdefs.optional.unix;

import java.io.File;

import org.napile.thermit.BuildException;
import org.napile.thermit.taskdefs.condition.Os;
import org.napile.thermit.types.Commandline;
import org.napile.thermit.types.FileSet;

/**
 * @ant.task category="filesystem"
 * @since Ant 1.6
 */

public abstract class AbstractAccessTask extends org.napile.thermit.taskdefs.ExecuteOn
{

	/**
	 * Chmod task for setting file and directory permissions.
	 */
	public AbstractAccessTask()
	{
		super.setParallel(true);
		super.setSkipEmptyFilesets(true);
	}

	/**
	 * Set the file which should have its access attributes modified.
	 *
	 * @param src the file to modify
	 */
	public void setFile(File src)
	{
		FileSet fs = new FileSet();
		fs.setFile(src);
		addFileset(fs);
	}

	/**
	 * Prevent the user from specifying a different command.
	 *
	 * @param cmdl A user supplied command line that we won't accept.
	 * @ant.attribute ignore="true"
	 */
	public void setCommand(Commandline cmdl)
	{
		throw new BuildException(getTaskType() + " doesn\'t support the command attribute", getLocation());
	}

	/**
	 * Prevent the skipping of empty filesets
	 *
	 * @param skip A user supplied boolean we won't accept.
	 * @ant.attribute ignore="true"
	 */
	public void setSkipEmptyFilesets(boolean skip)
	{
		throw new BuildException(getTaskType() + " doesn\'t support the " + "skipemptyfileset attribute", getLocation());
	}

	/**
	 * Prevent the use of the addsourcefile attribute.
	 *
	 * @param b A user supplied boolean we won't accept.
	 * @ant.attribute ignore="true"
	 */
	public void setAddsourcefile(boolean b)
	{
		throw new BuildException(getTaskType() + " doesn\'t support the addsourcefile attribute", getLocation());
	}

	/**
	 * Automatically approve Unix OS's.
	 *
	 * @return true if a valid OS, for unix this is always true, otherwise
	 *         use the superclasses' test (user set).
	 */
	protected boolean isValidOs()
	{
		return getOs() == null && getOsFamily() == null ? Os.isFamily(Os.FAMILY_UNIX) : super.isValidOs();
	}
}
