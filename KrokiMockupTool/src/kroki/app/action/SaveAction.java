/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kroki.app.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import kroki.app.KrokiMockupToolApp;
import kroki.app.utils.ImageResource;
import kroki.app.utils.StringResource;
import kroki.app.view.Canvas;
import kroki.common.copy.DeepCopy;
import kroki.profil.panel.VisibleClass;
import kroki.profil.subsystem.BussinesSubsystem;

/**
 *
 * @author Vladan Marsenić (vladan.marsenic@gmail.com)
 */
public class SaveAction extends AbstractAction {

    public SaveAction() {
        ImageIcon smallIcon = new ImageIcon(ImageResource.getImageResource("action.save.smallIcon"));
        ImageIcon largeIcon = new ImageIcon(ImageResource.getImageResource("action.save.largeIcon"));
        putValue(SMALL_ICON, smallIcon);
        putValue(LARGE_ICON_KEY, largeIcon);
        putValue(NAME, StringResource.getStringResource("action.save.name"));
        putValue(SHORT_DESCRIPTION, StringResource.getStringResource("action.save.description"));
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
    }

    public void actionPerformed(ActionEvent e) {
        Canvas c = KrokiMockupToolApp.getInstance().getTabbedPaneController().getCurrentTabContent();
        VisibleClass visibleClass = c.getVisibleClass();
        //NEW:
        BussinesSubsystem project = (BussinesSubsystem) visibleClass.umlPackage();
        while (true) {
            if (project.nestingPackage() != null) {
                project = (BussinesSubsystem) project.nestingPackage();
            } else {
                break;
            }
        }

        JFileChooser jfc = new JFileChooser();
        int retValue = jfc.showSaveDialog(KrokiMockupToolApp.getInstance().getKrokiMockupToolFrame());
        if (retValue == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            System.out.println("saving to file: " + file.getAbsolutePath());
            DeepCopy.save(project, file);
        } else {
            System.out.println("saving canceled: ");
        }


    }
}