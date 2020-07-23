const ApplicationDepartmentController  = {

    DepartmentController: function (url, root) {
        Utils.loadData('GET', url)
            .then(data => {
                root.innerHTML = ''
                let child = document.createRange().createContextualFragment(
                    TemplateService.DepartmentTemplate(data))
                let employeeList = child.getElementById('employees')
                let id = child.getElementById("id").value
                Utils.loadData('GET', 'api/department/' + id + '/employee')
                    .then(data => {
                        for (let employee of data.content) {
                            let list = document.createRange()
                                .createContextualFragment(TemplateService.ListEmployeesTemplate(employee))
                            employeeList.appendChild(list)
                        }
                    })
                    .catch(err => console.log(err))

            })
            .catch(err => console.log(err))
    },

    DepartmentsController: function (url, root) {
        Utils.loadData('GET', url)
            .then(data => {
                root.innerHTML = ''
                for (let element of data.content) {
                    root.insertAdjacentHTML('beforeend', TemplateService.DepartmentsTemplate(element))

                }
            })
            .catch(err => console.log(err))
    },

    DepartmentUpdateController: function (url, root) {
        const doc = document
        let body = {
            id: doc.getElementById('id').value,
            name: doc.getElementById('name').value,
            manager: doc.getElementById('manager').value,
            address: doc.getElementById('address').value
        }

        Utils.loadDataWithBody('POST', url, body)
            .catch(err => console.log(err))
    },

    DepartmentCreateController: function (url, root) {

        const doc = document
        let body = {
            name: doc.getElementById('name').value,
            manager: doc.getElementById('manager').value,
            address: doc.getElementById('address').value
        }

        Utils.loadDataWithBody('POST', url, body)
            .catch(err => console.log(err))
    },

    DepartmentFormController: function (url, root) {
        const form = document.createRange().createContextualFragment(TemplateService.DepartmentFormTemplate())

        root.innerHTML = ''
        root.appendChild(form)

    },

    DepartmentDeleteController: function (url, root) {
        let countEmployee = document.getElementById('count').value

        if (countEmployee === '0') {
            Utils.loadData('DELETE', url)
                .then(function () {
                    window.history.go(-2)
                })
                .catch(err => console.log(err))
        } else {
            alert('Can not delete Department that has employees')
        }
    }

}