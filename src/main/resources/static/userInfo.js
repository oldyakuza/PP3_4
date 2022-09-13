$(async function() {
    await thisUser();
});
async function thisUser() {
    fetch("http://localhost:8080/api/userInfo")
        .then(res => res.json())
        .then(data => {
            $('#topBarUsername').append(data.username);
            let roles = data.roles.map(role => " " + role.name.substring(5));
            $('#topBarRoles').append(roles);

            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.firstName}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.username}</td>
                <td>${roles}</td>)`;
            $('#userInfoPanelBody').append(user);
        })
}