if (document.getElementById('user-update')) {

    document.getElementById('user-update').addEventListener('submit',
        function (updateEvent) {
            updateEvent.preventDefault();
            const formData = new FormData(this);
            const data = {};
            formData.forEach((value, key) => {
                data[key] = value;
            })
            fetch("/api/user/update-profile", {
                method: "PUT",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        console.log("값 안옴")
                    }
                    return response.json()
                })

                .then(result => {
                    if (result) {
                        alert('수정완료');
                        window.location.href = "/user/my-page";
                    } else {
                        console.log("비정상");
                    }
                });
        });
}
if (document.getElementById("user-withdrawal-btn")) {
    console.log("hi")
    document.getElementById("user-withdrawal-btn").onclick = function () {
        let password = document.getElementById("password").value;
        let email = document.getElementById("email").value;
        console.log(password);
        console.log(email);
        fetch("/api/user/", {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'},
                body: JSON.stringify({
                    email: email,
                    password: password
                })

        }).then(response => {
            if (!response) {
                alert("에러!")
                history.back();
            }
            return response.json();
        }).then(result => {
            if (result) {
                alert("탈퇴되었습니다.")
                window.location.href = "/"
            }
        })
    }
}
