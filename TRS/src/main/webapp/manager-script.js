function display_2(xhr) {
    clearTable();
    tickets = JSON.parse(xhr.responseText);
    table = document.getElementById("user-ticket");
    for (let i in tickets) {

        let id = tickets[i]['ticketID'].toString();

        let amount = tickets[i]['ticketAmount'].toString();
        if (amount.includes('.')) {
            if (amount.length - amount.indexOf('.') == 2)
                amount = amount + "0";
        } else {
            amount = amount + ".00";
        }

        let status_ = "";
        if (tickets[i].ticketStatusID == 1) {
            status_ = 'Approved';
        } else if (tickets[i].ticketStatusID == 2) {
            status_ = 'Denied';
        } else {
            status_ = 'Pending';
        }

        let description = tickets[i]['ticketDescription'];

        let type = "";
        if (tickets[i].ticketTypeID == 1) {
            type = 'Lodging';
        } else if (tickets[i].ticketTypeID == 2) {
            type = 'Travel';
        } else if (tickets[i].ticketTypeID == 3) {
            type = 'Food';
        } else {
            type = 'Other';
        }

        let submittedDate = tickets[i]['ticketSubmitted'];

        let resolvedDate = tickets[i]['ticketResolved'];
        if (resolvedDate == null)
            resolvedDate = "N/A";

        let resolverid = tickets[i]['ticketResolver'];
        if (resolverid == 0)
            resolverid = "N/A";

        let authorID = tickets[i]['ticketAuthor'];

        newRow = document.createElement("tr");

        newRow.innerHTML =
            `<td>${id}</td>
                <td>${amount}</td>
                <td>${submittedDate}</td>
                <td>${description}</td>
                <td>${authorID}</td>
                <td>${resolvedDate}</td>
                <td>${resolverid}</td>
                <td>${status_}</td>
                <td>${type}</td>`;

        table.appendChild(newRow);
    }
}

function display_to_resolve(xhr) {
    clearTable();
    tickets = JSON.parse(xhr.responseText);
    table = document.getElementById("user-ticket");
    document.getElementById("table-title").innerHTML = "Here Is The List Of All Pending Ticket";
    for (let i in tickets) {

        let id = tickets[i]['ticketID'];

        let amount = tickets[i]['ticketAmount'].toString();
        if (amount.includes('.')) {
            if (amount.length - amount.indexOf('.') == 2)
                amount = amount + "0";
        } else {
            amount = amount + ".00";
        }

        let status_ = "";
        if (tickets[i].ticketStatusID == 1) {
            status_ = 'Approved';
        } else if (tickets[i].ticketStatusID == 2) {
            status_ = 'Denied';
        } else {
            status_ = 'Pending';
        }

        let description = tickets[i]['ticketDescription'];

        let type = "";
        if (tickets[i].ticketTypeID == 1) {
            type = 'Lodging';
        } else if (tickets[i].ticketTypeID == 2) {
            type = 'Travel';
        } else if (tickets[i].ticketTypeID == 3) {
            type = 'Food';
        } else {
            type = 'Other';
        }

        let submittedDate = tickets[i]['ticketSubmitted'];
        let resolvedDate = tickets[i]['ticketResolved'];

        if (resolvedDate == null)
            resolvedDate = "N/A";

        let resolverid = tickets[i]['ticketResolver'];
        if (resolverid == 0)
            resolverid = "N/A";

        let authorID = tickets[i]['ticketAuthor'];

        newRow = document.createElement("tr");

        newRow.innerHTML =
            `<td>${id}</td>
                <td>${amount}</td>
                <td>${submittedDate}</td>
                <td>${description}</td>
                <td>${authorID}</td>
                <td>${resolvedDate}</td>
                <td>${resolverid}</td>
                <td>${status_}</td>
                <td>${type}</td>
                <td><form action="resolve.do" method="post">
                    <button type="submit" name='approve-button' value=${id}>Approve</button>
                    <button type="submit" name='deny-button' value=${id}>Deny</button>
                    </form>
                 </td>
            `;
        table.appendChild(newRow);
    }
}

function clearTable() {
    table = document.getElementById("user-ticket");
    for (var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }
}

document.getElementById("pending_button").onclick = function () {
    //displays pending request
    var xhttp2 = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhttp2.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            display_to_resolve(this);
        }
    };
    xhttp2.open("GET", "http://localhost:8080/TicketReimbursementSystem_war/tickets-manager.view");
    xhttp2.send();
}

document.getElementById("resolve_button").onclick = function () {
    var xhttp2 = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhttp2.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            display_2(this);
        }
    };
    xhttp2.open("GET", "http://localhost:8080/TicketReimbursementSystem_war/getResolved.view");
    xhttp2.send();
}
