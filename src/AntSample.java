import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.junit.Test;


public class AntSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File buildFile = new File("build.xml");
		Project p = new Project();
		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		helper.parse(p, buildFile);
		p.executeTarget(p.getDefaultTarget());

		
	}
	@Test
	public void testbuildxml(){
		File buildFile = new File("build.xml");
		Project p = new Project();
		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		helper.parse(p, buildFile);
//		p.executeTarget(p.getDefaultTarget());
		p.executeTarget("use");
		
	}

}
