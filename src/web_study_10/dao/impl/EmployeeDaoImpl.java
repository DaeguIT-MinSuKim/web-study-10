package web_study_10.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import web_study_10.dao.EmployeeDao;
import web_study_10.ds.JndiDS;
import web_study_10.dto.Department;
import web_study_10.dto.Employee;
import web_study_10.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();

    private EmployeeDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    public static EmployeeDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Employee> selectEmployeeByAll() {
        String sql = "SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, REGDATE, TEL, EMAIL, PIC_URL, TITLE_NAME, DEPT_NAME, MANAGER_NAME "
                + "FROM VW_EMPLOYEE_JOIN";
        try (Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                List<Employee> list = new ArrayList<>();
                do {
                    list.add(getEmplyee(rs));
                } while (rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    private Employee getEmplyee(ResultSet rs) throws SQLException {
        int empNo = rs.getInt("EMP_NO");
        String empName = rs.getString("EMP_NAME");
        Title title = new Title(rs.getInt("TNO"));
        Employee manager = new Employee(rs.getInt("MANAGER"));
        int salary = rs.getInt("SALARY");
        Department dept = new Department(rs.getInt("DNO"));
        String email = rs.getString("EMAIL");
        Date regDate = rs.getTimestamp("REGDATE");
        String tel = rs.getString("TEL");
        String picUrl = rs.getString("PIC_URL");
        String passwd = null;
        try {
            passwd = rs.getString("PASSWD");
        } catch (SQLException e) {
        }
        try {
            String titleName = rs.getString("TITLE_NAME");
            title.setTitleName(titleName);
        } catch (SQLException e) {
        }
        try {
            String deptName = rs.getString("DEPT_NAME");
            dept.setDeptName(deptName);
        } catch (SQLException e) {
        }
        try {
            String managerName = rs.getString("MANAGER_NAME");
            manager.setEmpName(managerName);
        } catch (SQLException e) {
        }

        Employee emp = new Employee(empNo, empName, title, manager, salary, dept, email, regDate, tel, picUrl);
        if (passwd != null) {
            emp.setPasswd(passwd);
        }
        return emp;
    }

    @Override
    public int insertEmployee(Employee emp) {
        String sql = "INSERT INTO EMPLOYEE(EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, PASSWD, TEL) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, emp.getEmpNo());
            pstmt.setString(2, emp.getEmpName());
            pstmt.setInt(3, emp.getTitle().getTitleNo()); // tno 추후 수정
            pstmt.setInt(4, emp.getManager().getEmpNo());// manager 추후 수정
            pstmt.setInt(5, emp.getSalary());// salary 추후 수정
            pstmt.setInt(6, emp.getDept().getDeptNo());// dno 추후 수정
            pstmt.setString(7, emp.getEmail());
            pstmt.setString(8, emp.getPasswd());
            pstmt.setString(9, emp.getTel());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public List<Employee> selectManagerByDno(Department dept) {
        String sql = "SELECT EMP_NO, EMP_NAME FROM EMPLOYEE WHERE dno = ?";
        try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, dept.getDeptNo());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    List<Employee> list = new ArrayList<Employee>();
                    do {
                        list.add(new Employee(rs.getInt("EMP_NO"), rs.getString("EMP_NAME")));
                    } while (rs.next());
                    return list;
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public Employee selectEmployeeByNo(Employee emp) {
        String sql = "SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, REGDATE, TEL, PIC_URL, PASSWD "
                + "  FROM EMPLOYEE " + " WHERE EMP_NO = ?";
        try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setInt(1, emp.getEmpNo());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getEmplyee(rs);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public int idDupCheck(int empNo) {
        String sql = "SELECT EMP_NO FROM EMPLOYEE WHERE EMP_NO = ?";
        try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setInt(1, empNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return 1;
    }

    @Override
    public int updateEmployee(Employee emp) {
        String sql = "update EMPLOYEE "
                +    "   SET EMP_NAME=?, TNO=?, MANAGER=?, SALARY=?, DNO=?, EMAIL=?, PASSWD=?, TEL=? "
                   + "WHERE EMP_NO = ?";
        try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, emp.getEmpName());
            pstmt.setInt(2, emp.getTitle().getTitleNo());
            pstmt.setInt(3, emp.getManager().getEmpNo());
            pstmt.setInt(4, emp.getSalary());
            pstmt.setInt(5, emp.getDept().getDeptNo());
            pstmt.setString(6, emp.getEmail());
            pstmt.setString(7, emp.getPasswd());
            pstmt.setString(8, emp.getTel());
            pstmt.setInt(9, emp.getEmpNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public int deleteEmployee(Employee emp) {
        String sql = "DELETE FROM EMPLOYEE WHERE EMP_NO = ?";
        try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, emp.getEmpNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }
}
