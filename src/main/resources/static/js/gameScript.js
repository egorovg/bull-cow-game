window.onload = function() {
    document.getElementById('userNumber').addEventListener('submit', submitForm);
    document.getElementById('newGame').addEventListener('submit', clean);
};

function submitForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target);
    let number = document.getElementById('number').value;
    let obj = {
        "number": number
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
            return response.json();
        }
    }).then(function(data) {
        if (data != null) {
            let input = data.result;
            var parent = document.getElementsByTagName("ol")[0];
            var li = document.createElement("li");
            li.textContent = input;
            parent.appendChild(li);
        }
    })
    document.getElementById('userNumber').reset();
};

function clean() {
    var parent = document.getElementsByTagName("ol");
    parent = [];
};

function disp(form) {
    if (form.style.display == "none") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}