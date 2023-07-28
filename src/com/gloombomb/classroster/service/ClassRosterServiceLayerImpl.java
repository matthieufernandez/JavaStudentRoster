package com.gloombomb.classroster.service;

import com.gloombomb.classroster.dao.ClassRosterAuditDao;
import com.gloombomb.classroster.dao.ClassRosterDao;
import com.gloombomb.classroster.dao.ClassRosterPersistenceException;
import com.gloombomb.classroster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    private ClassRosterAuditDao auditDao;
    ClassRosterDao dao;

    public ClassRosterServiceLayerImpl(ClassRosterAuditDao auditDao, ClassRosterDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }

    private void validateStudentData(Student student) throws ClassRosterDataValidationException {
        if (student.getFirstName() == null
        || student.getFirstName().trim().length() == 0
        || student.getLastName() == null
        || student.getLastName().trim().length() == 0
        || student.getCohort() == null
        || student.getCohort().trim().length() == 0) {

            throw new ClassRosterDataValidationException(
                    "ERROR: All data fields [First Name, Last Name, Cohort] are required!"
            );
        }
    }

    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException(
                    "ERROR: Could not create student. Student Id " + student.getStudentId() + " already exists."
            );
        }

        validateStudentData(student);
        dao.addStudent(student.getStudentId(), student);
        auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED.");
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
        return dao.removeStudent(studentId);
    }
}
