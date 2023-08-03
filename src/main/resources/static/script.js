const BASE_URL = "https://qa2.sunbasedata.com/sunbase/portal/api";

let token = "";

async function login() {
    const loginId = document.getElementById("loginId").value;
    const password = document.getElementById("password").value;

    const response = await fetch(`${BASE_URL}/assignment_auth.jsp`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            login_id: loginId,
            password: password
        })
    });

    if (response.ok) {
        const data = await response.json();
        token = data.token;
        showCustomerListScreen();
    } else {
        alert("Login failed. Please check your credentials.");
    }
}

async function createCustomer() {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const street = document.getElementById("street").value;
    const address = document.getElementById("address").value;
    const city = document.getElementById("city").value;
    const state = document.getElementById("state").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;

    const response = await fetch(`${BASE_URL}/assignment.jsp?cmd=create`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            first_name: firstName,
            last_name: lastName,
            street: street,
            address: address,
            city: city,
            state: state,
            email: email,
            phone: phone
        })
    });

    if (response.status === 201) {
        alert("Customer created successfully.");
        showCustomerListScreen();
    } else if (response.status === 400) {
        alert("First Name or Last Name is missing.");
    } else {
        alert("Failed to create customer.");
    }
}

// Add other functions for getting customer list, deleting and updating customer

function showCustomerListScreen() {
    document.getElementById("login-screen").style.display = "none";
    document.getElementById("customer-list-screen").style.display = "block";
    document.getElementById("add-customer-screen").style.display = "none";
    // Fetch customer list and display it here
}

function showAddCustomerScreen() {
    document.getElementById("login-screen").style.display = "none";
    document.getElementById("customer-list-screen").style.display = "none";
    document.getElementById("add-customer-screen").style.display = "block";
}

function cancelAddCustomer() {
    document.getElementById("login-screen").style.display = "none";
    document.getElementById("customer-list-screen").style.display = "block";
    document.getElementById("add-customer-screen").style.display = "none";
}
