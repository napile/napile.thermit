package org.napile.thermit.taskdefs.launcher;

import java.io.File;
import java.io.IOException;

import org.napile.thermit.MagicNames;
import org.napile.thermit.Project;

/**
 * A command launcher that uses an auxiliary script to launch commands
 * in directories other than the current working directory.
 */
public class ScriptCommandLauncher extends CommandLauncherProxy
{
	private final String myScript;

	public ScriptCommandLauncher(String script, CommandLauncher launcher)
	{
		super(launcher);
		myScript = script;
	}

	/**
	 * Launches the given command in a new process, in the given
	 * working directory.
	 *
	 * @param project    the Ant project.
	 * @param cmd        the command line to execute as an array of strings.
	 * @param env        the environment to set as an array of strings.
	 * @param workingDir working directory where the command should run.
	 * @return the created Process.
	 * @throws IOException forwarded from the exec method of the command launcher.
	 */
	@Override
	public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException
	{
		if(project == null)
		{
			if(workingDir == null)
			{
				return exec(project, cmd, env);
			}
			throw new IOException("Cannot locate antRun script: " + "No project provided");
		}
		// Locate the auxiliary script
		String antHome = project.getProperty(MagicNames.ANT_HOME);
		if(antHome == null)
		{
			throw new IOException("Cannot locate antRun script: " + "Property '" + MagicNames.ANT_HOME + "' not found");
		}
		String antRun = FILE_UTILS.resolveFile(project.getBaseDir(), antHome + File.separator + myScript).toString();

		// Build the command
		File commandDir = workingDir;
		if(workingDir == null)
		{
			commandDir = project.getBaseDir();
		}
		String[] newcmd = new String[cmd.length + 2];
		newcmd[0] = antRun;
		newcmd[1] = commandDir.getAbsolutePath();
		System.arraycopy(cmd, 0, newcmd, 2, cmd.length);

		return exec(project, newcmd, env);
	}

}