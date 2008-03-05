import java.text.DateFormat
import java.text.SimpleDateFormat

class HutsTagLib {
    def showBooking = {attrs, body ->
        def b = Booking.get(attrs.bookingId)

        def p = Person.findByUserId(attrs.userId)

        if (p.admin || b.hut.owner == p || b.contact == p) {
            out << body()
        }
    }

    def showBookingDate = {attrs, body ->
        def b = Booking.get(attrs.bookingId)

        def p = Person.findByUserId(attrs.userId)

        if (!(p.admin || b.hut.owner == p || b.contact == p)) {
            out << body()
        }
    }

    def manageHut = {attrs, body ->
        def h = Hut.get(attrs.hutId)

        def p = Person.findByUserId(attrs.userId)

        if (p.admin || h.owner == p) {
            out << body()
        }
    }

    def isAdmin = {attrs, body ->
        def p = Person.findByUserId(attrs.userId)

        if (p.admin) {
            out << body()
        }
    }

    def monthView = {attrs ->
        def p = Person.findByUserId(attrs.userId)

        def hut = Hut.get(attrs.hutId)

        def count = 3
        if (attrs.monthcount) {
            count = new Integer(attrs.monthcount)
        }

        Calendar cal = Calendar.getInstance()
        cal.setFirstDayOfWeek(Calendar.MONDAY)

        DateFormat monthYear = new SimpleDateFormat("MMM yyyy");

        out << "<table><tr valign='top'>"

        1.upto(count) {c ->

            out << "<td><table class='datetable'><tr><th colspan='8'>"

            out << monthYear.format(cal.getTime());

            out << "</th></tr>"

            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1)

            def startWeek = cal.get(Calendar.WEEK_OF_YEAR)

            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1)

            cal.add(Calendar.DAY_OF_YEAR, -1)

            def endWeek = cal.get(Calendar.WEEK_OF_YEAR)

            def maxWeekOfYear = 54

            if (endWeek < startWeek) {
                // December - we rolled round
                cal.add(Calendar.WEEK_OF_YEAR, -1)

                maxWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR)

                endWeek = maxWeekOfYear + 1
            }

            def lastDayOfMonth = cal.get(Calendar.DAY_OF_WEEK)

            cal.set(Calendar.WEEK_OF_YEAR, startWeek)

            cal.set(Calendar.DAY_OF_MONTH, 1)

            def dow = cal.get(Calendar.DAY_OF_WEEK)

            if (dow < Calendar.MONDAY) {
                dow = dow + 7
            }
            dow = dow - (Calendar.MONDAY + 1)

            def prefix = 7 - dow


            def suffix = 7 - (lastDayOfMonth - 1)

            if (suffix == 7) {
                suffix = 0
            }

            out << "<tr><th>&nbsp;</th><th>M</th><th>T</th><th>W</th><th>T</th><th>F</th><th>S</th><th>S</th></th>"

            def lastDate = 0

            for (week in startWeek..endWeek) {
                out << "<tr>"

                if (week > maxWeekOfYear) {
                    out << "<th>1</th>"
                } else {
                    out << "<th>$week</th>"
                }

                def start = lastDate + 1
                def end = start + 7

                if (prefix >= 0 && prefix < 7) {
                    prefix.upto(7, {x ->
                        out << "<td>&nbsp;</td>"
                        end--
                    })
                }

                prefix = -1

                if (week == endWeek) {
                    end = end - suffix
                }


                for (date in start..<end) {
                    Calendar cal2 = Calendar.getInstance()
                    cal2.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), date, 0, 0, 0)

                    out << "<td"
                    def booking = isBooked(cal2.getTime(), hut)

                    if (booking) {
                        def cssClass = "booked"
                        if (booking.contact == p) {
                            cssClass = "bookedMe"
                        }
                        out << " class='$cssClass'"
                    }

                    out << ">$date</td>"
                    lastDate = date
                }

                out << "</tr>"
            }

            out << "</table></td>"

            if (c % 2 == 0) {
                out << "</tr><tr valign='top'>"
            }

            cal.add(Calendar.MONTH, 1)
        }

        out << "</tr></table>"
    }

    Booking isBooked(Date date, Hut hut) {
        def critera = Booking.createCriteria();

        def result = critera.list {
            and {
                eq("hut", hut)
                gt("endDate", date)
                le("startDate", date)
            }
        }

        if (result.size()) {
            return result.get(0)
        }

        return null
    }

}