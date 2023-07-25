package com.gloombomb.classroster.dao;

import com.gloombomb.classroster.dto.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRosterDaoFileImpl implements ClassRosterDao {
    private Map<String, Student> students = new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student) {
        Student prevStudent = students.put(studentId, student);
        return prevStudent;
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Student getStudent(String studentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Student removeStudent(String studentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
