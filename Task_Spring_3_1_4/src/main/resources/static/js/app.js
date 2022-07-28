let tb1 = document.getElementById('tb1');
let curUserName = document.getElementById('userName');

const url = 'http://localhost:8080/admin/';            

getUsersList();

/*GET All Users*/
function getUsersList() {
    fetch(`${url}userslist`)
            .then((resp) => resp.json())
            .then(function (data) {
                let users = data;
                users.forEach(createTableRow);
            })
            .catch(function (error) {
                console.log(error);
            });
}

function createTableRow(user) {
    let tr = document.createElement('tr');
    let thId = document.createElement('th');
    let tdFirstName = document.createElement('td');
    let tdLastName = document.createElement('td');
    let tdEmail = document.createElement('td');
    let tdRoles = document.createElement('td');
    let tdEditBtn = document.createElement('td');
    let tdDeleteBtn = document.createElement('td');

    thId.scope = "row";
    thId.innerHTML = `${user.id}`;
    tdFirstName.innerHTML = `${user.firstName}`;
    tdLastName.innerHTML = `${user.lastName}`;
    tdEmail.innerHTML = `${user.email}`;

    for (let i = 0; i < user.roles.length; i++) {
        tdRoles.innerHTML += `${user.roles[i].name}`;
        if (i < (user.roles.length - 1)) {
            tdRoles.innerHTML += ', ';
        }
    }

    if (user.email !== curUserName.innerHTML) {
        tdEditBtn.innerHTML = `<button class="btn btn-info text-light" id="editBtn" name=editBtn value=${user.id} data-bs-toggle="modal" data-bs-target="#staticBackdrop">Edit</button>`;
        tdDeleteBtn.innerHTML = `<button class="btn btn-danger" id="deleteBtn" name=deleteBtn value=${user.id}>Delete</button>`;
    }
    
    tr.id = `${user.id}`;
    tr.append(thId);
    tr.append(tdFirstName);
    tr.append(tdLastName);
    tr.append(tdEmail);
    tr.append(tdRoles);
    tr.append(tdEditBtn);
    tr.append(tdDeleteBtn);
    tb1.append(tr);
    
    let btnEdit = tdEditBtn.firstElementChild;
    editBtnClick(btnEdit);
    let btnDelete = tdDeleteBtn.firstElementChild;
    deleteBtnClick(btnDelete);
}

/*EDIT User By Id*/
function editBtnClick(btn) {
    if (btn !== null) {
        btn.addEventListener('click', (e) => {
            let id = e.target.value;
            
            /*GET User By Id*/
            fetch(`${url}update?id=${id}`)
                    .then((resp) => resp.json())
                    .then(function (data) {
                        let user = data;
                        fillTheModal(user);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
        });
    }
}

function fillTheModal(user) {
    let inputModalEmail = document.getElementById('inputModalEmail');
    inputModalEmail.value = `${user.email}`;
    let inputModalFirstName = document.getElementById('inputModalFirstName');
    inputModalFirstName.value = `${user.firstName}`;
    let inputModalLastName = document.getElementById('inputModalLastName');
    inputModalLastName.value = `${user.lastName}`;
    let inputModalPassword = document.getElementById('inputModalPassword');
            
    for (let i = 0; i < user.roles.length; i++) {
        if (user.roles[i].name==='ADMIN') {
            let inputModalRoleAdmin = document.getElementById('inputModalRoleAdmin');
            inputModalRoleAdmin.checked='true';
        } else if (user.roles[i].name==='USER') {
            let inputModalRoleUser = document.getElementById('inputModalRoleUser');
            inputModalRoleUser.checked='true';
        }
    }
            
    let formEdit = document.getElementById('formEdit');
    let ModalSaveBtn = document.getElementById('ModalSave');

    // Modal Save Button Listener
    ModalSaveBtn.addEventListener('click', (e) => {
        e.preventDefault();
        if (formEdit.checkValidity()) {
            let editUserRoles = [];
            if (inputModalRoleAdmin.checked) {
                editUserRoles.push({name: 'ADMIN'});
            }
            if (inputModalRoleUser.checked) {
                editUserRoles.push({name: 'USER'});
            }
            
            fetch(`${url}update?id=${user.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type':'application/json'
                },
                body: JSON.stringify({
                    id: user.id,
                    email: inputModalEmail.value,
                    password: inputModalPassword.value,
                    firstName: inputModalFirstName.value,
                    lastName: inputModalLastName.value,
                    roles: editUserRoles
                })
            })
                .then((resp) => resp.json())
                .then(() => location.reload());
        }
    });
}

/*DELETE User By Id*/
function deleteBtnClick(btn) {
    if (btn !== null) {
        btn.addEventListener('click', (e) => {
            let id = e.target.value;
            fetch(`${url}delete?id=${id}`, {
                method: 'DELETE'
            }).then(() => {
                let tr = document.getElementById(`${id}`);
                tb1.removeChild(tr);
            });
        });
    }
}