// Function for login
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8083/auth/login', { // Sử dụng URL tuyệt đối
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(errorData => {
                throw new Error(`Login failed: ${response.status} - ${errorData.message || response.statusText}`);
            });
        }
        return response.json();
    })
    .then(data => {
        console.log('Login success:', data); // Debug
        localStorage.setItem('jwtToken', data.token);
        window.location.href = '/profile';
    })
    .catch(error => {
        console.error('Error:', error); // Debug
        alert('Login failed: ' + error.message);
    });
});

// Function to fetch user profile
function fetchUserProfile() {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
        window.location.href = '/login';
        return;
    }

    fetch('http://localhost:8083/users/me', { // Sử dụng URL tuyệt đối
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(errorData => {
                throw new Error(`Failed to fetch profile: ${response.status} - ${errorData.message || response.statusText}`);
            });
        }
        return response.json();
    })
    .then(data => {
        console.log('Profile data:', data); // Debug
        document.getElementById('userInfo').innerHTML = `
            <p>Email: ${data.email}</p>
            <p>Name: ${data.name}</p> <!-- Sử dụng name thay vì fullName -->
        `;
    })
    .catch(error => {
        console.error('Error:', error); // Debug
        alert('Error: ' + error.message);
        localStorage.removeItem('jwtToken');
        window.location.href = '/login';
    });
}