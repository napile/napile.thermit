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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.napile.thermit.BuildException;
import org.napile.thermit.Project;
import org.napile.thermit.types.ZipFileSet;
import org.napile.thermit.util.FileUtils;
import org.napile.thermit.util.zip.ZipOutputStream;

/**
 * Creates a JAR archive.
 *
 * @ant.task category="packaging"
 * @since Ant 1.1
 */
public class NZip extends Zip
{
	public static class VarValue
	{
		private String name;

		private String value;

		public VarValue()
		{}

		public VarValue(String name, String value)
		{
			this.name = name;
			this.value = value;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public void setValue(String value)
		{
			this.value = value;
		}
	}

	public static final String MODULE_FILE_NAME = "@module@.xml";

	private File moduleDir;

	private List<VarValue> varValues = new ArrayList<VarValue>();

	public NZip()
	{
		super();
		archiveType = "nzip";
		emptyBehavior = "create";
		setEncoding("UTF8");

		varValues.add(new VarValue("build-by", System.getProperty("user.name")));
	}

	public void addVarValue(VarValue varValue)
	{
		varValues.add(varValue);
	}

	/**
	 * Not used for jar files.
	 *
	 * @param we not used
	 * @ant.attribute ignore="true"
	 */
	@Override
	public void setWhenempty(WhenEmpty we)
	{
		log("NZips are never empty, they contain at least a module file", Project.MSG_WARN);
	}

	/**
	 * Indicates if a jar file should be created when it would only contain a
	 * manifest file.
	 * Possible values are: <code>fail</code> (throw an exception
	 * and halt the build); <code>skip</code> (do not create
	 * any archive, but issue a warning); <code>create</code>
	 * (make an archive with only a manifest file).
	 * Default is <code>create</code>;
	 *
	 * @param we a <code>WhenEmpty</code> enumerated value
	 */
	public void setWhenmanifestonly(WhenEmpty we)
	{
		emptyBehavior = we.getValue();
	}

	/**
	 * Set the destination file.
	 *
	 * @param jarFile the destination file
	 * @deprecated since 1.5.x.
	 *             Use setDestFile(File) instead.
	 */
	public void setJarfile(File jarFile)
	{
		setDestFile(jarFile);
	}

	/**
	 * Set module dir
	 * @param file
	 */
	public void setModuleDir(File file)
	{
		this.moduleDir = file;
	}

	/**
	 * Initialize the zip output stream.
	 *
	 * @param zOut the zip output stream
	 * @throws IOException    on I/O errors
	 * @throws BuildException on other errors
	 */
	@Override
	protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException
	{
		if(!skipWriting)
			writeModule(zOut);
	}

	private void writeModule(ZipOutputStream zipOutputStream)
	{
		if(moduleDir == null)
		{
			log("'moduledir' Cant be empty", Project.MSG_ERR);
			return;
		}

		File moduleFile = new File(moduleDir, MODULE_FILE_NAME);
		if(!moduleFile.exists())
		{
			log("Module file not found", Project.MSG_ERR);
			return;
		}

		try
		{
			String str = FileUtils.readFully(new FileReader(moduleFile));

			for(VarValue varValue : varValues)
				str = str.replace("@" + varValue.name + "@", varValue.value);

			ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());

			super.zipFile(bais, zipOutputStream, MODULE_FILE_NAME, System.currentTimeMillis(), null, ZipFileSet.DEFAULT_FILE_MODE);
		}
		catch(IOException e)
		{
			log(e, Project.MSG_ERR);
		}
	}

	/**
	 * Make sure we don't think we already have a MANIFEST next time this task
	 * gets executed.
	 *
	 * @see Zip#cleanUp
	 */
	@Override
	protected void cleanUp()
	{
		super.cleanUp();

		// we want to save this info if we are going to make another pass
		if(!doubleFilePass || !skipWriting)
		{
			//TODO [VISTALL] null it
		}
	}

	/**
	 * reset to default values.
	 *
	 * @see Zip#reset
	 * @since 1.44, Ant 1.5
	 */
	@Override
	public void reset()
	{
		super.reset();
		emptyBehavior = "create";
	}
}
