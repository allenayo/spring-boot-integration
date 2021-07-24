package com.allenayo.sbj.controller;

import com.allenayo.sbj.domain.JsonResult;
import com.allenayo.sbj.domain.Employee;
import com.allenayo.sbj.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("employee")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult postEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return JsonResult.get("0", "操作成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonResult putEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        Employee getEmployee = employeeService.findById(id);
        if (getEmployee == null) return JsonResult.get("1", "数据不存在");

        employee.setId(getEmployee.getId());
        employeeService.update(employee);
        return JsonResult.get("0", "操作成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult getEmployee(@PathVariable("id") long id) {
        return JsonResult.get("0", "操作成功", employeeService.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteEmployee(@PathVariable("id") long id) {
        Employee getEmployee = employeeService.findById(id);
        if (getEmployee == null) return JsonResult.get("1", "数据不存在");

        employeeService.deleteById(getEmployee.getId());
        return JsonResult.get("0", "操作成功");
    }
}