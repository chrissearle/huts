function deleteCheckSubmit() {

    if (confirm('Are you sure?')) {
        document.forms['deletemenuform'].submit();
    } else {
        return false;
    }
}

function HutLocation(name, lat, lng, imgurl, showurl, linktext, description, organization, huttype) {
    this.name = name;
    this.lat = lat;
    this.lng = lng;
    this.showurl = showurl;
    this.imgurl = imgurl;
    this.linktext = linktext;
    this.description = description;
    this.organization = organization;
    this.huttype = huttype;
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

    // Zoom to bounds
    map.setZoom(map.getBoundsZoomLevel(bounds));
    map.setCenter(bounds.getCenter());

    // Based on that - grow top by a small amount
    bounds = growTopBound(map, bounds);

    // And re-do the zoom
    map.setZoom(map.getBoundsZoomLevel(bounds));
    map.setCenter(bounds.getCenter());

    // Force max zoom
    if (map.getZoom() > 12) {
        map.setZoom(12);
    }
}


function initializeSingleMap(lat, lng, huttype) {
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

    var markerOptions = { clickable: false, icon: getIcon(huttype) };

    var marker = new GMarker(point, markerOptions);

    map.addOverlay(marker);
}

function getMarker(hutloc, bounds) {
    var markerOptions = { icon:getIcon(hutloc.huttype) };

    var latlng = new GLatLng(hutloc.lat, hutloc.lng);
    var marker = new GMarker(latlng, markerOptions);

    bounds.extend(latlng);

    var popup = "<h4>" + hutloc.name + "</h4>";

    if (hutloc.organization) {
        popup += "<h5>" + hutloc.organization + "</h5>";
    }

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

function growTopBound(map, bounds) {
    var latlngNorthEast = bounds.getNorthEast();

    var pointNorthEast = map.fromLatLngToDivPixel(latlngNorthEast);

    bounds.extend(map.fromDivPixelToLatLng(new GPoint(pointNorthEast.x, pointNorthEast.y - 75)));

    return bounds;
}

function getIcon(huttype) {

    // Create our "tiny" marker icon
    var tinyIcon = new GIcon();
    var icon = "red";
    if (huttype == "PRIVATE") {
        icon = "blue";
    }
    if (huttype == "OWNER") {
        icon = "yellow";
    }
    tinyIcon.image = "http://labs.google.com/ridefinder/images/mm_20_" + icon + ".png";
    tinyIcon.shadow = "http://labs.google.com/ridefinder/images/mm_20_shadow.png";
    tinyIcon.iconSize = new GSize(12, 20);
    tinyIcon.shadowSize = new GSize(22, 20);
    tinyIcon.iconAnchor = new GPoint(6, 20);
    tinyIcon.infoWindowAnchor = new GPoint(5, 1);

    return tinyIcon;
}