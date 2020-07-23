const ApplicationEmployeeController = {

    EmployeesController: function (url, root) {
        Utils.loadData('GET', url)
            .then(data => {
                root.innerHTML = ''
                for (let element of data.content) {
                    root.insertAdjacentHTML('beforeend', TemplateService.EmployeesTemplates(element))

                }
            })
            .catch(err => console.log(err))
    },

    EmployeeController: function (url, root) {
        Utils.loadData('GET', url)
            .then(data => {
                root.innerHTML = ''
                let employee = document.createRange()
                    .createContextualFragment(TemplateService.EmployeeProfileTemplate(data))
                let department_name = employee.getElementById('department')
                let department_id = employee.getElementById('id_dep')
                if (data.department === null) {
                    department_name.value = 'Department not chosen'
                    department_id.value = '0'
                } else {
                    department_name.value = data.department.name
                    department_id.value = data.department.id
                }
                root.appendChild(employee)

            })
            .catch(err => console.log(err))
    },


    EmployeeUpdateController: function (url, root) {

        const doc = document
        let department;
        department = doc.getElementById('id_dep').value === '0' ? null : {
            id: doc.getElementById("id_dep").value,
            name: doc.getElementById("department").value
        }
        let body = {
            id: doc.getElementById("id").value,
            firstName: doc.getElementById("first_name").value,
            secondName: doc.getElementById("second_name").value,
            email: doc.getElementById("email").value,
            salary: doc.getElementById("salary").value,
            department: department

        }

        Utils.loadDataWithBody('PUT', url, body)
            .catch(err => console.log(err))
    },

    EmployeeDeleteController: function (url, root) {
        Utils.loadData('DELETE', url)
            .then(function () {
                window.history.go(-2)
            })
            .catch(err => console.log(err))
    },

    EmployeeCreateController: function (url, root) {
        const doc = document
        let body = {
            firstName: doc.getElementById("first_name").value,
            secondName: doc.getElementById("second_name").value,
            email: doc.getElementById("email").value,
            salary: doc.getElementById("salary").value,
            department: {
                id: doc.getElementById('select_dep').value
            }

        }
        Utils.loadDataWithBody('POST', url, body)
            .catch(err => console.log(err))
    },

    EmployeeFormController: function (url, root) {
        let form = document.createRange().createContextualFragment(TemplateService.EmployeeFormTemplate())
        let selects = form.querySelector("#select_dep")
        Utils.loadData('GET', 'api/department/all')
            .then(data => {
                for (let department of data.content) {
                    let option = document.createElement('option')
                    option.textContent = department.name
                    option.value = department.id
                    selects.appendChild(option)
                }
            })
            .catch(error => console.log(error))
        root.innerHTML = ''
        root.appendChild(form)
    },

    EmployeeRemoveController: function (id) {
        let body = {
            id: id,
            department: null
        }

        Utils.loadDataWithBody('PATCH', 'employee', body)
            .catch(err => console.log(err))
    }

}