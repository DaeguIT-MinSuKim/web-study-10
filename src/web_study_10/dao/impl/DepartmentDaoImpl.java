package web_study_10.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web_study_10.dao.DepartmentDao;
import web_study_10.ds.JndiDS;
import web_study_10.dto.Department;

public class DepartmentDaoImpl implements DepartmentDao {
    private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();

    private DepartmentDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    public static DepartmentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Department> selectDepartmentByAll() {
        String sql = "SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT";
        try (Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                ArrayList<Department> list = new ArrayList<>();
                do {
                    list.add(getDepartment(rs));
                } while (rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    private Department getDepartment(ResultSet rs) throws SQLException {
        int deptNo = rs.getInt("DEPT_NO");
        String deptName = rs.getString("DEPT_NAME");
        int floor = rs.getInt("FLOOR");
        return new Department(deptNo, deptName, floor);
    }

    @Override
    public Department selectDepartmentByNo(Department dept) {
        String sql = "SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT WHERE DEPT_NO = ?";
        try (Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);){
            pstmt.setInt(1, dept.getDeptNo());
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                     return getDepartment(rs);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public int insertDepartment(Department dept) {
        String sql = "insert into department values(?, ?, ?)";
        try(Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, dept.getDeptNo());
            pstmt.setString(2, dept.getDeptName());
            pstmt.setInt(3, dept.getFloor());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public int updateDepartment(Department dept) {
        String sql = "update department set dept_name=?, floor=? where dept_no = ?";
        try(Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, dept.getDeptName());
            pstmt.setInt(2, dept.getFloor());
            pstmt.setInt(3, dept.getDeptNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public int deleteDepartment(Department dept) {
        String sql = "delete from department where dept_no = ?";
        try(Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, dept.getDeptNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public int getNextNo() {
        String sql = "SELECT MAX(DEPT_NO)+1 FROM DEPARTMENT";
        try(Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
           if (rs.next()) {
               return rs.getInt(1);
           }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return 0;
    }


}
