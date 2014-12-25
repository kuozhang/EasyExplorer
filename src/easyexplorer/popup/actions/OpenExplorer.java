package easyexplorer.popup.actions;


import java.io.IOException;

import org.eclipse.core.internal.resources.OS;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class OpenExplorer implements IObjectActionDelegate {

	private Shell shell;
	private String file_Path;

	/**
	 * Constructor for Action1.
	 */
	public OpenExplorer() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		try {
			if(file_Path == null){
				return;
			}

            Runtime rt = Runtime.getRuntime();
            String osName = Platform.getOS();
            if (osName.contains("win")) { // OS Windows
                rt.exec("cmd /c start "+"file:///"+ PathUtil.handleSpace(file_Path));
            }
            else if (osName.contains("ubuntu")) { // OS: Ubuntu
                rt.exec("xdg-open "+"file:///"+ PathUtil.handleSpace(file_Path));
            }
            else if (osName.contains("mac")) { // OS: Ubuntu
                rt.exec("open "+"file:///"+ PathUtil.handleSpace(file_Path));
            }
            // TODO other distributions of linux


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sselection = (IStructuredSelection) selection;
			if (!sselection.isEmpty()) {
				Object object =  sselection.getFirstElement();
				file_Path = PathUtil.handlePath(object);
			}
		}
	}
}
