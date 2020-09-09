package web_study_10.service;

import java.util.List;

import web_study_10.dao.DepartmentDao;
import web_study_10.dao.impl.DepartmentDaoImpl;
import web_study_10.dto.Department;

public class DeptService {
    private DepartmentDao dao = DepartmentDaoImpl.getInstance();

    public List<Department> getDepartmentList() {
        return dao.selectDepartmentByAll();
    }

    public int getNextDeptNo() {
        return dao.getNextNo();
    }

    public int addDepartment(Department dept) {
        return dao.insertDepartment(dept);
    }

    public Department getDepartment(Department dept) {
        return dao.selectDepartmentByNo(dept);
    }

    public int delDepartment(Department dept) {
        return dao.deleteDepartment(dept);
    }

    public int modifyDepartment(Department dept) {
        return dao.updateDepartment(dept);
    }
}
