function deleteCheckSubmit() {

    if (confirm('Are you sure?')) {
        document.forms['deletemenuform'].submit();
    } else {
        return false;
    }
}

function HutLocation(name, lat, lng, imgurl, showurl) {
    this.name = name;
    this.lat = lat;
    this.lng = lng;
    this.showurl = showurl;
    this.imgurl = imgurl;
}

function toggleMapList() {
    var oldList = document.getElementById("oldlist");
    var map = document.getElementById("map");

    if (map.style.display == "none") {
        map.style.display = "";
        oldList.style.display = "none";
    } else {
        map.style.display = "none";
        oldList.style.display = "";
    }
}


function initializeMaps(hutlocs) {
    var norway = new GLatLng(64.830253743883, 16.2158203125);

    if (GBrowserIsCompatible()) {
        var map = new google.maps.Map2(document.getElementById("map"));

        map.setCenter(norway, 4);

        map.addControl(new GSmallMapControl());
        map.addControl(new GMapTypeControl());
        map.addControl(new GScaleControl());
    } else {
        toggleMapList();
    }

    for (name in hutlocs) {
        var hutloc = hutlocs[name];

        var marker = getMarker(hutloc);

        map.addOverlay(marker);
    }
}

function getMarker(hutloc) {
    var marker = new GMarker(new GLatLng(hutloc.lat, hutloc.lng));

    var popup = "<h4>" + hutloc.name + "</h4>";

    if (hutloc.imgurl) 
{
    popup += "<img width='150px' src='" + hutloc.imgurl + "'/>";
}

    popup += "<p><a href='" + hutloc.showurl + "'>" + hutloc.name + "</a>";

    GEvent.addListener(marker, "click", function() {
        marker.openInfoWindowHtml(popup);
    });

    return marker;
}
