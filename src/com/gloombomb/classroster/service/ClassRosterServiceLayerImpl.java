package com.gloombomb.classroster.service;

import com.gloombomb.classroster.dao.ClassRosterDao;
import com.gloombomb.classroster.dao.ClassRosterPersistenceException;
import com.gloombomb.classroster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    ClassRosterDao dao;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao) {
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
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
//        throw new UnsupportedOperationException("Not supported yet!");
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
//        throw new UnsupportedOperationException("Not supported yet!");
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.removeStudent(studentId);
//        throw new UnsupportedOperationException("Not supported yet!");
    }
}
