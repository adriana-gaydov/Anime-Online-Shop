var requestOptions = {
    method: 'GET',
    redirect: 'follow'
};

let usersContainer = document.getElementById('users-container')
usersContainer.innerHTML = ''

fetch("http://localhost:8080/api/users", requestOptions)
    .then(response => response.json())
    .then(json => json.forEach(user => {

        let row = document.createElement('tr')

        let id = document.createElement('td')
        let firstName = document.createElement('td')
        let midName = document.createElement('td')
        let lastName = document.createElement('td')
        let email = document.createElement('td')
        let action = document.createElement('td')

        id.textContent = user.id
        firstName.textContent = user.firstName
        midName.textContent = user.middleName == null ? "" : user.middleName
        lastName.textContent = user.lastName
        email.textContent = user.email

        let actionHref = document.createElement('a')

        if (user.active) {
            actionHref.href = '/admin/users/disable/' + user.id
            actionHref.textContent = 'Disable'
        } else {
            actionHref.href = '/admin/users/enable/' + user.id
            actionHref.textContent = 'Enable'
        }

        action.appendChild(actionHref)

        row.appendChild(id)
        row.appendChild(firstName)
        row.appendChild(midName)
        row.appendChild(lastName)
        row.appendChild(email)
        row.appendChild(action)

        usersContainer.appendChild(row)


    }))
    .catch(error => console.log('error', error));



