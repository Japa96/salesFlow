function loadData(endpoint) {
    fetch(endpoint)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            inputTable(data);

        })
        .catch(error => {
            console.error('Error:', error);
        });
}
function clearTable() {
    var table = document.getElementById("table-list-customer");
    var rowCount = table.rows.length;

    for (var i = rowCount - 1; i > 0; i--) {
        table.deleteRow(i);
    }
}
function inputTable(data) {
    clearTable();
    data.forEach(function(customer) {
        var table = document.getElementById('table-list-customer');
        var row = table.insertRow();

        // Adiciona células à linha
        var idCell = row.insertCell(0);
        var nameCell = row.insertCell(1);
        var lastNameCell = row.insertCell(2);
        var cpfCell = row.insertCell(3);
        var emailCell = row.insertCell(4);
        var cityCell = row.insertCell(5);
        var stateCell = row.insertCell(6);
        var countryCell = row.insertCell(7);
        var streetCell = row.insertCell(8);
        var neighborhoodCell = row.insertCell(9);
        var zipCodeCell = row.insertCell(10);
        var numberCell = row.insertCell(11);

        // Preenche as células com dados do cliente
        idCell.innerHTML = customer.id;
        nameCell.innerHTML = customer.name;
        lastNameCell.innerHTML = customer.lastName;
        cpfCell.innerHTML = customer.cpf;
        emailCell.innerHTML = customer.email;
        cityCell.innerHTML = customer.address.city;
        stateCell.innerHTML = customer.address.state;
        countryCell.innerHTML = customer.address.country;
        streetCell.innerHTML = customer.address.street;
        neighborhoodCell.innerHTML = customer.address.neighborhood;
        zipCodeCell.innerHTML = customer.address.zipCode;
        numberCell.innerHTML = customer.address.number;
    });
}


document.addEventListener("DOMContentLoaded", function () {
    var customersButton = document.getElementById("customers");
    var suppliersButton = document.getElementById("suppliers");
    var itemsButton = document.getElementById("items");
    var salesPersonsButton = document.getElementById("salesPersons");
    var sellsButton = document.getElementById("sells");
    var homeButton = document.getElementById("home-page");
    var registerCustomerButton = document.getElementById("register-customer");
    var searchAllCustomer = document.getElementById("search-all-customer");
    var searchCustomerById = document.getElementById("search-customer-by-id");

    if (customersButton) {
        customersButton.addEventListener("click", function () {
            window.location.href = "pages/customer.html";
        });
    }

    if (suppliersButton) {
        suppliersButton.addEventListener("click", function () {
            window.location.href = "pages/supplier.html";
        });
    }

    if (itemsButton) {
        itemsButton.addEventListener("click", function () {
            window.location.href = "pages/item.html";
        });
    }

    if (salesPersonsButton) {
        salesPersonsButton.addEventListener("click", function () {
            window.location.href = "pages/salesPerson.html";
        });
    }

    if (sellsButton) {
        sellsButton.addEventListener("click", function () {
            window.location.href = "pages/sell.html";
        });
    }

    if (homeButton) {
        homeButton.addEventListener("click", function () {
            window.location.href = "../index.html";
        });
    }

    if (registerCustomerButton) {
        registerCustomerButton.addEventListener("click", function () {
            document.getElementById('container-form-customer').style.display = 'block';
            document.getElementById('table-list-customer').style.display = 'none';
            document.getElementById('container-customer-by-id').style.display = 'none';
        });
    }

    var registerForm = document.getElementById('customer-form');
            if (registerForm) {
                registerForm.addEventListener('submit', function (event) {
                    event.preventDefault();

                    var formData = {
                        name: document.getElementById('firstName').value,
                        lastName: document.getElementById('lastName').value,
                        cpf: document.getElementById('cpf').value,
                        email: document.getElementById('email').value,
                        address: {
                            street: document.getElementById('street').value,
                            neighborhood: document.getElementById('neighborhood').value,
                            city: document.getElementById('city').value,
                            country: document.getElementById('country').value,
                            state: document.getElementById('state').value,
                            zipCode: document.getElementById('zipCode').value,
                            number: document.getElementById('number').value
                        }
                    };

                    fetch('http://localhost:8080/api/customer/registerCustomer', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(formData)
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);
                        window.alert(data.message);

                        document.getElementById('firstName').value = '';
                        document.getElementById('lastName').value = '';
                        document.getElementById('cpf').value = '';
                        document.getElementById('email').value = '';
                        document.getElementById('zipCode').value = '';
                        document.getElementById('city').value = '';
                        document.getElementById('state').value = '';
                        document.getElementById('country').value = '';
                        document.getElementById('street').value = '';
                        document.getElementById('neighborhood').value = '';
                        document.getElementById('number').value = '';
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
                });
            }

    if(searchAllCustomer) {
       searchAllCustomer.addEventListener("click", function () {
            clearTable();
            document.getElementById('container-form-customer').style.display = 'none';
            document.getElementById('container-customer-by-id').style.display = 'none';
            document.getElementById('table-list-customer').style.display = 'block';
            loadData('http://localhost:8080/api/customer/searchAllCustomer')
       })
    }

    if(searchCustomerById){
        searchCustomerById.addEventListener("click", function () {
            clearTable();
            document.getElementById('container-customer-by-id').style.display = 'block';
            document.getElementById('container-form-customer').style.display = 'none';
            document.getElementById('table-list-customer').style.display = 'none';

            var customerById = document.getElementById('customer-form-id');
            if(customerById){
                customerById.addEventListener('submit', function (event) {
                    event.preventDefault();

                var formDataId = document.getElementById('cpf-number').value

                fetch('http://localhost:8080/api/customer/searchCustomerByCpf/' + formDataId, {
                method: 'GET',
                headers: {'Content-Type': 'application/json'}
                })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    document.getElementById('cpf-number').value = '';
                    document.getElementById('container-customer-by-id').style.display = 'none';
                    document.getElementById('table-list-customer').style.display = 'block';
                    inputTable(data);
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
              })
            }
        })
    }
});