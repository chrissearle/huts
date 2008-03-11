function deleteCheckSubmit() {

    if (confirm('Are you sure?')) {
        document.forms['deletemenuform'].submit();
    } else {
        return false;
    }
}

function HutLocation(name, lat, lng, imgurl, showurl, linktext, description) {
    this.name = name;
    this.lat = lat;
    this.lng = lng;
    this.showurl = showurl;
    this.imgurl = imgurl;
    this.linktext = linktext;
    this.description = description;
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

function toggleMapView() {
    var img = document.getElementById("hutimg");
    var map = document.getElementById("map");

    if (map.style.display == "none") {
        map.style.display = "";
        img.style.display = "none";
    } else {
        map.style.display = "none";
        img.style.display = "";
    }
}

function initializeMaps(hutlocs) {
    //    var norway = new GLatLng(64.830253743883, 16.2158203125);
    var norway = new GLatLng(60, 10.7);

    if (GBrowserIsCompatible()) {
        var map = new google.maps.Map2(document.getElementById("map"));

        map.setCenter(norway, 7);

        map.addControl(new GLargeMapControl());
        map.addControl(new GMapTypeControl());
    } else {
        toggleMapList();
    }

    var bounds = new GLatLngBounds();

    for (name in hutlocs) {
        var hutloc = hutlocs[name];

        var marker = getMarker(hutloc, bounds);

        map.addOverlay(marker);
    }

    map.setZoom(map.getBoundsZoomLevel(bounds));

    map.setCenter(bounds.getCenter());
}


function initializeSingleMap(lat, lng) {
    var point = new GLatLng(lat, lng);

    if (GBrowserIsCompatible()) {
        var map = new google.maps.Map2(document.getElementById("map"));

        map.setCenter(point, 7);

        map.addControl(new GSmallMapControl());
        map.addControl(new GMapTypeControl());
    }

    if (document.getElementById("hutimg")) {
        toggleMapView();
    }

    var marker = new GMarker(point);

    map.addOverlay(marker);
}

function getMarker(hutloc, bounds) {
    var latlng = new GLatLng(hutloc.lat, hutloc.lng)
    var marker = new GMarker(latlng);

    bounds.extend(latlng);

    var popup = "<h4>" + hutloc.name + "</h4>";

    if (hutloc.imgurl) {
        popup += "<img width='150px' src='" + hutloc.imgurl + "'/>";
    }

    popup += "<p class='maptext'>" + hutloc.description + "</p>";
    popup += "<p class='maptext'><a href='" + hutloc.showurl + "'>" + hutloc.linktext + "</a></p>";

    GEvent.addListener(marker, "click", function() {
        marker.openInfoWindowHtml(popup);
    });

    return marker;
}
