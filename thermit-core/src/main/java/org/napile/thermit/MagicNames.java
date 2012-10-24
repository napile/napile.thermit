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
package org.napile.thermit;

import org.napile.thermit.launch.Launcher;

/**
 * Magic names used within Ant.
 * <p/>
 * Not all magic names are here yet.
 *
 * @since Ant 1.6
 */
public final class MagicNames
{

	private MagicNames()
	{
	}

	/**
	 * prefix for antlib URIs:
	 * {@value}
	 */
	public static final String ANTLIB_PREFIX = "antlib:";

	/**
	 * Ant version property.
	 * Value: {@value}
	 */
	public static final String ANT_VERSION = "thermit.version";

	/**
	 * System classpath policy.
	 * Value: {@value}
	 */
	public static final String BUILD_SYSCLASSPATH = "build.sysclasspath";

	/**
	 * The name of the script repository used by the script repo task.
	 * Value {@value}
	 */
	public static final String SCRIPT_REPOSITORY = "org.napile.thermit.scriptrepo";

	/**
	 * The name of the reference to the System Class Loader.
	 * Value {@value}
	 */
	public static final String SYSTEM_LOADER_REF = "thermit.coreLoader";

	/**
	 * Name of the property which can provide an override of the repository dir.
	 * for the libraries task
	 * Value {@value}
	 */
	public static final String REPOSITORY_DIR_PROPERTY = "thermit.maven.repository.dir";

	/**
	 * Name of the property which can provide an override of the repository URL.
	 * for the libraries task
	 * Value {@value}
	 */
	public static final String REPOSITORY_URL_PROPERTY = "thermit.maven.repository.url";

	/**
	 * name of the resource that taskdefs are stored under.
	 * Value: {@value}
	 */
	public static final String TASKDEF_PROPERTIES_RESOURCE = "/org/napile/thermit/taskdefs/defaults.properties";

	/**
	 * name of the resource that typedefs are stored under.
	 * Value: {@value}
	 */
	public static final String TYPEDEFS_PROPERTIES_RESOURCE = "/org/napile/thermit/types/defaults.properties";

	/**
	 * Reference to the current Ant executor.
	 * Value: {@value}
	 */
	public static final String ANT_EXECUTOR_REFERENCE = "thermit.executor";

	/**
	 * Property defining the classname of an executor.
	 * Value: {@value}
	 */
	public static final String ANT_EXECUTOR_CLASSNAME = "thermit.executor.class";

	/**
	 * property name for basedir of the project.
	 * Value: {@value}
	 */
	public static final String PROJECT_BASEDIR = "basedir";

	/**
	 * property for thermit file name.
	 * Value: {@value}
	 */
	public static final String ANT_FILE = "thermit.file";

	/**
	 * property for type of thermit build file (either file or url)
	 * Value: {@value}
	 *
	 * @since Ant 1.8.0
	 */
	public static final String ANT_FILE_TYPE = "thermit.file.type";

	/**
	 * thermit build file of type file
	 * Value: {@value}
	 *
	 * @since Ant 1.8.0
	 */
	public static final String ANT_FILE_TYPE_FILE = "file";

	/**
	 * thermit build file of type url
	 * Value: {@value}
	 *
	 * @since Ant 1.8.0
	 */
	public static final String ANT_FILE_TYPE_URL = "url";

	/**
	 * Property used to store the java version thermit is running in.
	 * Value: {@value}
	 *
	 * @since Ant 1.7
	 */
	public static final String ANT_JAVA_VERSION = "thermit.java.version";

	/**
	 * Property used to store the location of thermit.
	 * Value: {@value}
	 *
	 * @since Ant 1.7
	 */
	public static final String ANT_HOME = Launcher.ANTHOME_PROPERTY;

	/**
	 * Property used to store the location of the thermit library (typically the thermit.jar file.)
	 * Value: {@value}
	 *
	 * @since Ant 1.7
	 */
	public static final String ANT_LIB = "thermit.core.lib";

