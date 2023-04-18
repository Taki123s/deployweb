<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/12/2023
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<!-- Replace "test" with your own sandbox Business account app client ID -->
<script src="https://www.paypal.com/sdk/js?client-id=AbenXsywXYlbMw4GpzHDSdiXPx7hKY7adwNFIjsSlY7HfsmSRD6DOzeswhhcBtKiqC46A2kiwzyk_Wf7&currency=USD"></script>
<!-- Set up a container element for the button -->
<div id="paypal-button-container"></div>
<script>
    paypal.Buttons({
        createOrder() {
            return fetch("/anime-main/PayPalCheckOut", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({

                            sku: "123213",
                            quantity: "1122",
                            concac:"concac",

                }),
            })
                .then((response) => response.json())
                .then((order) => order.id);
        },
        onApprove(data) {
            return fetch("/anime-main/PayPalCheckOut", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    orderID: data.orderID
                })
            })
                .then((response) => response.json())
                .then((orderData) => {

                    console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));
                    const transaction = orderData.purchase_units[0].payments.captures[0];
                    alert(`Transaction `+transaction.status+`: `+transaction.id+`\n\nSee console for all available details`);

                });
        }
    }).render('#paypal-button-container');
</script>
</body>
</html>
