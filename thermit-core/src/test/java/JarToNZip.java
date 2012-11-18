import java.io.File;

import org.napile.thermit.util.FileUtils;

/**
 * @author VISTALL
 * @date 9:23/18.11.12
 */
public class JarToNZip
{
	public static void main(String... arg) throws Exception
	{
		File d = new File("lib");
		for(File f : d.listFiles())
		{
			if(f.getName().endsWith(".jar"))
			{
				FileUtils.getFileUtils().copyFile(f, new File(f.getAbsolutePath().replace(".jar", ".nzip")));
				f.delete();
			}
		}
	}
}
