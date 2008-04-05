Weekly Rental Report

${person.name},

Overview of bookings this week

<%
    rentals.each {rental ->
%>
Hut: ${rental.hut}
Contact: ${rental.contact.name} ${rental.contact.email}/${rental.contact.phone}
From: ${rental.start}
To: ${rental.end}
People: ${rental.people}
<%
        if (rental.price) {
%>
Price: ${rental.currency} ${rental.price}
<%
        }
%>
<%
    }
%>