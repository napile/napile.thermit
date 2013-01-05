/*
 * Copyright 2010-2012 napile.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.napile.thermit.taskdefs;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.napile.thermit.BuildException;
import org.napile.thermit.types.Path;

/**
 * @author VISTALL
 * @date 18:30/17.11.12
 */
public class Napilec extends MatchingTask
{
	private Path src;
	private Path out;

	public Path createSrc()
	{
		if(src == null)
			src = new Path(getProject());
		return src.createPath();
	}

	public Path createOut()
	{
		if(out == null)
			out = new Path(getProject());
		return out.createPath();
	}

	public void setSrcdir(Path srcDir)
	{
		if(src == null)
			src = srcDir;
		else
			src.append(srcDir);
	}

	public void setOutDir(Path outDir)
	{
		if(out == null)
			out = outDir;
		else
			out.append(outDir);
	}

	@Override
	public void execute() throws BuildException
	{
		List<String> arguments = new ArrayList<String>();
		arguments.add("-output");
		arguments.add(out.toString());
		arguments.add(src.toString());

		try
		{
			Class<?> compilerProcessorClazz = Class.forName("org.napile.compiler.common.CompilerProcessor");
			Method execMethod = compilerProcessorClazz.getMethod("exec", PrintStream.class, String[].class);
			//
			Class<?> exitCode = Class.forName("org.napile.compiler.common.ExitCode");
			Object okCode = exitCode.getField("OK").get(null);

			Object compilerProcessor = compilerProcessorClazz.newInstance();

			Object returnVal = execMethod.invoke(compilerProcessor, System.err, arguments.toArray(new String[arguments.size()]));
			if(returnVal != okCode)
				throw new BuildException("Compilation is failed. Check error messages");
		}
		catch(Throwable ex)
		{
			if(ex instanceof BuildException)
			{
				throw (BuildException) ex;
			}
			else
			{
				//ex.printStackTrace();
				throw new BuildException("napilec: " + ex.getMessage(), ex, getLocation());
			}
		}
	}
}

