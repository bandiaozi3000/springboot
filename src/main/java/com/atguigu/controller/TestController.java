package com.atguigu.controller;

import com.atguigu.dao.DepartmentDao;
import com.atguigu.dao.EmployeeDao;
import com.atguigu.entity.Department;
import com.atguigu.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @PostMapping(value="/login")
    public String login(HttpServletRequest request, Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            request.getSession().setAttribute("username",username);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }
    }

    @GetMapping("/emps")
    public String list(Map<String,Object> map){
        Collection<Employee> collection = employeeDao.getAll();
        map.put("emps",collection);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String addPage(Map<String,Object> map){
        Collection<Department> departments = departmentDao.getDepartments();
        map.put("depts",departments);
        return "/emp/add";
    }

    @GetMapping("/emp/{id}")
    public String editPage(@PathVariable("id") Integer id , Map<String,Object> map){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        map.put("emp",employee);
        map.put("depts",departments);
        return "/emp/add";
    }

    @PostMapping("/emp")
    public String addEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id")Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }



}
