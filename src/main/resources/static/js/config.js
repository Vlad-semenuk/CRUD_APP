function init() {
    return new Router([
        new Route('#employee/all', 'api/employee/all', ApplicationEmployeeController.EmployeesController),
        new Route('#employee/[0-9]+', 'api/employee/', ApplicationEmployeeController.EmployeeController),
        new Route('#employee/update', 'api/employee', ApplicationEmployeeController.EmployeeUpdateController),
        new Route('#employee/delete/[0-9]+', 'api/employee/', ApplicationEmployeeController.EmployeeDeleteController),
        new Route('#employee/create', 'api/employee/', ApplicationEmployeeController.EmployeeCreateController),
        new Route('#employee/remove', 'api/employee/', ApplicationEmployeeController.EmployeeRemoveController),
        new Route('#employee_form', '', ApplicationEmployeeController.EmployeeFormController),
        new Route('#department_form', '', ApplicationDepartmentController.DepartmentFormController),
        new Route('#department/all', 'api/department/all', ApplicationDepartmentController.DepartmentsController),
        new Route('#department/[0-9]+', 'api/department/', ApplicationDepartmentController.DepartmentController),
        new Route('#department/create', 'api/department/', ApplicationDepartmentController.DepartmentCreateController),
        new Route('#department/update', 'api/department/', ApplicationDepartmentController.DepartmentUpdateController),
        new Route('#department/delete/[0-9]+', 'api/department/', ApplicationDepartmentController.DepartmentDeleteController),

    ])

}


let router = init()

window.addEventListener('popstate', function () {
    router.loadLocation(document.location.href)
})


