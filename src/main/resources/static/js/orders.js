var requestOptions = {
    method: 'GET',
    redirect: 'follow'
};

let ordersContainer = document.getElementById('orders-container')
ordersContainer.innerHTML = ''

fetch("http://localhost:8080/api/orders", requestOptions)
    .then(response => response.json())
    .then(json => json.forEach(order => {

        let row = document.createElement('tr')

        let orderId = document.createElement('td')
        let products = document.createElement('td')
        let totalPrice = document.createElement('td')
        let orderDate = document.createElement('td')
        let customerEmail = document.createElement('td')
        let fullAddress = document.createElement('td')

        orderId.textContent = order.orderId

        // let productsHtml = '<>'
        // order.products.forEach(p => {
        //     alert(p.prodName)
        //     productsHtml += p.prodName + ';'
        // })
        //
        // products.textContent = productsHtml

            let productsSpan = document.createElement('span')

            order.products.forEach(p => {

                    let productHtml = document.createElement('a')
                    productHtml.innerHTML=p.prodName+" "
                    productHtml.href='/products/'+p.id

                    productsSpan.appendChild(productHtml)
            })



        totalPrice.textContent = order.totalPrice.toFixed(2)
        orderDate.textContent = order.orderDate
        customerEmail.textContent = order.customerEmail
        fullAddress.textContent = order.fullAddress


        row.appendChild(orderId)
        products.appendChild(productsSpan)
        row.appendChild(products)
        row.appendChild(customerEmail)
        row.appendChild(totalPrice)
        row.appendChild(orderDate)
        row.appendChild(fullAddress)

        ordersContainer.appendChild(row)


    }))
    .catch(error => console.log('error', error));



