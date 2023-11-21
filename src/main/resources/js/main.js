function loadData(endpoint, tableId) {
    fetch(endpoint)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            inputTable(data, tableId);

        })
        .catch(error => {
            console.error('Error:', error);
        });
}
function clearTable(tableId) {
    var table = document.getElementById(tableId);
    var rowCount = table.rows.length;

    for (var i = rowCount - 1; i > 0; i--) {
        table.deleteRow(i);
    }
}
function inputTable(data, tableId) {
    clearTable(tableId);
    data.forEach(function(item) {
        var table = document.getElementById(tableId);
        var row = table.insertRow();

        for (var key in item) {
            if (item.hasOwnProperty(key)) {
                var cell = row.insertCell();
                cell.innerHTML = item[key];
            }
        }
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
    var searchCustomerById = document.getElementById("search-customer-by-cpf");
    var registerSupplier = document.getElementById("register-supplier");
    var searchAllSupplier = document.getElementById("search-all-supplier");

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
            clearTable('table-list-customer');
            document.getElementById('container-form-customer').style.display = 'none';
            document.getElementById('container-customer-by-id').style.display = 'none';
            document.getElementById('table-list-customer').style.display = 'block';
            loadData('http://localhost:8080/api/customer/searchAllCustomer', 'table-list-customer')
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
                    inputTable(data, 'table-list-customer');
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
              })
            }
        })
    }

    if(registerSupplier){
        registerSupplier.addEventListener("click", function () {
            document.getElementById("container-form-supplier").style.display = 'block';
            document.getElementById("table-list-supplier").style.display = 'none';
        })
    }

    if(searchAllSupplier){
        searchAllSupplier.addEventListener("click", function () {
            document.getElementById("container-form-supplier").style.display = 'none';
            document.getElementById("table-list-supplier").style.display = 'block';
            loadData('http://localhost:8080/api/supplier/searchAllSupplier', 'table-list-supplier')
        })
    }

    var registerSupplierForm = document.getElementById('supplier-form');
    if(registerSupplierForm){
        registerSupplierForm.addEventListener('submit', function (event) {
            event.preventDefault();

        var formDataSupplier = {
            name: document.getElementById('supplier-name').value,
            countryOfOrigin: document.getElementById('country-of-origin-supplier').value
        };

        fetch('http://localhost:8080/api/supplier/registerSupplier', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                },
            body: JSON.stringify(formDataSupplier)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            window.alert(data.message);

            document.getElementById('supplier-name').value = '';
             document.getElementById('country-of-origin-supplier').value = '';
        })
        .catch(error => {
            console.error('Error:', error);
            });
        });
    }})