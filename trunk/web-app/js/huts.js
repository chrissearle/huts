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
    setupKey();

    //    var norway = new GLatLng(64.830253743883, 16.2158203125);
    var norway = new GLatLng(60, 10.7);

    if (GBrowserIsCompatible()) {
        var map = new google.maps.Map2(document.getElementById("map"));

        map.setCenter(norway, 7);

        map.addControl(new GLargeMapControl());
        map.addControl(new GMapTypeControl(), new GControlPosition(G_ANCHOR_BOTTOM_RIGHT, 0));
    } else {
        toggleMapList();
    }

    var markersArray=[];

    for (name in hutlocs) {
        var hutloc = hutlocs[name];

        var marker = getMarker(hutloc);

        markersArray.push(marker);
    }

    var cluster=new ClusterMarker(map, { markers:markersArray, clusterMarkerIcon: getClusterIcon() } );
    cluster.fitMapToMarkers();

    return map;
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

function getMarker(hutloc) {
    var markerOptions = { icon:getIcon(hutloc.huttype) };

    var latlng = new GLatLng(hutloc.lat, hutloc.lng);
    var marker = new GMarker(latlng, markerOptions);

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

function getIcon(huttype) {

    var primaryColor = "#FF0000";

    if (huttype == "PRIVATE") {
        primaryColor = "#0000FF";
    }
    if (huttype == "OWNER") {
        primaryColor = "#FFFF00";
    }

    var newIcon = MapIconFactory.createMarkerIcon({width: 30, height: 30, primaryColor: primaryColor});

    return newIcon;
}

function getClusterIcon() {
    var clusterIcon = MapIconFactory.createMarkerIcon({width: 40, height: 40, primaryColor: "#ccccff"});

    return clusterIcon;
}

function toggleMapKey() {
        var key = document.getElementById("mapkey");

        if (key.style.display == "none") {
            key.style.display = "";
        } else {
            key.style.display = "none";
        }

}

function setupKey() {
    var icons = new Array();

    icons[0] = new MapKey(getIcon("PUBLIC").image, "A public hut that anyone can book");
    icons[1] = new MapKey(getIcon("PRIVATE").image, "A private hut that you can book");
    icons[2] = new MapKey(getIcon("OWNER").image, "A hut you own");
    icons[3] = new MapKey(getClusterIcon().image, "Group of huts that are too close together to show (click to zoom in)");

    var htmlCode = '<table id="key" class="key">';

    for (id in icons) {
        htmlCode += '<tr><td>';
        htmlCode += '<img src="' + icons[id].url + '"/>';
        htmlCode += '</td><td>';
        htmlCode += icons[id].desc;
        htmlCode += '</td></tr>';
    }
    htmlCode += '</table>';

    var key = document.getElementById("mapkey");

    key.style.position = "absolute";
    key.style.padding = "10px";
    key.innerHTML = htmlCode;
}

function MapKey(url, desc) {
    this.url = url;
    this.desc = desc;
}