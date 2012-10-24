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

package org.napile.thermit.types.selectors;

import java.io.File;
import java.util.Enumeration;

/**
 * This selector has a collection of other selectors, any of which have to
 * select a file in order for this selector to select it.
 *
 * @since 1.5
 */
public class OrSelector extends BaseSelectorContainer
{

	/**
	 * Default constructor.
	 */
	public OrSelector()
	{
	}

	/**
	 * @return a string representation of the selector
	 */
	public String toString()
	{
		StringBuilder buf = new StringBuilder();
		if(hasSelectors())
		{
			buf.append("{orselect: ");
			buf.append(super.toString());
			buf.append("}");
		}
		return buf.toString();
	}

	/**
	 * Returns true (the file is selected) if any of the other selectors
	 * agree that the file should be selected.
	 *
	 * @param basedir  the base directory the scan is being done from
	 * @param filename the name of the file to check
	 * @param file     a java.io.File object for the filename that the selector
	 *                 can use
	 * @return whether the file should be selected or not
	 */
	public boolean isSelected(File basedir, String filename, File file)
	{
		validate();
		Enumeration<FileSelector> e = selectorElements();

		// First, check that all elements are correctly configured
		while(e.hasMoreElements())
		{
			if(e.nextElement().isSelected(basedir, filename, file))
			{
				return true;
			}
		}
		return false;
	}

}