	/**
	 * property for regular expression implementation.
	 * Value: {@value}
	 */
	public static final String REGEXP_IMPL = "thermit.regexp.regexpimpl";

	/**
	 * property that provides the default value for javac's and
	 * javadoc's source attribute.
	 * Value: {@value}
	 *
	 * @since Ant 1.7
	 */
	public static final String BUILD_JAVAC_SOURCE = "thermit.build.javac.source";

	/**
	 * property that provides the default value for javac's target attribute.
	 * Value: {@value}
	 *
	 * @since Ant 1.7
	 */
	public static final String BUILD_JAVAC_TARGET = "thermit.build.javac.target";

	/**
	 * Name of the magic property that controls classloader reuse.
	 * Value: {@value}
	 *
	 * @since Ant 1.4.
	 */
	public static final String REFID_CLASSPATH_REUSE_LOADER = "thermit.reuse.loader";

	/**
	 * Prefix used to store classloader references.
	 * Value: {@value}
	 */
	public static final String REFID_CLASSPATH_LOADER_PREFIX = "thermit.loader.";

	/**
	 * Reference used to store the property helper.
	 * Value: {@value}
	 */
	public static final String REFID_PROPERTY_HELPER = "thermit.PropertyHelper";

	/**
	 * Reference used to store the local properties.
	 * Value: {@value}
	 */
	public static final String REFID_LOCAL_PROPERTIES = "thermit.LocalProperties";

	/**
	 * Name of JVM system property which provides the name of the ProjectHelper class to use.
	 * Value: {@value}
	 */
	public static final String PROJECT_HELPER_CLASS = "org.napile.thermit.ProjectHelper";

	/**
	 * The service identifier in jars which provide ProjectHelper implementations.
	 * Value: {@value}
	 */
	public static final String PROJECT_HELPER_SERVICE = "META-INF/services/org.napile.thermit.ProjectHelper";

	/**
	 * Name of ProjectHelper reference that we add to a project.
	 * Value: {@value}
	 */
	public static final String REFID_PROJECT_HELPER = "thermit.projectHelper";

	/**
	 * Name of the property holding the name of the currently
	 * executing project, if one has been specified.
	 * <p/>
	 * Value: {@value}
	 *
	 * @since Ant 1.8.0
	 */
	public static final String PROJECT_NAME = "thermit.project.name";

	/**
	 * Name of the property holding the default target of the
	 * currently executing project, if one has been specified.
	 * <p/>
	 * Value: {@value}
	 *
	 * @since Ant 1.8.0
	 */
	public static final String PROJECT_DEFAULT_TARGET = "thermit.project.default-target";

	/**
	 * Name of the property holding a comma separated list of targets
	 * that have been invoked (from the command line).
	 * <p/>
	 * Value: {@value}
	 *
	 * @since Ant 1.8.0
	 */
	public static final String PROJECT_INVOKED_TARGETS = "thermit.project.invoked-targets";

	/**
	 * Name of the project reference holding an instance of {@link
	 * org.napile.thermit.taskdefs.launcher.CommandLauncher} to use
	 * when executing commands with the help of an external skript.
	 * <p/>
	 * <p>Alternatively this is the name of a system property holding
	 * the fully qualified class name of a {@link
	 * org.napile.thermit.taskdefs.launcher.CommandLauncher}.</p>
	 * <p/>
	 * Value: {@value}
	 *
	 * @since Ant 1.9.0
	 */
	public static final String ANT_SHELL_LAUNCHER_REF_ID = "thermit.shellLauncher";

	/**
	 * Name of the project reference holding an instance of {@link
	 * org.napile.thermit.taskdefs.launcher.CommandLauncher} to use
	 * when executing commands without the help of an external skript.
	 * <p/>
	 * <p>Alternatively this is the name of a system property holding
	 * the fully qualified class name of a {@link
	 * org.napile.thermit.taskdefs.launcher.CommandLauncher}.</p>
	 * <p/>
	 * Value: {@value}
	 *
	 * @since Ant 1.9.0
	 */
	public static final String ANT_VM_LAUNCHER_REF_ID = "thermit.vmLauncher";
}

