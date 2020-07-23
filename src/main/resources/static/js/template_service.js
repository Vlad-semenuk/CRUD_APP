var TemplateService = {

    DepartmentsTemplate: function (data) {
        return `
    <div class="col-sm-4" id="${data.id}">
    <div class="card text-white bg-secondary mb-3">
        <div class="card-header" id="department_name[2]">Department: ${data.name}</div>
        <div class="card-body">
            <h4 class="card-title">Manager: ${data.manager}</h4>
            <h6 class="card-title">Count employee: ${data.countEmployee}</h6>
            <p class="card-text">Address: ${data.address}</p>

            <div class="content">
                <a href="#department/${data.id}" class="btn btn-outline-light"
                 onclick="router.changeLocation(this)">Edit Department</a>
            </div>
        </div>
    </div>
    </div>
    `

    },

    EmployeesTemplates: function (data) {
        return `
    <div class="col-sm-3">
    <div class="card text-white bg-secondary mb-3" id="employee[${data.id}]">

        <img class="card-img-top"
             src="image/no_avatar.png"
             alt="Card image">

        <div class="card-body">
            <div class="content">

                <h4 class="card-title">${data.firstName}</h4>
                <h4 class="card-title">${data.secondName}</h4>

                <a href="#employee/${data.id}" class="btn btn-outline-light" 
                onclick="router.changeLocation(this)">See profile</a>
            </div>
        </div>
    </div>
    </div>
    `

    },

    EmployeeProfileTemplate: function (data) {
        return `
  <div class="col-sm-4">
    <div class="card text-white bg-secondary mb-3" id="employee[${data.id}]">

        <img class="card-img-top"
             src="image/no_avatar.png"
             alt="Card image">

        <form id="employee_form">
            <div class="card-body">
                <div class="content">


                    <input type="hidden" id="id" value="${data.id}">
                    <input type="hidden" id="id_dep" value="">
                    <input type="text" id="email" class="form-control form-control-lg text-white bg-secondary "
                           value=${data.email}>

                    <input type="text" id="first_name"
                           class="form-control form-control-lg text-white bg-secondary "
                           value="${data.firstName}">
                    <input type="text" id="second_name"
                           class="form-control form-control-lg text-white bg-secondary "
                           value="${data.secondName}">
                    <input id="department"
                           class="form-control form-control-lg text-white bg-secondary "
                           value="">
                    <input type="text" id="salary"
                           class="form-control form-control-lg text-white bg-secondary "
                           value="${data.salary}">

                      
                    <a href="#employee/update" class="btn btn-outline-light" 
                    onclick="router.changeLocation(this)">Update</a>
                    <a href="#employee/delete/${data.id}" class="btn btn-outline-light" 
                    onclick="router.changeLocation(this)">Delete</a>

                </div>
            </div>
        </form>
    </div>

</div>
`
    },

    DepartmentTemplate: function (data) {
        return `
        <div class="col-sm-5" id="${data.id}" >
            <div class="card text-white bg-secondary mb-3">
                <div class="card-header" id="department_name[2]"><input type="text" id="name"
                           class="form-control form-control-lg text-white bg-secondary "
                           value="${data.name}"></div>

                <input type="hidden" id="id" value="${data.id}">
                <input type="hidden" id="count" value="${data.countEmployee}">
                <div class="card-body">
                    <h3 class="card-text"> <input type="text" id="manager"
                           class="form-control form-control-lg text-white bg-secondary "
                           value="${data.manager}"></h3>
    
                    <h3 class="card-text"><input type="text" id="address"
                           class="form-control form-control-lg text-white bg-secondary "
                           value="${data.address}"></h3>

                    <div class="content">
                        <label for="employees">Employees</label>
                    </div>
                    <ul class="list-group list-group-flash" id="employees" style="text-align: center">

                    </ul>

                    <div class="content">
                         
                        <a href="#department/update" class="btn btn-outline-light" 
                        onclick="router.changeLocation(this)">Update</a>
                        <a href="#department/delete/${data.id}" class="btn btn-outline-light" 
                        onclick="router.changeLocation(this)">Delete</a>
                           
                    </div>
                </div>
            </div>
        </div>
    `

    },

    EmployeeFormTemplate: function () {
        return `  
        <div class="col-sm-3" style="margin-top: 25px">

            <form>
                <div class="content">
                    <label>Create employee</label>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                           placeholder="Enter email">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="first_name" placeholder="Enter First name">
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" id="second_name" placeholder="Enter Second name">
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" id="salary" placeholder="Enter Salary">
                </div>
                <label for="select_dep">Choose department</label> 
                <div class="form-group">
                    <select class="form-control" id="select_dep"></select>
                </div>

                <div class="content">
                    <a href="#employee/create" class="btn btn-outline-secondary" 
                    onclick="router.changeLocation(this)">Create</a>
                </div>
            </form>

        </div>
      `

    },

    ListEmployeesTemplate: function (data) {
        return `
         <li class="list-group-item bg-secondary ">
          <form >
           <input type="hidden" id="employee_id" value="${data.id}">
                            Name: ${data.firstName}<br>
                            Email: ${data.email}<br>
                            Salary: ${data.salary}<br>
              <div class="content">
                <input type="button" class="btn btn-outline-light"
                        onclick="ApplicationEmployeeController.EmployeeRemoveController(${data.id})" value="Remove">
             </div>
             </form>
          </li>
        `

    },

    DepartmentFormTemplate: function () {
        return ` 
     <div class="col-sm-3" style="margin-top: 25px">

     <form>
        <div class="content">
            <label>Create Department</label>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="name"
                   placeholder="Enter name">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="manager" placeholder="Enter manager name">
        </div>

        <div class="form-group">
            <input type="text" class="form-control" id="address" placeholder="Enter address">
        </div>

        <div class="content">
            <a href="#department/create" class="btn btn-outline-secondary" 
            onclick="router.changeLocation(this)">Create</a>
        </div>
     </form>

    </div>
        
        `
    }
}
