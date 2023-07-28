package com.gloombomb.classroster;

import com.gloombomb.classroster.controller.ClassRosterController;
import com.gloombomb.classroster.dao.ClassRosterAuditDao;
import com.gloombomb.classroster.dao.ClassRosterAuditDaoImpl;
import com.gloombomb.classroster.dao.ClassRosterDao;
import com.gloombomb.classroster.dao.ClassRosterDaoFileImpl;
import com.gloombomb.classroster.service.ClassRosterServiceLayer;
import com.gloombomb.classroster.service.ClassRosterServiceLayerImpl;
import com.gloombomb.classroster.ui.ClassRosterView;
import com.gloombomb.classroster.ui.UserIO;
import com.gloombomb.classroster.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myAuditDao, myDao);
        ClassRosterController controller = new ClassRosterController(myService, myView);
        controller.run();
    }
}
