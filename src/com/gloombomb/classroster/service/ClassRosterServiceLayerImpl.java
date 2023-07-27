package com.gloombomb.classroster.service;

import com.gloombomb.classroster.dao.ClassRosterPersistenceException;
import com.gloombomb.classroster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        throw new UnsupportedOperationException("Not supported yet!");
    }
}
