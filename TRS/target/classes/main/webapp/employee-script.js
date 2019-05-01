function getTicket(myFunction) {
    var xhttp = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            myFunction(this);
        }
    };
    xhttp.open("GET", "http://localhost:8080/TicketReimbursementSystem_war/tickets-employee.view");
    xhttp.send();
}

//getTicket(display);
//to display on load, uncomment line 12, add url as another parameter to line 1,

function display(xhr) {
    tickets = JSON.parse(xhr.responseText);
     table = document.getElementById("user-ticket"); //user-ticket is tableID/place holder in employee.html
    for ( let i in tickets) {
        let id = tickets[i]['ticketID'].toString();

        let amount = tickets[i]['ticketAmount'].toString();
        if (amount.includes('.')) {
            if(amount.length-amount.indexOf('.') == 2)
                amount = amount + "0";
        } else {
            amount = amount + ".00";
        }

        let status = "";
        if (tickets[i].ticketStatusID == 1) {
            status = 'Approved';
        } else if (tickets[i].ticketStatusID == 2) {
            status = 'Denied';
        } else {
            status = 'Pending';
        }

        let description = tickets[i]['ticketDescription'];

        let type = "";
        if (tickets[i].ticketTypeID == 1) {
            type = 'Lodging';
        } else if (tickets[i].ticketTypeID == 2) {
            type = 'Travel';
        } else if (tickets[i].ticketTypeID == 3) {
            type = 'Food';
        } else{
            type = 'Other';
        }

        let submittedDate = tickets[i]['ticketSubmitted'];
        let resolvedDate = tickets[i]['ticketResolved'];

        if(resolvedDate == null)
            resolvedDate ="N/A";

        let resolverID = tickets[i]['ticketResolver'];
        if(resolverID == 0)
            resolverID = "N/A";

        let authorID = tickets[i]['ticketAuthor'];


        newRow = document.createElement("tr");

        newRow.innerHTML =
            `<td>${id}</td>
            <td>${amount}</td>
            <td>${submittedDate}</td>
            <td>${description}</td>
            <td>${authorID}</td>
            <td>${resolvedDate}</td>
            <td>${resolverID}</td>
            <td>${status}</td>
            <td>${type}</td>`;

        table.appendChild(newRow);
    }
}