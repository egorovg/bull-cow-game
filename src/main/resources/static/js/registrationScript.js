window.onload = function() {
    document.getElementById('registerData').addEventListener('submit', submitForm);
};

function submitForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target);
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    let obj = {
        "username": username,
        "password": password
    };
    let request = new Request(event.target.action, {
        method: 'POST',
        body: JSON.stringify(obj),
        headers: {
            'Content-Type': 'application/json',
        },
    });
    fetch(request).then(function(response) {
        if (response.ok) {
            window.location.replace(location.pathname + '?success');
        } else {
            window.location.replace(location.pathname+ '?err')
        }
    });
}